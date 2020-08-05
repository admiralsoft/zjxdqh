package com.tcp.tcp.convert.command.impl;

import com.tcp.bean.MUpgradeResult;
import com.tcp.tcp.convert.command.BaseCommand;
import com.tcp.tcp.convert.command.CommandService;
import com.tcp.util.DataUtil;
import com.tcp.util.DownPackDataUtill;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author TcT
 * Date: 2018/8/15.
 * Time: 上午10:31.
 * @Title:
 * @Description: 数据包分包下载
 */
@Service("activePackDataDownCommandImpl")
@Slf4j
public class ActivePackDataDownCommandImpl extends BaseCommand implements CommandService<MUpgradeResult> {


    @Override
    public byte[] getByteInfo(MUpgradeResult mUpgradeResult, String pileNum, short cmd) {
        /* 数据段头部 */
        int num = headLength;
        byte[] data = getSendHead(pileNum, cmd, 1);
        //获取文件数据源
        String url = mUpgradeResult.getUpgradeLocal();
        //版本号作为文件名
        String fileName = mUpgradeResult.getVersion();
        //需要拆分的包大小
        double packSize = Double.valueOf(mUpgradeResult.getNowResultNum());

        //服务器里获取文件
//        String path = "/Users/mac/Documents/log/"+fileName;
        String path = "/opt/mms_collect/"+fileName;
        File file = new File(path);
        //文件不存在则下载
        if (!file.exists()){
            int code = DataUtil.DownLoadPages(url, fileName);
            if (code != 1){
                //todo:返回MQ,文件下载失败
                log.info("文件下载失败!");
                return null;
            }
        }
        FileInputStream is;
        try {
            is = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.error("数据包升级,文件获取异常:",e);
            //todo: 返回MQ,文件下载失败
            return null;
        }
        DataInputStream stream = new DataInputStream(is);
        int fileLength = 0;
        HashMap<String, byte[]> map = new HashMap<>();
        try {
            //Map<String, LinkedList<Map<String,byte[]>>> downData = DownPackDataUtill.downData;
            Map<String, HashMap<String, byte[]>> downData = DownPackDataUtill.downData;
            //判断缓存里是否已有数据,有则先清空残余
            if (downData.get(pileNum) != null){
                downData.remove(pileNum);
            }
            //LinkedList<Map<String,byte[]>> linkedList = new LinkedList<>();
            //获取资源长度
            fileLength = stream.available();
            byte[] fileData = new byte[fileLength];
            //注入到fileData
            stream.readFully(fileData);
            int size = fileData.length;
            //拆包个数
            double dataNum = Math.ceil(size / packSize);
            //拆成N个包,一次发packNum大小
            int packNum =(int) Math.ceil(size / dataNum);
            int mark = 0;
            byte[] small;
            int index = 1;
            while (size > 0){
                int sendSize = packNum;//发送大小
                if (size < sendSize){ //长度不足
                    sendSize = size;
                }
                //截取
                small = new byte[sendSize];
                //mark起始,0起始,sendSize拷贝长度
                System.arraycopy(fileData,mark,small,0,sendSize);
                // small 放缓存
                map.put(String.valueOf(index),small);
                mark += sendSize;//移动起始发送位置
                size -= sendSize;//减去已经发送字节.
                index++;
            }
            //放缓存
            downData.put(pileNum,map);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("数据操作流异常:",e);
            return null;
        }

        /* 解析*/
        //升级指示 1，进入升级模式 2，升级数据 3，数据包下发完毕
        data[num++] = 1;
        //软件版本号
        byte[] toAsciiByte = DataUtil.StringToAsciiByte(mUpgradeResult.getVersion(), 4);
        for (int i = 0; i < 4; i++) {
            data[num++] = toAsciiByte[i];
        }
        //总数据长度
        log.info("总数据长度:"+fileLength);
        byte[] lengths = DataUtil.intToFourBytes(fileLength);
        for (int i = 0; i < 4; i++) {
            data[num++] = lengths[i];
        }
        //总数据包数
        byte[] allNum = DataUtil.intToFourBytes(map.size());
        for (int i = 0; i < 4; i++) {
            data[num++] = allNum[i];
        }
        //已发送数据长度
        for (int i = 0; i < 4; i++) {
            data[num++] = 0;
        }
        //当前包数
        for (int i = 0; i < 4; i++) {
            data[num++] = 0;
        }
        //升级地址
        for (int i = 0; i < 4; i++) {
            data[num++] = 0;
        }
        //升级数据长度
        for (int i = 0; i < 2; i++) {
            data[num++] = 0;
        }
        //升级数据
        data[num++] = 0;
        //包尾
        return getData(data, num);
    }

}
