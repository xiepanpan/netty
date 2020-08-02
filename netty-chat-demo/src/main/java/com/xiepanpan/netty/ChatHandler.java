package com.xiepanpan.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: xiepanpan
 * @Date: 2020/8/1 0001
 * @Description: 自定义聊天处理器 TextWebSocketFrame: 在netty中，是用于为websocket专门处理文本的对象，frame是消息的载体
 *
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    //用来保存所有的客户端连接
    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:MM");

    /**
     * 当Channel中有新的事件消息会自动调用
     * @param channelHandlerContext
     * @param textWebSocketFrame
     * @throws Exception
     */
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {

        //获取客户端发送过来的文本消息
        String text = textWebSocketFrame.text();

        System.out.println("接收到消息数据："+text);

        for (Channel channel: channels) {
            channel.writeAndFlush(new TextWebSocketFrame(sdf.format(new Date())+""+text));
        }
    }

    //当有新的客户端连接服务器后 会自动调用这个方法
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //将新的通道加入到channels中
        channels.add(ctx.channel());
    }
}