package com.xiepanpan.service.impl;

import com.xiepanpan.entity.*;
import com.xiepanpan.entity.vo.FriendReq;
import com.xiepanpan.entity.vo.User;
import com.xiepanpan.mapper.TbFriendMapper;
import com.xiepanpan.mapper.TbFriendReqMapper;
import com.xiepanpan.mapper.TbUserMapper;
import com.xiepanpan.service.FriendService;
import com.xiepanpan.utils.IdWorker;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * (TbFriend)表服务实现类
 *
 * @author makejava
 * @since 2020-08-01 16:11:14
 */
@Service("tbFriendService")
public class FriendServiceImpl implements FriendService {
    @Resource
    private TbFriendMapper friendMapper;
    @Autowired
    private TbUserMapper userMapper;
    @Autowired
    private TbFriendReqMapper friendReqMapper;
    @Autowired
    private IdWorker idWorker;


    @Override
    public void sendRequest(String fromUserid, String toUserid) {
        //检查是否允许添加好友
        TbUser friend = userMapper.selectByPrimaryKey(toUserid);
        checkAllowToAddFriend(fromUserid,friend);
        //添加好友请求
        TbFriendReq friendReq = new TbFriendReq();
        friendReq.setId(idWorker.nextId());
        friendReq.setFromUserid(fromUserid);
        friendReq.setToUserid(toUserid);
        friendReq.setCreatetime(new Date());
        friendReq.setStatus(0);

        friendReqMapper.insert(friendReq);
    }

    @Override
    public List<FriendReq> findFriendReqByUserid(String userid) {
        TbFriendReqExample friendReqExample = new TbFriendReqExample();
        TbFriendReqExample.Criteria criteria = friendReqExample.createCriteria();
        criteria.andToUseridEqualTo(userid);
        criteria.andStatusEqualTo(0);

        List<TbFriendReq> tbFriendReqList = friendReqMapper.selectByExample(friendReqExample);
        List<FriendReq> friendReqList = new ArrayList<>();

        for (TbFriendReq tbFriendReq :tbFriendReqList) {
            TbUser tbUser = userMapper.selectByPrimaryKey(tbFriendReq.getFromUserid());
            FriendReq friendReq = new FriendReq();
            BeanUtils.copyProperties(tbUser,friendReq);
            friendReq.setId(tbFriendReq.getId());
            friendReqList.add(friendReq);
        }

        return friendReqList;
    }

    @Override
    public void acceptFriendReq(String reqid) {

        //将好友请求的status标志设置为1 表示已经处理了该好友请求
        TbFriendReq friendReq = friendReqMapper.selectByPrimaryKey(reqid);
        friendReq.setStatus(1);

        friendReqMapper.updateByPrimaryKey(friendReq);

        //互相添加好友 在tb_friend表中应该添加两条记录
        TbFriend friend1 = new TbFriend();
        friend1.setId(idWorker.nextId());
        friend1.setUserid(friendReq.getFromUserid());
        friend1.setFriendsId(friendReq.getToUserid());
        friend1.setCreatetime(new Date());

        TbFriend friend2 = new TbFriend();
        friend2.setId(idWorker.nextId());
        friend2.setUserid(friendReq.getToUserid());
        friend2.setFriendsId(friendReq.getFromUserid());
        friend2.setCreatetime(new Date());

        friendMapper.insert(friend1);
        friendMapper.insert(friend2);
    }

    @Override
    public void ignoreFriendReq(String reqid) {
        TbFriendReq tbFriendReq = friendReqMapper.selectByPrimaryKey(reqid);
        tbFriendReq.setStatus(1);

        friendReqMapper.updateByPrimaryKey(tbFriendReq);
    }

    @Override
    public List<User> findFriendByUserid(String userid) {
        TbFriendExample friendExample = new TbFriendExample();
        TbFriendExample.Criteria friendExampleCriteria = friendExample.createCriteria();
        friendExampleCriteria.andUseridEqualTo(userid);

        List<TbFriend> tbFriendList = friendMapper.selectByExample(friendExample);

        List<User> friendList = new ArrayList<>();
        for (TbFriend tbFriend: tbFriendList) {
            TbUser tbUser = userMapper.selectByPrimaryKey(tbFriend.getFriendsId());
            User friend = new User();
            BeanUtils.copyProperties(tbUser,friend);

            friendList.add(friend);
        }
        return friendList;
    }

    private void checkAllowToAddFriend(String fromUserid, TbUser friend) {

        //用户不能添加自己为好友
        if (friend.getId().equals(fromUserid)) {
            throw new RuntimeException("不能添加自己为好友");
        }
        //用户不能重复添加
        //如果用户已经添加该好友 就不能再次添加
        TbFriendExample friendExample = new TbFriendExample();
        TbFriendExample.Criteria friendExampleCriteria = friendExample.createCriteria();
        friendExampleCriteria.andUseridEqualTo(fromUserid);
        friendExampleCriteria.andFriendsIdEqualTo(friend.getId());
        List<TbFriend> friendList = friendMapper.selectByExample(friendExample);
        if (friendList!=null&& friendList.size()>0) {
            throw new RuntimeException(friend.getUsername()+"已经是您的好友了");
        }

        //判断是否已提交好友申请 如果已经提交 直接抛出运行时异常
        TbFriendReqExample friendReqExample = new TbFriendReqExample();
        TbFriendReqExample.Criteria friendReqCritieria = friendReqExample.createCriteria();

        friendReqCritieria.andFromUseridEqualTo(fromUserid);
        friendReqCritieria.andToUseridEqualTo(friend.getId());

        //而且这个请求时没有被处理的
        friendReqCritieria.andStatusEqualTo(0);

        List<TbFriendReq> friendReqList = friendReqMapper.selectByExample(friendReqExample);

        if (friendReqList!=null&& friendReqList.size()>0) {
            throw new RuntimeException("已经申请过了");
        }



    }
}