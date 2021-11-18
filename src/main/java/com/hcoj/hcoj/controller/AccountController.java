package com.hcoj.hcoj.controller;


import com.hcoj.hcoj.domain.User;
import com.hcoj.hcoj.service.TotalService;
import com.hcoj.hcoj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;
    @Autowired
    private TotalService totalService;

    @RequestMapping("/login")
    public String login(Model model){
        return "account/login";
    }

    @RequestMapping("/register")
    public String register(Model model){
        return "account/register";
    }

    //退出登录
    @RequestMapping("/exitlogin")
    public String exitlogin(Model model,HttpSession session){
        session.setAttribute("USER_SESSION",null);
        return "account/login";
    }

    //查看账户信息
    @RequestMapping("/myaccount")
    public String myaccount(Model model, String user_id, String user_pwd, HttpSession session, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=gb2312");
        PrintWriter out = response.getWriter();
        User user = (User) session.getAttribute("USER_SESSION");
        //首先获取session，当session没有User及user_id,pwd不为空时执行
        if(user==null) {
            if(user_id!=null&&user_pwd!=null)
                user = userService.ueserlogin(user_id, user_pwd);
            if(user!=null){//如果返回有值说明登录成功可跳转至我的账户
                session.setAttribute("USER_SESSION", user);
                double acsub=((double)user.getUserAc()/(double)user.getUserSubmit())*100;
                model.addAttribute("acsub",String.format("%.1f", acsub));
                return  "account/myaccount";
            }else{
                if(user_id!=null&&user_pwd!=null) out.print("<script language=\"javascript\">alert('登录失败！请检查用户名、密码是否正确。');window.location.href='/account/login'</script>");
                return "account/login";
            }
        }else{//当session有值时直接跳转页面
            double acsub=((double)user.getUserAc()/(double)user.getUserSubmit())*100;
            model.addAttribute("acsub",String.format("%.2f", acsub));
            return "account/myaccount";
        }
    }

    //注册提交服务
    @RequestMapping("/subregister")
    public String subregister(Model model, HttpServletResponse response, String user_name, String user_phone, String user_email, String user_pwd) throws IOException {
        User user=new User(user_pwd,user_name,user_phone,user_email);
        int b=userService.userregister(user);
        response.setContentType("text/html;charset=gb2312");
        PrintWriter out = response.getWriter();
        if(b>0) {
            totalService.userTotalAdd();
            out.print("<script language=\"javascript\">alert('注册成功！');window.location.href='/account/login'</script>");
            return "account/login";
        }
        else {
            out.print("<script language=\"javascript\">alert('手机号码重复，注册失败！');window.location.href='/account/login'</script>");
            return "account/register";
        }
    }

}
