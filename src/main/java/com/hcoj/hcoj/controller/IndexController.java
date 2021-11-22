package com.hcoj.hcoj.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcoj.hcoj.domain.Contests;
import com.hcoj.hcoj.service.ContestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @Autowired
    private ContestsService contestsService;

    @RequestMapping(value = {"/index","/"})
    public ModelAndView index(Model model){
        Page<Contests> page = new Page<>(1, 3);
        IPage<Contests> iPage=contestsService.SelectPageContests(page);
        if(iPage.getRecords().size()!=0){
            model.addAttribute("iPage",iPage);
        }
        return new ModelAndView("index");
    }
}
