package com.xiepanpan.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author: xiepanpan
 * @Date: 2020/8/1 0001
 * @Description:
 */
public class WebSocketNettyServer {

    public static void main(String[] args) {

        //创建两个线程池
        //主线程池
        NioEventLoopGroup mainGroup = new NioEventLoopGroup();
        //从线程池
        NioEventLoopGroup subGroup = new NioEventLoopGroup();

        try {
            //创建netty服务器启动对象
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            //初始化服务器启动对象
            serverBootstrap
                    .group(mainGroup,subGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new WebSocketChannelInitializer());

            //绑定服务器端口 以同步的方式启动服务器
            ChannelFuture future = serverBootstrap.bind(9090).sync();
            //等待服务器关闭
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //关闭服务器
            mainGroup.shutdownGracefully();
            mainGroup.shutdownGracefully();
        }
    }
}