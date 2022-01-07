package com.hcoj.hcoj.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcoj.hcoj.domain.Contests;
import com.hcoj.hcoj.domain.Ctsrank;
import com.hcoj.hcoj.domain.Topic;
import com.hcoj.hcoj.service.ContestsService;
import com.hcoj.hcoj.service.CtsrankService;
import com.hcoj.hcoj.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;


@Controller
@RequestMapping("/contests")
public class ContestsController {

    @Autowired
    private ContestsService contestsService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private CtsrankService ctsrankService;

    //跳转比赛列表展示
    @RequestMapping("/show")
    public String show(Model model){
        return "contests/show";
    }

    //获取比赛列表
    @RequestMapping("/list")
    public String list(Model model,@RequestParam(value = "pn",defaultValue = "1")int pn){
        Page<Contests> page = new Page<>(pn, 5);
        IPage<Contests> iPage=contestsService.SelectPageContests(page);
        if(iPage.getRecords().size()!=0){
            model.addAttribute("iPage",iPage);
        }
        return "contests/list";
    }

    //跳转比赛详情
    @RequestMapping("/overview")
    public String overview(Model model,int cts_id){
        Contests cts=contestsService.findContestsById(cts_id);
        model.addAttribute("cts",cts);
        return "contests/overview";
    }

    //跳转比赛题目
    @RequestMapping("/problem")
    public String problem(Model model,int cts_id){
        List<Topic> lt=topicService.getTopicByCts(cts_id);
        model.addAttribute("lt",lt);
        return "contests/problem";
    }

    //跳转比赛提交记录
    @RequestMapping("/submission")
    public String submission(Model model,int cts_id){
        model.addAttribute("cts_id",cts_id);
        return "contests/submission";
    }

    //查看比赛排行榜
    @RequestMapping("/ctsrank")
    public String ctsrank(Model model,int cts_id,@RequestParam(value = "pn",defaultValue = "1")int pn){
        Page<Ctsrank> page = new Page<>(pn, 10);
        IPage<Ctsrank> iPage=contestsService.selectPageCtsrank(page,cts_id);
        Contests cts=contestsService.findContestsById(cts_id);
        model.addAttribute("cts",cts);
        model.addAttribute("iPage",iPage);
        return "contests/ctsrank";
    }

    //跳转比赛排行榜
    @RequestMapping("/ctsran")
    public String ctsran(Model model){
        return "contests/problem";
    }

    //报名比赛
    @RequestMapping("/signup")
    public void signup(Model model,HttpServletResponse response,String userId,Integer ctsId)throws IOException{
        response.setContentType("text/html;charset=gb2312");
        PrintWriter out = response.getWriter();
        //添加一条比赛排行信息
        Ctsrank ctsrank=new Ctsrank();
        ctsrank.setUserId(userId);
        ctsrank.setCtsId(ctsId);
        ctsrank.setRanSub(0);
        ctsrank.setRanAc(0);
        ctsrank.setRanTotal(0.0);
        ctsrankService.addCtsrank(ctsrank);
        //比赛用户中添加用户id
        Contests contests=contestsService.findContestsById(ctsId);
        contests.setCtsUser(contests.getCtsUser()+userId+',');
        contestsService.updateContests(contests);
        out.print("<script language=\"javascript\">alert('报名成功!');window.location.href='/contests/list'</script>");
    }

}
