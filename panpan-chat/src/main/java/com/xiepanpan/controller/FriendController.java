package com.xiepanpan.controller;

import com.xiepanpan.entity.TbFriendReq;
import com.xiepanpan.entity.vo.FriendReq;
import com.xiepanpan.entity.vo.Result;
import com.xiepanpan.entity.vo.User;
import com.xiepanpan.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * (TbFriend)表控制层
 *
 * @author makejava
 * @since 2020-08-01 16:11:14
 */
@RestController
@RequestMapping("friend")
public class FriendController {
    /**
     * 服务对象
     */
    @Autowired
    private FriendService friendService;

    /**
     * 发送好友请求
     * @param friendReq
     * @return
     */
    @RequestMapping("sendRequest")
    public Result sendRequest(@RequestBody TbFriendReq friendReq) {
        friendService.sendRequest(friendReq.getFromUserid(),friendReq.getToUserid());

        return new Result(true,"已申请");
    }

    @RequestMapping("/findFriendReqByUserid")
    public List<FriendReq> findFriendReqByUserid(String userid) {
        return friendService.findFriendReqByUserid(userid);
    }

    /**
     * 接受好友请求
     * @param reqid
     * @return
     */
    @RequestMapping("/acceptFriendReq")
    public Result acceptFriendReq(String reqid) {
        friendService.acceptFriendReq(reqid);
        return new Result(true,"添加好友成功");
    }

    /**
     * 忽略好友请求
     * @param reqid
     * @return
     */
    @RequestMapping("/ignoreFriendReq")
    public Result ignoreFriendReq(String reqid) {
        friendService.ignoreFriendReq(reqid);
        return new Result(true,"忽略好友请求成功");
    }

    /**
     * 展示通讯录
     * @param userid
     * @return
     */
    @RequestMapping("/findFriendByUserid")
    public List<User> findFriendByUserid(String userid) {
        return friendService.findFriendByUserid(userid);
    }




}