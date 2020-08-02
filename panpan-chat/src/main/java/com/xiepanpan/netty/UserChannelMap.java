package com.xiepanpan.netty;

import io.netty.channel.Channel;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xiepanpan
 * @Date: 2020/8/2 0002
 * @Description:  建立用户id与通道的关联
 */
public class UserChannelMap {

    private static Map<String, Channel> userChannelMap;

    static {
        userChannelMap = new HashMap<String, Channel>();
    }

    /**
     * 添加用户id与channel的关联
     * @param userid
     * @param channel
     */
    public static void put(String userid,Channel channel) {
        userChannelMap.put(userid,channel);
    }

    /**
     * 打印所有的用户与通道的关联数据
     */
    public static void print() {
        for (String s: userChannelMap.keySet()) {
            System.out.println("用户id："+s+"通道"+userChannelMap.get(s).id());
        }
    }

    /**
     * 根据通道id移除用户与channel的关联
     * @param channelId
     */
    public static void removeByChannelId(String channelId) {
        if(!StringUtils.isNotBlank(channelId)) {
            return;
        }

        for (String s: userChannelMap.keySet()) {
            Channel channel = userChannelMap.get(s);
            if (channelId.equals(channel.id().asLongText())) {
                System.out.println("客户端连接断开，取消用户"+s+"与通道"+channelId+"的关联");
                userChannelMap.remove(s);
                break;
            }
        }
    }

    /**
     * 根据好友id获取对应的通道 没有说明没在线
     * @param friendid
     * @return
     */
    public static Channel get(String friendid) {
        return userChannelMap.get(friendid);
    }
}