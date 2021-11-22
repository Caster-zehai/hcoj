package com.hcoj.hcoj.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("status")
public class Status implements Serializable {
    private static final long serialVersionUID = -1340095119555693650L;
    public Status(){
    }
    @TableId(value = "run_id",type = IdType.INPUT)
    private String runId;
    private String username;
    private int cid;
    private String pno;
    private Integer result;
    private int time, mem;
    private int length;
    private String language;
    private String code;
    private Date submitDate;
    @TableField(value = "t.tpc_ame")
    private String tpcName;
    @TableField(value = "r.res_c")
    private String resC;
    @TableField(value = "r.res_class")
    private String resClass;
    @TableField(value = "l.lang_name")
    private String langName;
    private Integer isadd;
}
