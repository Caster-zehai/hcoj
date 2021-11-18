package com.hcoj.hcoj.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("admin")
public class Admin {
    @TableId(value = "adm_id",type = IdType.AUTO)
    private Integer admId;
    private String admName;
    private String admPwd;
}
