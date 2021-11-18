package com.hcoj.hcoj;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcoj.hcoj.domain.Status;
import com.hcoj.hcoj.domain.Topic;
import com.hcoj.hcoj.mapper.TopicMapper;
import com.hcoj.hcoj.service.ContestsService;
import com.hcoj.hcoj.service.StatusService;
import com.hcoj.hcoj.service.TopicService;
import com.hcoj.hcoj.util.MessageReceiver;
import com.hcoj.hcoj.util.MessageSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Date;
import java.util.List;

@SpringBootTest
class HcojApplicationTests {

    @Autowired
    private StatusService statusService;
    @Autowired
    private MessageSender messageSender;
    @Test
    void contextLoads() {

    }

    @Test
    void sendRedisMsg() {
        Page<Status> page=new Page<>(1,10);
        IPage<Status> iPage=statusService.SelectPageStatus(page,0);
        for(Status status:iPage.getRecords()){
            messageSender.sendMessage(status);
        }
        System.out.println("The message of Redis sent successfully");
    }
}
