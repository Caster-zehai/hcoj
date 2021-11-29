package com.hcoj.hcoj.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcoj.hcoj.domain.Topic;
import com.hcoj.hcoj.domain.TopicContent;
import com.hcoj.hcoj.domain.User;
import com.hcoj.hcoj.service.TopicService;
import com.hcoj.hcoj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    private TopicService topicService;
    @Autowired
    private UserService userService;


    @RequestMapping("/show")
    public String show(Model model){
        return "topic/show";
    }

    @RequestMapping("/list")
    public String list(Model model, @RequestParam(value = "pn",defaultValue = "1")int pn, HttpSession session){
        Page<Topic> page = new Page<>(pn, 10);
        IPage<Topic> iPage=topicService.SelectPageTopic(page,1);
        if(iPage.getRecords().size()!=0){
            model.addAttribute("iPage",iPage);
        }
        //刷新session
        User user = (User) session.getAttribute("USER_SESSION");
        if(user!=null){
            user = userService.ueserlogin(user.getUserId(), user.getUserPwd());
            session.setAttribute("USER_SESSION", user);
        }
        return "topic/list";
    }

    @RequestMapping("/content")
    public String content(Model model,String tpc_id){
        Topic topic=topicService.getTopicById(tpc_id);
        TopicContent topicContent=topicService.getTopicContentById(tpc_id);
        model.addAttribute("topicContent",topicContent);
        model.addAttribute("topic",topic);
        return "topic/content";
    }

    @RequestMapping("/random")
    public String random(Model model){
        String tpcId=topicService.getTopicIdRandom();
        TopicContent topicContent=topicService.getTopicContentById(tpcId);
        Topic topic=topicService.getTopicById(tpcId);
        model.addAttribute("topicContent",topicContent);
        model.addAttribute("topic",topic);
        return "topic/content";
    }
}
