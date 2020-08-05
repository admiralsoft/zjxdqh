
package com.tcp.tcp.convert.command;

public interface CommandService<T> {

	byte[] getByteInfo(T t, String pileNum, short cmd);
}
