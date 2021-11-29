package com.hcoj.hcoj.service;

import com.hcoj.hcoj.domain.Total;

public interface TotalService {
    //查询管理页面中总计信息
    public Total selectTotal();

    //用户增加更新用户数
    public void userTotalAdd();

    //用户删除更新用户数
    public void userTotalDel();

    //题目增加更新题目数及难度数
    public void topicTotalAdd(String tpcDiff);

    //题目删除更新题目数及难度数
    public void topicTotalDel(String tpcDiff);

    //更新提交数+1
    public void updatesub();

    //更新通过数+1
    public void updateac();

    //更新结果数据
    public void updateresult(Integer result);

}
