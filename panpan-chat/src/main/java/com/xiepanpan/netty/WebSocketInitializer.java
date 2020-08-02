package com.xiepanpan.netty;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @author: xiepanpan
 * @Date: 2020/8/1 0001
 * @Description:
 */
public class WebSocketInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        //webSocket基于http协议 需要http的编解码器
        pipeline.addLast(new HttpServerCodec());
        //对写大数据流的之支持
        pipeline.addLast(new ChunkedWriteHandler());
        //添加对http请求和响应的聚合器
        //对HttpMessage进行稽核 聚合成FullHttpRequest 或者FullHttpResponse
        pipeline.addLast(new HttpObjectAggregator(1024*64));
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));

        //添加netty空闲超时检查的支持
        pipeline.addLast(new IdleStateHandler(4,8,12));

        pipeline.addLast(new HeartbeatHandler());

        //添加自定义的handler
        pipeline.addLast(new ChatHandler());

    }
}