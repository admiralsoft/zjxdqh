package com.tcp.tcp.service.impl;

import com.tcp.core.code.MQCode;
import com.tcp.core.code.RedisCode;
import com.tcp.core.code.TCPCode;
import com.tcp.core.service.BaseService;
import com.tcp.tcp.base.TCPService;
import com.tcp.tcp.vo.send.vo.PackDataDownVo;
import com.tcp.util.DownPackDataUtill;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

/**
 * @author TcT
 * Date: 2018/8/15.
 * Time: 下午7:44.
 * @Title:
 * @Description: 数据包分包下载回复
 */
@Service("packDataDownServiceImpl")
@Slf4j
public class PackDataDownServiceImpl extends BaseService<Object> implements TCPService {

    @Override
    public void service(ChannelHandlerContext ctx, byte[] bytes, String pileNum) {

        PackDataDownVo info = packDataDownParseImpl.getInfo(bytes);
        Map<String, HashMap<String, byte[]>> downData = DownPackDataUtill.downData;
        Map<String, Map<String, Integer>> faltMark = DownPackDataUtill.faltMark;
        //升级指示
        byte type = info.getUpgradeType();
        switch (type) {
            //0，准备过程失败
            case 0:
                delFileCacheAndMarkCache(pileNum, downData, faltMark);
                sender.sendRabbitmqCollectDirect(getResultObj(pileNum,"下载失败",MQCode.PACKDATA_DOWN,false));
                log.info("桩号{},准备过程失败",pileNum);
                break;
            //1，准备就绪，可以下发升级数据
            case 1:
                /*获取缓存数据*/
                if (downData.get(pileNum) != null && downData.get(pileNum).size() > 0) {
                    HashMap<String, byte[]> map = downData.get(pileNum);
                    /*准备就绪发送第一个包*/
                    String nowResultNum = String.valueOf(Integer.parseInt(info.getNowResultNum()) + 1);//获取桩当前包,第一次应该是0,所以满足协议应该是1
                    //获取下载数据
                    byte[] data = map.get(nowResultNum);
                    info.setData(data);
                    //传第一个包,command=2:进入升级数据模式
                    int command = 2;
                    info.setCommand(command);
                    //当前包
                    info.setNowResultNum(nowResultNum);
                    sendPackage(ctx,info,pileNum);
                    log.info("指令1:桩号:{},第{}包开始传输.",pileNum,nowResultNum);
                }
                break;
            //2，接收成功
            case 2:
                /*传下一个包*/
                if (downData.get(pileNum) != null && downData.get(pileNum).size() > 0) {
                    HashMap<String, byte[]> map = downData.get(pileNum);
                    /*获取上一个发送包的下标+1,取这次应该发送的包数据,前提是包不是最后一个包*/
                    //最大包数
                    int resultNum =Integer.parseInt(info.getResultNum());
                    //当前包
                    String nextPack = String.valueOf(Integer.parseInt(info.getNowResultNum()) + 1);
                    int next = Integer.parseInt(nextPack);
                    info.setNowResultNum(nextPack);
                    //当前包小于最大包
                    if (next <= resultNum) {
                        /*获取下载数据*/
                        byte[] data = map.get(nextPack);
                        info.setData(data);
                        //传第一个包,command=2:进入升级数据模式
                        int command = 2;
                        info.setCommand(command);
                        sendPackage(ctx, info, pileNum);
                        log.info("指令2:桩号:{},第{}包开始传输.", pileNum, nextPack);
                    }
                    //传输完毕
                    if (next > resultNum){
                        //command=3:数据包发送完毕
                        info.setNowResultNum(String.valueOf(next - 1));//保证当前包的一致性
                        byte[] data = new byte[0];
                        info.setData(data);
                        int command = 3;
                        info.setCommand(command);
                        sendPackage(ctx, info, pileNum);
                        log.info("指令2:桩号:{},数据包发送完毕.", pileNum);
                    }
                }
                break;
            //3，数据包接收完毕
            case 3:
                //删除缓存数据,通知MQ
                delFileCacheAndMarkCache(pileNum, downData, faltMark);
                redisUtil.setValueExpire(RedisCode.PILE_UPGRADE, pileNum, MQCode.PACKDATA_DOWN);
                //升级成功（0207指令返回升级结果）
//                sender.sendRabbitmqCollectDirect(getResultObj(pileNum,"升级成功", MQCode.PACKDATA_DOWN,true));
                log.info("桩号{},数据包接收成功!",pileNum);
                break;
            //4，接收失败
            case 4:
                //重发当前包3次,3次后表示失败
                if (downData.get(pileNum) != null && downData.get(pileNum).size() > 0) {
                    HashMap<String, byte[]> map = downData.get(pileNum);
                    String nowResultNum = info.getNowResultNum();
                    //获取上个当前包
                    byte[] data = map.get(nowResultNum);
                    info.setData(data);
                    //重发,command=2:进入升级数据模式
                    int command = 2;
                    info.setCommand(command);
                    //已有失败缓存记录
                    if (faltMark.get(pileNum) != null){
                        Map<String, Integer> marks = faltMark.get(pileNum);
                        if (marks.get(nowResultNum) != null){
                            Integer count = marks.get(nowResultNum);
                            if (count <= 3){
                                //重发
                                sendPackage(ctx, info, pileNum);
                                Map<String, Integer> hashMap = new HashMap<>();
                                hashMap.put(nowResultNum,count+1);
                                faltMark.put(pileNum,hashMap);
                            }else{
                                //回复MQ下载失败
                                sender.sendRabbitmqCollectDirect(getResultObj(pileNum,"下载失败",MQCode.PACKDATA_DOWN,false));
                                delFileCacheAndMarkCache(pileNum, downData, faltMark);
                                log.info("桩号:{},数据包{}重发多次失败.", pileNum,nowResultNum);
                            }
                        }
                    }else { //未有失败缓存记录
                        sendPackage(ctx, info, pileNum);
                        Map<String, Integer> hashMap = new HashMap<>();
                        //记录第一次失败
                        hashMap.put(nowResultNum, 1);
                        faltMark.put(pileNum, hashMap);
                        log.info("桩号:{},数据包{}重发.", pileNum, nowResultNum);
                    }
                }
                break;
            //5，升级失败，需重新升级
            case 5:
                //删除文件缓存及失败记录数据
                delFileCacheAndMarkCache(pileNum, downData, faltMark);
                sender.sendRabbitmqCollectDirect(getResultObj(pileNum,"下载失败",MQCode.PACKDATA_DOWN,false));
                log.info("桩号:{},下载失败.",pileNum);
                break;

        }
    }

    private void delFileCacheAndMarkCache(String pileNum, Map<String, HashMap<String, byte[]>> downData, Map<String, Map<String, Integer>> faltMark) {
        if (downData.get(pileNum) != null && downData.get(pileNum).size() > 0) {
            downData.remove(pileNum);
            log.info("删除文件缓存数据成功!");
        }
        //删除失败缓存
        if (faltMark.get(pileNum) != null && faltMark.get(pileNum).size() > 0){
            faltMark.remove(pileNum);
            log.info("删除传送失败记录数据成功!");
        }
    }


    //发送数据包
    private void sendPackage(ChannelHandlerContext ctx, PackDataDownVo info , String pileNum) {
        short cmd = TCPCode.DOWN_PACKDATA_UPDATE;
        sendMessage(ctx,packDataDownCommandImpl.getByteInfo(info,pileNum,cmd),pileNum,cmd);
    }
}
