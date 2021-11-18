package com.hcoj.hcoj.service;

import com.hcoj.hcoj.domain.Admin;

public interface AdminService {
    public Admin adminLogin(Integer admId,String admPwd);
}
