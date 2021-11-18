package com.hcoj.hcoj.config;

import com.hcoj.hcoj.domain.Contests;
import com.hcoj.hcoj.service.ContestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.List;

/*
* 比赛状态修改定时器
* (每一分钟检查一次)
* */

@Configuration
@EnableScheduling
public class ContestTask {

    @Autowired
    ContestsService contestsService;

    @Scheduled(cron = "0 */1 * * * ?")
    private void compareTime(){
        List<Contests> lc=contestsService.findAllNoEnd();
        if(lc!=null){
            for(Contests ct:lc){
                Integer state=getState(ct.getEnrollBegin(),ct.getEnrollEnd(),ct.getCtsBegin(),ct.getCtsEnd());
                if(ct.getCtsState()!=state){
                    ct.setCtsState(state);
                    contestsService.updateContests(ct);
                }
            }
        }
    }

    public Integer getState(Date date1, Date date2,Date date3,Date date4){
        Integer state=0;
        Date date=new Date();
        if (date.before(date1)) state=0;
        else if(date.before(date2)) state=1;
        else if(date.before(date3)) state=2;
        else if(date.before(date4)) state=3;
        else if(date4.before(date)) state=4;
        return state;
    }

}
