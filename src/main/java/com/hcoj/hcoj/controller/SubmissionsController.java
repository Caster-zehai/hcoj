package com.hcoj.hcoj.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcoj.hcoj.domain.Inout;
import com.hcoj.hcoj.domain.Status;
import com.hcoj.hcoj.domain.TopicContent;
import com.hcoj.hcoj.service.InoutService;
import com.hcoj.hcoj.service.StatusService;
import com.hcoj.hcoj.service.TopicService;
import com.hcoj.hcoj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


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

    @RequestMapping("/show")
    public String show(Model model){
        return "submissions/show";
    }

    @RequestMapping("/list")
    public String list(Model model,@RequestParam(value = "pn",defaultValue = "1")int pn){
        Page<Status> page=new Page<>(pn,20);
        IPage<Status> iPage=statusService.SelectPageStatus(page,0);
        if(iPage.getRecords().size()!=0){
            model.addAttribute("iPage",iPage);
        }
        return "submissions/list";
    }

    @RequestMapping("/subjudge")
    public String subjudge(Model model,Status status){
        status.setRunId(statusService.getLastRunId());
        status.setResult(10);
        status.setTime(0);
        status.setMem(0);
        status.setSubmitDate(new Date());
        status.setIsadd(0);
        userService.updateUserSub(status.getUsername());
        topicService.addTopicSub(status.getPno());
        statusService.addSub(status);
        return "redirect:/submissions/seljudge?runId="+status.getRunId();
    }

    @RequestMapping("/seljudge")
    public String seljudge(Model model,String runId){
        Status status =statusService.getMessageById(runId);
        List<Inout> listinout=inoutService.selectInoutById(status.getPno());
        model.addAttribute("listinout",listinout);
        model.addAttribute("status",status);
        return "submissions/submessage";
    }

}