package com.hcoj.hcoj.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("ctsrank")
public class Ctsrank {
    @TableId(value = "ran_id",type = IdType.AUTO)
    private Integer ranId;
    private Integer ctsId;
    private String userId;
    private Integer ranSub;
    private Integer ranAc;
    private Double ranTotal;

}
