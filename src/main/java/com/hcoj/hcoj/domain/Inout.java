package com.hcoj.hcoj.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("datainout")
public class Inout {
    @TableId(value = "inout_id",type = IdType.AUTO)
    private Integer inoutId;
    private String tpcId;
    private String datain;
    private String dataout;
}
