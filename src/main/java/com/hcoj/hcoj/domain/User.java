package com.hcoj.hcoj.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class User {
    @TableId(value = "user_id",type = IdType.INPUT)
    private String userId;
    private String userPwd;
    private String userName;
    private String userPhone;
    private String userEmail;
    private Integer userAc;
    private Integer userSubmit;
    private String userWatopic;
    private String userActopic;
    private String userCts;

    public User() {
    }

    public User(String userPwd, String userName, String userPhone, String userEmail) {
        this.userPwd = userPwd;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
    }
}
