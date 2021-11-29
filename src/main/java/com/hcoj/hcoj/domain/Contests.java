package com.hcoj.hcoj.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("contests")
public class Contests {
    @TableId(value = "cts_id",type = IdType.AUTO)
    private Integer ctsId;
    private String ctsName;
    private String ctsCreator;
    private Date enrollBegin;
    private Date enrollEnd;
    private Date ctsBegin;
    private Date ctsEnd;
    private String ctsIntroduction;
    private Integer ctsState;
    private String ctsUser;
    private String ctsTopic;
}
