package com.tcp.tcp.convert.parse.impl;

import com.tcp.tcp.convert.parse.BaseParse;
import com.tcp.tcp.convert.parse.TCPParseService;
import org.springframework.stereotype.Service;

/**
 * @Author Xu
 */
@Service("keyUpdateResultParseImpl")
public class KeyUpdateResultParseImpl extends BaseParse implements TCPParseService<Byte> {
    @Override
    public Byte getInfo(byte[] data) {
        int num = SUBSCRIPT;
        return data[num];
    }
}
