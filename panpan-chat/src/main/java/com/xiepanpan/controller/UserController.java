package com.xiepanpan.controller;

import com.xiepanpan.entity.TbUser;
import com.xiepanpan.entity.vo.Result;
import com.xiepanpan.entity.vo.User;
import com.xiepanpan.exception.BizException;
import com.xiepanpan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (TbUser)表控制层
 *
 * @author makejava
 * @since 2020-08-01 16:11:14
 */
@RestController
@RequestMapping("/user")
public class UserController {
    /**
     * 服务对象
     */
    @Autowired
    private UserService userService;


    @RequestMapping("/login")
    public Result login(@RequestBody TbUser user) {
        User user1 = userService.login(user.getUsername(), user.getPassword());
        if (user1 == null) {
            return new Result(false,"登录失败，请检查用户名或密码是否正确");
        }else {
            return new Result(true,"登录成功",user1);
        }
    }
    @RequestMapping("/register")
    public Result register(@RequestBody TbUser user) {
        userService.register(user);
        return new Result(true,"注册成功");
    }

    @RequestMapping("/findByUsername")
    public Result findByUsername(String userid,String friendUsername) {
        User user = userService.findByUsername(userid, friendUsername);
        if (user!=null) {
            return new Result(true,"搜索成功",user);
        }else {
            return new Result(false,"没有找到用户");
        }
    }

}