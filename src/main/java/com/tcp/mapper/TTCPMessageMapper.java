
package com.tcp.mapper;

import com.tcp.bean.TTCPMessage;

/**
 * 报文监听
 */
public interface TTCPMessageMapper {

	int insert(TTCPMessage record);
}