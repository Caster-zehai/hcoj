package com.hcoj.hcoj.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcoj.hcoj.domain.*;
import com.hcoj.hcoj.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/manage")
public class ManageController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private TotalService totalService;
    @Autowired
    private ContestsService contestsService;
    @Autowired
    private InoutService inoutService;

    @RequestMapping("/login")
    public String login(Model model){
        return "manage/login";
    }

    @RequestMapping(value = {"/index","/"})
    public String index(HttpSession session,Model model){
        Admin admin = (Admin) session.getAttribute("ADMIN_SESSION");
        if (admin==null)
            return "manage/login";
        else{
            Total total=totalService.selectTotal();
            model.addAttribute("total",total);
            return "manage/index";
        }
    }

    @RequestMapping("/mould")
    public String mould(){
        return "manage/mould";
    }

    //退出登录
    @RequestMapping("/exitlogin")
    public String exitlogin(Model model,HttpSession session){
        session.setAttribute("ADMIN_SESSION",null);
        return "manage/login";
    }

    //提交登录
    @RequestMapping("/sublogin")
    public String sublogin(HttpSession session, HttpServletResponse response,Integer admId,String admPwd)throws IOException {
        response.setContentType("text/html;charset=gb2312");
        Admin admin=adminService.adminLogin(admId,admPwd);
        PrintWriter out = response.getWriter();
        if(admin!=null){
            session.setAttribute("ADMIN_SESSION",admin);
            return "redirect:/manage/index";
        }else{
            out.print("<script language=\"javascript\">alert('登录失败！请检查用户名、密码是否正确。');window.location.href='/manage/login'</script>");
            return "manage/login";
        }
    }

    /*
     *
     * list列表展示
     *
     * */

    //查看用户
    @RequestMapping("/userlist")
    public String userlist(Model model,@RequestParam(value = "pn", defaultValue = "1")int pn){
        Page<User> page = new Page<>(pn, 13);
        IPage<User> iPage=userService.SelectPageUser(page);
        if(iPage.getRecords().size()!=0){
            model.addAttribute("iPage",iPage);
        }
        return "manage/user/userList";
    }

    @RequestMapping("/topiclist")
    public String topiclist(Model model,@RequestParam(value = "pn",defaultValue = "1")int pn){
        Page<Topic> page = new Page<>(pn, 13);
        IPage<Topic> iPage=topicService.SelectPageAllTopic(page);
        if(iPage.getRecords().size()!=0){
            model.addAttribute("iPage",iPage);
        }
        return "manage/topic/topicList";
    }


    @RequestMapping("/contestslist")
    public String contestslist(Model model,@RequestParam(value = "pn",defaultValue = "1")int pn){
        Page<Contests> page = new Page<>(pn, 13);
        IPage<Contests> iPage=contestsService.SelectPageContests(page);
        if(iPage.getRecords().size()!=0){
            model.addAttribute("iPage",iPage);
        }
        return "manage/contests/contestsList";
    }


    /*
     *
     * 用户管理操作
     *
     * */

    //跳转编辑用户页面
    @RequestMapping("useredit")
    public String useredit(Model model,String userId){
        if(userId!=null&&userId!=""){
            User user=userService.selectUserById(userId);
            model.addAttribute("user",user);
        }
        return "manage/user/userEdit";
    }

    //执行编辑用户
    @RequestMapping("/subuseredit")
    public void subuseredit(User user,HttpServletResponse response)throws IOException{
        response.setContentType("text/html;charset=gb2312");
        PrintWriter out = response.getWriter();
        if(userService.updateUser(user)){
            out.print("<script language=\"javascript\">alert('编辑成功!');window.location.href='/manage/useredit'</script>");
        }else
            out.print("<script language=\"javascript\">alert('编辑失败!');window.location.href='/manage/useredit'</script>");
    }

    //执行删除用户
    @RequestMapping("/deluser")
    public void deluser(String userId,HttpServletResponse response)throws IOException{
        response.setContentType("text/html;charset=gb2312");
        PrintWriter out = response.getWriter();
        if(userService.delUser(userId)) {
            out.print("<script language=\"javascript\">alert('删除成功!');window.location.href='/manage/userlist'</script>");
            totalService.userTotalDel();
        }else
            out.print("<script language=\"javascript\">alert('删除失败!');window.location.href='/manage/userlist'</script>");
    }


    /*
    *
    * 题目管理操作
    *
    * */


    //跳转添加题目
    @RequestMapping("/topicadd")
    public String topicadd(Model model){
        return "manage/topic/topicAdd";
    }

    //跳转编辑题目
    @RequestMapping("/topicedit")
    public String topicedit(Model model,String tpcId){
        if(tpcId!=null&&tpcId!=""){
            Topic topic=topicService.getTopicById(tpcId);
            TopicContent topicContent=topicService.getTopicContentById(tpcId);
            model.addAttribute("topic",topic);
            model.addAttribute("tpccont",topicContent);
        }
        return "manage/topic/topicEdit";
    }

    //执行编辑题目
    @RequestMapping("/subtopicedit")
    public void subtopicedit(HttpServletResponse response,Topic topic,TopicContent topicContent)throws IOException{
        response.setContentType("text/html;charset=gb2312");
        PrintWriter out = response.getWriter();
        if(topicService.editTopic(topic,topicContent)){
            out.print("<script language=\"javascript\">alert('编辑成功!');window.location.href='/manage/topicedit'</script>");
        }else
            out.print("<script language=\"javascript\">alert('编辑失败!');window.location.href='/manage/topicedit'</script>");
    }

    //执行添加题目
    @RequestMapping("/subtopicadd")
    public String subtopicadd(Model model,HttpServletResponse response,Topic topic,TopicContent topicContent)throws IOException{
        response.setContentType("text/html;charset=gb2312");
        PrintWriter out = response.getWriter();
        if(topicService.addTopic(topic,topicContent)) {
            out.print("<script language=\"javascript\">alert('添加成功!');window.location.href='/manage/topicadd'</script>");
            totalService.topicTotalAdd(topic.getTpcDiff());
            //更新输入输出表内容
            List<Inout> list=new ArrayList<Inout>();
            if(topicContent.getOut1()!=null){
                Inout inout=new Inout();
                inout.setTpcId(topicContent.getTpcId());inout.setDatain(topicContent.getIn1());inout.setDataout(topicContent.getOut1());
                list.add(inout);
            }
            if(topicContent.getOut2()!=null){
                Inout inout=new Inout();
                inout.setTpcId(topicContent.getTpcId());inout.setDatain(topicContent.getIn2());inout.setDataout(topicContent.getOut2());
            list.add(inout);
            }
            if(topicContent.getOut3()!=null){
                Inout inout=new Inout();
                inout.setTpcId(topicContent.getTpcId());inout.setDatain(topicContent.getIn3());inout.setDataout(topicContent.getOut3());
                list.add(inout);
            }
            if(topicContent.getOut4()!=null){
                Inout inout=new Inout();
                inout.setTpcId(topicContent.getTpcId());inout.setDatain(topicContent.getIn4());inout.setDataout(topicContent.getOut4());
                list.add(inout);
            }
            if(topicContent.getOut5()!=null){
                Inout inout=new Inout();
                inout.setTpcId(topicContent.getTpcId());inout.setDatain(topicContent.getIn5());inout.setDataout(topicContent.getOut5());
                list.add(inout);
            }
            inoutService.insertInoutDate(list);
        }else
            out.print("<script language=\"javascript\">alert('添加失败!');window.location.href='/manage/topicadd'</script>");
        return "manage/topic/topicAdd";
    }

    //执行删除题目
    @RequestMapping("/topicdel")
    public void topicdel(String tpcId,String tpcDiff,HttpServletResponse response)throws IOException{
        response.setContentType("text/html;charset=gb2312");
        PrintWriter out = response.getWriter();
        if(topicService.delTopic(tpcId)){
            out.print("<script language=\"javascript\">alert('删除成功!');window.location.href='/manage/topiclist'</script>");
            totalService.topicTotalDel(tpcDiff);
            inoutService.deleteByTpcId(tpcId);
        }
        else
            out.print("<script language=\"javascript\">alert('删除失败!');window.location.href='/manage/topiclist'</script>");
    }


    /*
     *
     * 比赛管理操作
     *
     * */

    //跳转添加比赛操作
    @RequestMapping("/contestsadd")
    public String contestsadd(){
        return "manage/contests/contestsAdd";
    }

    @RequestMapping("/contestsedit")
    public String contestsedit(Model model,Integer ctsId){
        if(ctsId!=null){
            Contests contests=contestsService.findContestsById(ctsId);
            //Date转换localdatetime类型
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String ctsBegin1=simpleDateFormat.format(contests.getCtsBegin()).replaceAll(" ", "T");
            String ctsEnd1=simpleDateFormat.format(contests.getCtsEnd()).replaceAll(" ", "T");
            String enrollBegin1=simpleDateFormat.format(contests.getEnrollBegin()).replaceAll(" ", "T");
            String enrollEnd1=simpleDateFormat.format(contests.getEnrollEnd()).replaceAll(" ", "T");
            model.addAttribute("contests",contests);
            model.addAttribute("ctsBegin1",ctsBegin1);
            model.addAttribute("ctsEnd1",ctsEnd1);
            model.addAttribute("enrollBegin1",enrollBegin1);
            model.addAttribute("enrollEnd1",enrollEnd1);
        }
        return "manage/contests/contestsEdit";
    }

    //跳转题目绑定
    @RequestMapping("/contestscon")
    public String contestscon(Model model){
        List<Contests> lc=contestsService.findAllNoEnd();
        List<Topic> lt=topicService.SelectTopicNoOpen();
        model.addAttribute("lc",lc);
        model.addAttribute("lt",lt);
        return "manage/contests/contestsCon";
    }

    //提交题目绑定
    @RequestMapping("/subcontestscon")
    public void subcontestscon(String ctsId,String[] tpcId1,HttpServletResponse response)throws IOException{
        response.setContentType("text/html;charset=gb2312");
        PrintWriter out = response.getWriter();
        //提取题目id与比赛id
        Integer ctsid=Integer.parseInt(ctsId.substring(0,ctsId.indexOf("#")));
        int length=tpcId1.length;
        String[] tpcid=new String[length];
        int i=0;
        for(String tpc:tpcId1){
            tpcid[i]=tpc.substring(0,tpc.indexOf("#"));
            i++;
        }
        //清除比赛原绑定题目
        List<Topic> lt=topicService.getTopicByCts(ctsid);
        if(!lt.isEmpty()) topicService.delTopicCts(lt);
        if(topicService.conTopicCts(tpcid,ctsid)) {
            //更新比赛绑定题目状态
            Contests contests=contestsService.findContestsById(ctsid);
            contests.setCtsTopic("已绑定");
            contestsService.updateContests(contests);
            out.print("<script language=\"javascript\">alert('绑定成功!');window.location.href='/manage/contestscon'</script>");
        }
        else
            out.print("<script language=\"javascript\">alert('绑定失败!');window.location.href='/manage/contestscon'</script>");
    }

    //提交删除比赛操作
    @RequestMapping("/contestsdel")
    public void contestsdel(Integer ctsId,HttpServletResponse response)throws IOException{
        response.setContentType("text/html;charset=gb2312");
        PrintWriter out = response.getWriter();
        //先清除题目中绑定的比赛再删除比赛
        List<Topic> lt=topicService.getTopicByCts(ctsId);
        if(!lt.isEmpty()) topicService.delTopicCts(lt);
        if(contestsService.delContests(ctsId)){
            out.print("<script language=\"javascript\">alert('删除成功!');window.location.href='/manage/contestslist'</script>");
        }
        else
            out.print("<script language=\"javascript\">alert('删除失败!');window.location.href='/manage/contestslist'</script>");
    }

    //提交添加比赛操作
    @RequestMapping("/subcontestsadd")
    public void subcontestsadd(Contests contests,HttpServletResponse response,String ctsBegin1,String ctsEnd1,String enrollBegin1,String enrollEnd1)throws Exception{
        contests=changeTime(contests,ctsBegin1,ctsEnd1,enrollBegin1,enrollEnd1);
        //state获取
        contests.setCtsState(getState(contests.getEnrollBegin(),contests.getEnrollEnd(),contests.getCtsBegin(),contests.getCtsEnd()));
        contests.setCtsTopic("未绑定");
        response.setContentType("text/html;charset=gb2312");
        PrintWriter out = response.getWriter();
        if(contestsService.addContests(contests)){
            out.print("<script language=\"javascript\">alert('添加成功!');window.location.href='/manage/contestsadd'</script>");
        }else {
            out.print("<script language=\"javascript\">alert('添加失败!');window.location.href='/manage/contestsadd'</script>");
        }
    }

    @RequestMapping("/subcontestsedit")
    public void subcontstsedit(Contests contests,HttpServletResponse response,String ctsBegin1,String ctsEnd1,String enrollBegin1,String enrollEnd1)throws Exception{
        Contests cts=contestsService.findContestsById(contests.getCtsId());
        contests=changeTime(contests,ctsBegin1,ctsEnd1,enrollBegin1,enrollEnd1);
        //state获取
        contests.setCtsState(getState(contests.getEnrollBegin(),contests.getEnrollEnd(),contests.getCtsBegin(),contests.getCtsEnd()));
        contests.setCtsTopic(cts.getCtsTopic());
        response.setContentType("text/html;charset=gb2312");
        PrintWriter out = response.getWriter();
        if(contestsService.updateContests(contests)){
            out.print("<script language=\"javascript\">alert('编辑成功!');window.location.href='/manage/contestsadd'</script>");
        }else {
            out.print("<script language=\"javascript\">alert('编辑失败!');window.location.href='/manage/contestsadd'</script>");
        }
    }

    //LocalDateTime格式转换Date格式
    public Contests changeTime(Contests contests,String ctsBegin1,String ctsEnd1,String enrollBegin1,String enrollEnd1) throws Exception{
        //格式转换
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//注意月份是MM
        Date date = simpleDateFormat.parse(ctsBegin1.replaceAll("T", " ") + ":00");
        contests.setCtsBegin(date);
        date=simpleDateFormat.parse(ctsEnd1.replaceAll("T", " ") + ":00");
        contests.setCtsEnd(date);
        date=simpleDateFormat.parse(enrollBegin1.replaceAll("T", " ") + ":00");
        contests.setEnrollBegin(date);
        date=simpleDateFormat.parse(enrollEnd1.replaceAll("T", " ") + ":00");
        contests.setEnrollEnd(date);
        return contests;
    }

    //获取比赛状态
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
