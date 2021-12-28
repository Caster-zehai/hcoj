package com.hcoj.hcoj.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcoj.hcoj.domain.Inout;
import com.hcoj.hcoj.domain.Status;
import com.hcoj.hcoj.domain.TopicContent;
import com.hcoj.hcoj.domain.User;
import com.hcoj.hcoj.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/submissions")
public class SubmissionsController {
    @Autowired
    private StatusService statusService;
    @Autowired
    private InoutService inoutService;
    @Autowired
    private UserService userService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private TotalService totalService;

    @RequestMapping("/show")
    public String show(Model model){
        return "submissions/show";
    }

    @RequestMapping("/list")
    public String list(Model model,@RequestParam(value = "pn",defaultValue = "1")int pn,@RequestParam(value = "cts_id",defaultValue = "0")int cts_id,String userId,String tpcId){
        Page<Status> page=new Page<>(pn,20);
        IPage<Status> iPage=null;
        if((userId!=null&&userId!="")&&(tpcId!=null&&tpcId!=""))
            iPage=statusService.SelectPageStatus(page,cts_id,userId,tpcId);
        else if((userId!=null&&userId!="")&&(tpcId==null||tpcId==""))
            iPage=statusService.SelectPageStatusByuser(page,cts_id,userId);
        else if((userId==null||userId=="")&&(tpcId!=null&&tpcId!=""))
            iPage=statusService.SelectPageStatusBytpc(page,cts_id,tpcId);
        else
            iPage=statusService.SelectPageStatus(page,cts_id);
        if(iPage.getRecords().size()!=0){
            model.addAttribute("iPage",iPage);
        }
        return "submissions/list";
    }


    @RequestMapping("/subjudge")
    public String subjudge(Model model,Status status,@RequestParam(value = "cts_id",defaultValue = "0")int cts_id){
        status.setRunId(statusService.getLastRunId());
        status.setCid(cts_id);
        status.setResult(10);
        status.setTime(0);
        status.setMem(0);
        status.setSubmitDate(new Date());
        status.setIsadd(0);
        userService.updateUserSub(status.getUsername());
        topicService.addTopicSub(status.getPno());
        statusService.addSub(status);
        totalService.updatesub();
        return "redirect:/submissions/seljudge?runId="+status.getRunId();
    }

    @RequestMapping("/seljudge")
    public String seljudge(Model model, String runId, HttpSession session){
        Status status =statusService.getMessageById(runId);
        List<Inout> listinout=inoutService.selectInoutById(status.getPno());
        model.addAttribute("listinout",listinout);
        model.addAttribute("status",status);
        return "submissions/submessage";
    }

}
