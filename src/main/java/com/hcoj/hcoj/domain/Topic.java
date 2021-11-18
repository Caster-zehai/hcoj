package com.hcoj.hcoj.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
@TableName("topic")
public class Topic {
    @TableId(value = "tpc_id",type = IdType.INPUT)
    private String tpcId;
    private String tpcName;
    private String tpcLabel;
    private String tpcDiff;
    private Integer tpcSub;
    private Integer tpcAc;
    private Integer tpcState;
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private Integer ctsId;
}
