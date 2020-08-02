package com.xiepanpan.controller;

import com.xiepanpan.entity.TbChatRecord;
import com.xiepanpan.service.ChatRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: xiepanpan
 * @Date: 2020/8/2 0002
 * @Description:
 */
@RestController
@RequestMapping("/chatrecord")
public class ChatRecordController {

    @Autowired
    private ChatRecordService chatRecordService;

    /**
     * 根据用户id和好友id将聊天记录查询出来
     * @param userid
     * @param friendid
     * @return
     */
    @RequestMapping("/findByUserIdAndFriendId")
    public List<TbChatRecord> findByUserIdAndFriendId(String userid, String friendid) {
        return chatRecordService.findByUserIdAndFriendId(userid,friendid);
    }

    @RequestMapping("/findUnreadByUserid")
    public List<TbChatRecord> findUnreadByUserid(String userid){
        return chatRecordService.findUnreadByUserid(userid);
    }
}