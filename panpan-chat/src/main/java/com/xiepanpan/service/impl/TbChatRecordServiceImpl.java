package com.xiepanpan.service.impl;

import com.xiepanpan.entity.TbChatRecord;
import com.xiepanpan.mapper.TbChatRecordMapper;
import com.xiepanpan.service.TbChatRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TbChatRecord)表服务实现类
 *
 * @author makejava
 * @since 2020-08-01 16:11:12
 */
@Service("tbChatRecordService")
public class TbChatRecordServiceImpl implements TbChatRecordService {
    @Resource
    private TbChatRecordMapper tbChatRecordDao;

}