package com.xiepanpan.service.impl;

import com.xiepanpan.mapper.TbFriendReqMapper;
import com.xiepanpan.service.FriendReqService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (TbFriendReq)表服务实现类
 *
 * @author makejava
 * @since 2020-08-01 16:11:14
 */
@Service("tbFriendReqService")
public class TbFriendReqServiceImpl implements FriendReqService {
    @Resource
    private TbFriendReqMapper tbFriendReqDao;

}