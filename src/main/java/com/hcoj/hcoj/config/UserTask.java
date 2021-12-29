package com.hcoj.hcoj.config;

import com.hcoj.hcoj.domain.Contests;
import com.hcoj.hcoj.domain.Ctsrank;
import com.hcoj.hcoj.domain.Status;
import com.hcoj.hcoj.service.*;
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
    @Autowired
    private ContestsService contestsService;
    @Autowired
    private CtsrankService ctsrankService;

    @Scheduled(cron = "0 */1 * * * ?")
    private void addAcTopic(){
        List<Status> list=statusService.selectAllNotAdd();
        if(list!=null){
            for(Status status:list){
                if(status.getResult()==1) {//如果结果为通过
                    userService.updateUserAc(status.getUsername(), status.getPno());
                    topicService.addTopicAc(status.getPno());
                    totalService.updateac();
                    if(status.getCid()!=0){
                        Contests contests=contestsService.findContestsById(status.getCid());
                        if(contests.getCtsState()==3){
                            Ctsrank ctsrank=ctsrankService.getCtsrankById(status.getUsername(),status.getCid());
                            ctsrank.setRanAc(ctsrank.getRanAc()+1);
                            ctsrank.setRanSub(ctsrank.getRanSub()+1);
                            ctsrank.setRanTotal( ctsrank.getRanAc()/(ctsrank.getRanSub()*1.0) );
                            ctsrankService.updateCtsrank(ctsrank);
                        }
                    }
                }else {
                    if (status.getCid() != 0) {
                        Contests contests = contestsService.findContestsById(status.getCid());
                        if (contests.getCtsState() == 3) {
                            Ctsrank ctsrank = ctsrankService.getCtsrankById(status.getUsername(), status.getCid());
                            ctsrank.setRanSub(ctsrank.getRanSub() + 1);
                            ctsrank.setRanTotal( ctsrank.getRanAc() / (ctsrank.getRanSub() * 1.0) );
                            ctsrankService.updateCtsrank(ctsrank);
                        }
                    }
                }
                statusService.updateIsadd(status.getRunId());
                totalService.updateresult(status.getResult());
            }
        }
    }
}
