package com.hcoj.hcoj.util;

import com.alibaba.fastjson.JSON;
import com.hcoj.hcoj.domain.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/*
*消息发送器
* */
@Slf4j
@Component
public class MessageSender {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    public void sendMessage(Status status){
        String msg = JSON.toJSONString(status);
        log.info("转发消息 : {}", msg);
        stringRedisTemplate.convertAndSend("chat",msg);
    }
}
