package com.xiepanpan.service.impl;

import com.xiepanpan.entity.TbUser;
import com.xiepanpan.entity.TbUserExample;
import com.xiepanpan.entity.vo.User;
import com.xiepanpan.mapper.TbUserMapper;
import com.xiepanpan.service.UserService;
import com.xiepanpan.utils.IdWorker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (TbUser)表服务实现类
 *
 * @author makejava
 * @since 2020-08-01 16:11:14
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private TbUserMapper userMapper;

    @Autowired
    private IdWorker idWorker;


    @Override
    public User login(String username, String password) {
        if (StringUtils.isNotBlank(username)&&StringUtils.isNotBlank(password)) {
            TbUserExample example = new TbUserExample();
            TbUserExample.Criteria criteria = example.createCriteria();
            criteria.andUsernameEqualTo(username);

            List<TbUser> userList = userMapper.selectByExample(example);
            if (userList!=null && userList.size()==1) {
                String encodingPassoword = DigestUtils.md5DigestAsHex(password.getBytes());
                if (encodingPassoword.equals(userList.get(0).getPassword())) {
                    User user = new User();
                    BeanUtils.copyProperties(userList.get(0),user);
                    return user;
                }
            }

        }
        return null;
    }

    @Override
    public void register(TbUser user) {

        //判断这个用户名是否存在
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(user.getUsername());
        List<TbUser> userList = userMapper.selectByExample(example);
        if (userList!=null && userList.size()>0) {
            throw new RuntimeException("用户已存在");
        }

        //雪花算法生成唯一Id
        user.setId(idWorker.nextId());
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        user.setPicNormal("");
        user.setPicSmall("");
        user.setNickname(user.getUsername());


        //生成图片

        user.setCreatetime(new Date());
        userMapper.insert(user);
        //插入数据

    }

    @Override
    public User findByUsername(String userid, String friendUsername) {
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();

        criteria.andUsernameEqualTo(friendUsername);

        List<TbUser> userList = userMapper.selectByExample(example);

        User friendUser = new User();
        if (userList!=null&&userList.size()>0) {
            TbUser friend = userList.get(0);


            BeanUtils.copyProperties(friend,friendUser);
            return friendUser;
        }
        return null;

    }
}