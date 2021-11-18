package com.hcoj.hcoj.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("topiccontent")
public class TopicContent {
    @TableId(value = "tpc_id",type = IdType.INPUT)
    private String tpcId;
    private int tpcTl;
    private int tpcMl;
    private String tpcDes;
    private String tpcIn;
    private String tpcOut;
    private String tpcExp;
    private String in1;
    private String out1;
    private String in2;
    private String out2;
    private String in3;
    private String out3;
    private String in4;
    private String out4;
    private String in5;
    private String out5;
}
