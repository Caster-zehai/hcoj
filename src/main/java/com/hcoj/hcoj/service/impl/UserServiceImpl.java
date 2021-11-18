package com.hcoj.hcoj.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcoj.hcoj.domain.User;
import com.hcoj.hcoj.mapper.UserMapper;
import com.hcoj.hcoj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User ueserlogin(String user_id, String user_pwd) {
        User user=null;
        if(user_id.length()==11) {//11位数为手机登录
            user = userMapper.findUserByPhone(user_id);
        }else if(user_id.length()==6){//6位数为user_id登录
            user =userMapper.findUserById(user_id);
        }
        //如果密码不相同则返回null
        if(user!=null&&!user.getUserPwd().equals(user_pwd)) user=null;
        return user;
    }

    @Override
    public int userregister(User user) {
        String id=Integer.toString(Integer.parseInt(userMapper.findLastId())+1);
        user.setUserId(id);
        int b=0;
        if(userMapper.findUserByPhone(user.getUserPhone())==null) {
            b = userMapper.registerUser(user.getUserId(), user.getUserPwd(), user.getUserName(), user.getUserPhone(), user.getUserEmail());
        }
        return b;
    }

    @Override
    public IPage<User> SelectPageUser(Page<User> page) {
        return userMapper.selectPageVo(page);
    }

    @Override
    public boolean delUser(String userId) {
        try {
            userMapper.deleteById(userId);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public User selectUserById(String userId) {
        return userMapper.selectById(userId);
    }

    @Override
    public boolean updateUser(User user) {
        try {
            userMapper.updateById(user);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }


}
