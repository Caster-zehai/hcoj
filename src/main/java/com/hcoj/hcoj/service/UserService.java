package com.hcoj.hcoj.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcoj.hcoj.domain.User;



public interface UserService {
    //通过id/手机号码登录
    public User ueserlogin(String user_id,String user_pwd);
    //注册
    public int userregister(User user);
    //获取所有用户信息
    public IPage<User> SelectPageUser(Page<User> page);
    //删除用户
    public boolean delUser(String userId);
    //根据用户Id查询用户信息
    public User selectUserById(String userId);
    //编辑/更改用户信息
    public boolean updateUser(User user);
    //用户提交代码，更新用户提交数
    public boolean updateUserSub(String userId);
    //用户提交代码ac，更新用户ac数
    public boolean updateUserAc(String userId,String tpcId);
}
