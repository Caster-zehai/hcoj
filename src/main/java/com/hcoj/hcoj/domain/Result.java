package com.hcoj.hcoj.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("result")
public class Result {
    @TableId(value = "res_id",type = IdType.AUTO)
    private Integer resId;
    private String resC;
    private String resE;
    private String resClass;
}
