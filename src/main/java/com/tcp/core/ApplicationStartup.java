package com.tcp.core;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent>, DisposableBean {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("serverBootstrap")
    private ServerBootstrap		serverBootstrap;

    @Autowired
    @Qualifier("tcpSocketAddress")
    private InetSocketAddress	tcpSocketAddress;

    private ChannelFuture serverChannelFuture = null;

    public void onApplicationEvent(ApplicationReadyEvent event) {
        ApplicationContext context = event.getApplicationContext();
        try {
            serverChannelFuture = serverBootstrap.bind(tcpSocketAddress).sync();
            if (serverChannelFuture.isSuccess()) {
                logger.info("TCP Start Success............." +tcpSocketAddress.toString());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.error("TCP Start fail................", e);
        }

    }

    @Override
    public void destroy() throws Exception {
        if (serverChannelFuture != null) {
            logger.debug("serverChannelFuture close ............." );
            serverChannelFuture.channel().closeFuture().sync();
        }
    }
}