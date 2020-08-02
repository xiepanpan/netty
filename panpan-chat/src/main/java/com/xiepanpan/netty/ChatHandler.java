package com.xiepanpan.netty;

import com.alibaba.fastjson.JSON;
import com.xiepanpan.entity.TbChatRecord;
import com.xiepanpan.service.ChatRecordService;
import com.xiepanpan.service.impl.ChatRecordServiceImpl;
import com.xiepanpan.utils.SpringUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;

/**
 * @author: xiepanpan
 * @Date: 2020/8/1 0001
 * @Description:  处理消息的handler
 * TextWebSocketFrame: 在netty中 是用于为webSocket专门处理文本的对象 frame是消息载体
 *
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    //用来保存所有的客户端连接
    private static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:MM");

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        String text = textWebSocketFrame.text();
        System.out.println("接收到消息数据为："+text);

        Message message = JSON.parseObject(text, Message.class);

        ChatRecordService chatRecordService = SpringUtil.getBean(ChatRecordService.class);

        switch (message.getType()) {
            case 0:
                String userid = message.getChatRecord().getUserid();
                UserChannelMap.put(userid,channelHandlerContext.channel());
                System.out.println("建立用户："+userid+"与通道"+channelHandlerContext.channel().id()+"的关联");
                UserChannelMap.print();
                break;
            case 1:
                System.out.println("接收到用户消息");
                TbChatRecord chatRecord = message.getChatRecord();
                chatRecordService.insert(chatRecord);

                //如果发送消息好友在线 可以直接将消息发送给好友
                Channel channel = UserChannelMap.get(chatRecord.getFriendid());
                if (channel!=null) {
                    channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(message)));
                }else {
                    System.out.println("用户"+chatRecord.getFriendid()+"不在线");
                }
                break;
            case 2:
                chatRecordService.updateStatusHasRead(message.getChatRecord().getId());
            case 3:
//                System.out.println("接收到心跳消息："+JSON.toJSONString(message));
                break;
        }
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //将新的通道追加到clients中
        clients.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("关闭通道");
        UserChannelMap.removeByChannelId(ctx.channel().id().asLongText());
        UserChannelMap.print();

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        UserChannelMap.removeByChannelId(ctx.channel().id().asLongText());
        ctx.channel().close();
    }
}