package com.hcoj.hcoj.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("total")
public class Total {
    @TableId(value = "tol_id",type = IdType.AUTO)
    private Integer tolId;
    private Integer tolUser;
    private Integer tolTopic;
    private Integer tolSub;
    private Integer tolAc;
    private Integer tolSs;
    private Integer tolS;
    private Integer tolA;
    private Integer tolB;
    private Integer tolC;
    private Integer tolD;
    private Integer tolCe;
    private Integer tolPc;
    private Integer tolWa;
    private Integer tolRe;
    private Integer tolTle;
    private Integer tolMle;
    private Integer tolOle;
    private Integer tolUe;
}
