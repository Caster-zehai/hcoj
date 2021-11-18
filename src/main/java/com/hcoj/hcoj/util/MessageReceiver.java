package com.hcoj.hcoj.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/*
*消息接收器
*/
@Slf4j
@Component
public class MessageReceiver {
    /**接收消息的方法*/
    public void receiveMessage(String msg){
        log.info("收到一条消息：{}", msg);
        Map<String, String> map = JSON.parseObject(msg, Map.class);
        String code = map.get("code");
        String id=map.get("runId");
        log.info("id:{}",id);
        log.info("code : {}", code);
    }
}
