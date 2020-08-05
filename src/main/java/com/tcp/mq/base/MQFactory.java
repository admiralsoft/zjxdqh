
package com.tcp.mq.base;

/**
 * @param <T>这里用什么类型
 *            由自己的需求而定 我这样写是方便适应
 */
public interface MQFactory<T> {

	void mqMessage(T t);

	void mqMessage(Object code, Object pileNum, T t);
}
