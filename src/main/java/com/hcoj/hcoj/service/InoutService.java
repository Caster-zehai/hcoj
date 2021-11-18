package com.hcoj.hcoj.service;

import com.hcoj.hcoj.domain.Inout;

import java.util.List;

public interface InoutService {
    //插入新的输入输出数据
    public void insertInoutDate(List<Inout> list);
    //删除输入输出数据
    public void deleteByTpcId(String tpcId);
    //根据题目id获取输入输出
    public List<Inout> selectInoutById(String tpcId);
}
