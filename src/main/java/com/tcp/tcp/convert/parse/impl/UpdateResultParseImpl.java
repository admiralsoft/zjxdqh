package com.tcp.tcp.convert.parse.impl;

import com.tcp.log.CustomerLogger;
import com.tcp.tcp.convert.ByteToMessageConvert;
import com.tcp.tcp.convert.parse.BaseParse;
import com.tcp.tcp.convert.parse.TCPParseService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author TcT
 * Date: 2018/8/9.
 * Time: 下午2:11.
 * @Title:
 * @Description:
 */
@Service("updateResultParseImpl")
public class UpdateResultParseImpl extends BaseParse implements TCPParseService<Map<String, Integer>> {

    @Override
    public Map<String, Integer> getInfo(byte[] data) {

        int num = SUBSCRIPT;
        /*回复结果 1. 查询返回更新成功 2. 查询返回更新失败 3. 主动发送更新成功 4. 主动发送更新失败 */
        Integer result = (int) data[num++];
        /*失败原因 1. 软件验证失败 2. 电桩更新失败 (结果为失败时，必选，成功填 0)*/
        Integer failResult = (int)data[num];
        Map<String, Integer> map = new HashMap<>();
        map.put("result",result);
        map.put("failResult",failResult);

        //输出 指令 日志
        CustomerLogger.printCommandLogger(ByteToMessageConvert.unWrapConvertCmd(data), ByteToMessageConvert.unWrapConvertDeviceNo(data), map);
        return map;
    }
}
