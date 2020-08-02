package com.xiepanpan.controller;

import com.xiepanpan.entity.TbChatRecord;
import com.xiepanpan.service.TbChatRecordService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (TbChatRecord)表控制层
 *
 * @author makejava
 * @since 2020-08-01 16:11:13
 */
@RestController
@RequestMapping("tbChatRecord")
public class TbChatRecordController {
    /**
     * 服务对象
     */
    @Resource
    private TbChatRecordService tbChatRecordService;



}