package com.xiepanpan.service;

import com.xiepanpan.entity.TbFriend;
import com.xiepanpan.entity.vo.FriendReq;
import com.xiepanpan.entity.vo.User;

import java.util.List;
import java.util.UUID;

/**
 * (TbFriend)表服务接口
 *
 * @author makejava
 * @since 2020-08-01 16:11:14
 */
public interface FriendService {


    void sendRequest(String fromUserid, String toUserid);

    List<FriendReq> findFriendReqByUserid(String userid);

    void acceptFriendReq(String reqid);

    void ignoreFriendReq(String reqid);

    List<User> findFriendByUserid(String userid);
}