package com.hcoj.hcoj.service.impl;

import com.hcoj.hcoj.domain.Admin;
import com.hcoj.hcoj.mapper.AdminMapper;
import com.hcoj.hcoj.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin adminLogin(Integer admId, String admPwd) {
        Admin admin=null;
        admin=adminMapper.selectById(admId);
        //如果密码不相同则返回null
        if(admin!=null && !admin.getAdmPwd().equals(admPwd)) admin=null;
        return admin;
    }
}
