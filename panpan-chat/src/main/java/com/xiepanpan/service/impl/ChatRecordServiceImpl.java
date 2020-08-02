package com.xiepanpan.service.impl;

import com.xiepanpan.entity.TbChatRecord;
import com.xiepanpan.entity.TbChatRecordExample;
import com.xiepanpan.mapper.TbChatRecordMapper;
import com.xiepanpan.service.ChatRecordService;
import com.xiepanpan.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author: xiepanpan
 * @Date: 2020/8/2 0002
 * @Description:
 */
@Service
@Transactional
public class ChatRecordServiceImpl implements ChatRecordService {

    @Autowired
    private IdWorker idWorker;
    @Autowired
    private TbChatRecordMapper chatRecordMapper;


    @Override
    public void insert(TbChatRecord chatRecord) {
        chatRecord.setId(idWorker.nextId());
        chatRecord.setHasRead(0);
        chatRecord.setCreatetime(new Date());
        chatRecord.setHasDelete(0);
        chatRecordMapper.insert(chatRecord);
    }

    @Override
    public List<TbChatRecord> findByUserIdAndFriendId(String userid, String friendid) {
        TbChatRecordExample example = new TbChatRecordExample();
        TbChatRecordExample.Criteria criteria1 = example.createCriteria();
        TbChatRecordExample.Criteria criteria2 = example.createCriteria();

        criteria1.andUseridEqualTo(userid);
        criteria1.andFriendidEqualTo(friendid);
        criteria1.andHasDeleteEqualTo(0);

        criteria2.andUseridEqualTo(friendid);
        criteria2.andFriendidEqualTo(userid);
        criteria2.andHasDeleteEqualTo(0);

        example.or(criteria1);
        example.or(criteria2);

        //将发给userid的所有聊天记录设置为已读
        TbChatRecordExample exampleQuerySendToMe = new TbChatRecordExample();
        TbChatRecordExample.Criteria criteria = exampleQuerySendToMe.createCriteria();

        criteria.andFriendidEqualTo(userid);
        criteria.andHasReadEqualTo(0);

        List<TbChatRecord> tbChatRecords = chatRecordMapper.selectByExample(exampleQuerySendToMe);

        for (TbChatRecord tbChatRecord: tbChatRecords) {
            tbChatRecord.setHasRead(1);
            chatRecordMapper.updateByPrimaryKey(tbChatRecord);
        }


        return chatRecordMapper.selectByExample(example);
    }

    @Override
    public List<TbChatRecord> findUnreadByUserid(String userid) {
        TbChatRecordExample tbChatRecordExample = new TbChatRecordExample();
        TbChatRecordExample.Criteria criteria = tbChatRecordExample.createCriteria();

        criteria.andFriendidEqualTo(userid);
        criteria.andHasReadEqualTo(0);
        return chatRecordMapper.selectByExample(tbChatRecordExample);
    }

    @Override
    public void updateStatusHasRead(String id) {
        TbChatRecord tbChatRecord = chatRecordMapper.selectByPrimaryKey(id);
        tbChatRecord.setHasRead(1);

        chatRecordMapper.updateByPrimaryKey(tbChatRecord);
    }
}