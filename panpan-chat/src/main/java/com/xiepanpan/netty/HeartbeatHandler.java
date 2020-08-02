package com.xiepanpan.netty;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * @author: xiepanpan
 * @Date: 2020/8/2 0002
 * @Description:  心跳检查处理类  如果读写空闲 关闭通道资源
 */
public class HeartbeatHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;

            if (idleStateEvent.state() == IdleState.READER_IDLE) {
                System.out.println("读空闲事件触发。。。。");
            }
            else if (idleStateEvent.state() == IdleState.WRITER_IDLE) {
                System.out.println("写空闲事件触发。。。");
            }
            else if (idleStateEvent.state() == IdleState.ALL_IDLE) {
                System.out.println("----------");
                System.out.println("读写空闲事件触发");
                System.out.println("关闭通道资源");
                ctx.channel().close();
            }
        }
    }
}