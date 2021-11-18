package com.hcoj.hcoj.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcoj.hcoj.domain.Topic;
import com.hcoj.hcoj.domain.User;
import com.hcoj.hcoj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/rank")
public class RankController {

    @Autowired
    private UserService userService;

    @RequestMapping("/show")
    public String show(Model model){
        return "rank/show";
    }

    @RequestMapping("/list")
    public String list(Model model,@RequestParam(value = "pn", defaultValue = "1")int pn){
        Page<User> page = new Page<>(pn, 10);
        IPage<User> iPage=userService.SelectPageUser(page);
        if(iPage.getRecords().size()!=0){
            model.addAttribute("iPage",iPage);
        }
        return "rank/list";
    }

}
