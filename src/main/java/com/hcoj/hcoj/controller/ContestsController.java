package com.hcoj.hcoj.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcoj.hcoj.domain.Contests;
import com.hcoj.hcoj.domain.Topic;
import com.hcoj.hcoj.service.ContestsService;
import com.hcoj.hcoj.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.List;


@Controller
@RequestMapping("/contests")
public class ContestsController {

    @Autowired
    private ContestsService contestsService;
    @Autowired
    private TopicService topicService;

    @RequestMapping("/show")
    public String show(Model model){
        return "contests/show";
    }

    @RequestMapping("/list")
    public String list(Model model,@RequestParam(value = "pn",defaultValue = "1")int pn){
        Page<Contests> page = new Page<>(pn, 5);
        IPage<Contests> iPage=contestsService.SelectPageContests(page);
        if(iPage.getRecords().size()!=0){
            model.addAttribute("iPage",iPage);
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        model.addAttribute("dateFormat",dateFormat);
        return "contests/list";
    }

    @RequestMapping("/overview")
    public String overview(Model model,int cts_id){
        Contests cts=contestsService.findContestsById(cts_id);
        model.addAttribute("cts",cts);
        return "contests/overview";
    }

    @RequestMapping("/problem")
    public String problem(Model model,int cts_id){
        List<Topic> lt=topicService.getTopicByCts(cts_id);
        model.addAttribute("lt",lt);
        return "contests/problem";
    }
}
