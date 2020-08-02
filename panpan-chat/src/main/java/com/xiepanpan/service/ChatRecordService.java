package com.xiepanpan.service;

import com.xiepanpan.entity.TbChatRecord;

import java.util.List;

public interface ChatRecordService {
    void insert(TbChatRecord chatRecord);

    List<TbChatRecord> findByUserIdAndFriendId(String userid, String friendid);

    List<TbChatRecord> findUnreadByUserid(String userid);

    /**
     * 设置消息为已读
     * @param id
     */
    void updateStatusHasRead(String id);
}
