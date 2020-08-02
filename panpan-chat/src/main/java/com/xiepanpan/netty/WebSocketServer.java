package com.xiepanpan.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.stereotype.Component;

/**
 * @author: xiepanpan
 * @Date: 2020/8/1 0001
 * @Description:
 */
@Component
public class WebSocketServer {

    //主线程池
    private EventLoopGroup bossGroup;
    //工作线程池
    private EventLoopGroup workerGroup;
    //服务器
    private ServerBootstrap serverBootstrap;
    //回调
    private ChannelFuture future;

    public WebSocketServer() {
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();

        serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new WebSocketInitializer());
    }

    public void start() {
        future = serverBootstrap.bind(9001);

        System.out.println("netty server --启动成功");
    }
}
