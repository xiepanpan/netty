package com.xiepanpan.service;

import com.xiepanpan.entity.TbUser;
import com.xiepanpan.entity.vo.User;

import java.util.List;

/**
 * (TbUser)表服务接口
 *
 * @author makejava
 * @since 2020-08-01 16:11:14
 */
public interface UserService {


    User login(String username, String password);

    void register(TbUser user);

    User findByUsername(String userid, String friendUsername);
}