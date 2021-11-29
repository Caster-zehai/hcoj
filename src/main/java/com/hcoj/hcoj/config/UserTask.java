package com.hcoj.hcoj.config;

import com.hcoj.hcoj.domain.Status;
import com.hcoj.hcoj.service.StatusService;
import com.hcoj.hcoj.service.TopicService;
import com.hcoj.hcoj.service.TotalService;
import com.hcoj.hcoj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/*
 * 用户提交状态定时器
 * (每一分钟检查一次)
 * */

@Configuration
@EnableScheduling
public class UserTask {
    @Autowired
    private StatusService statusService;
    @Autowired
    private UserService userService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private TotalService totalService;

    @Scheduled(cron = "0 */1 * * * ?")
    private void addAcTopic(){
        List<Status> list=statusService.selectAllNotAdd();
        if(list!=null){
            for(Status status:list){
                if(status.getResult()==1) {
                    userService.updateUserAc(status.getUsername(), status.getPno());
                    topicService.addTopicAc(status.getPno());
                    totalService.updateac();
                }
                statusService.updateIsadd(status.getRunId());
                totalService.updateresult(status.getResult());
            }
        }
    }
}
