
package com.tcp.tcp.convert.parse;

public interface TCPParseService<T> {

	T getInfo(byte[] data);
}
