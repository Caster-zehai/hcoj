package com.hcoj.hcoj.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcoj.hcoj.domain.Status;

import java.util.List;

public interface StatusService {
    //分页获取提交记录列表信息，根据不同参赛选择不同的搜索方式
    public IPage<Status> SelectPageStatus(Page<Status> page,Integer cid);
    public IPage<Status> SelectPageStatus(Page<Status> page,Integer cid,String userId,String tpcId);
    public IPage<Status> SelectPageStatusBytpc(Page<Status> page,Integer cid,String tpcId);
    public IPage<Status> SelectPageStatusByuser(Page<Status> page,Integer cid,String userId);
    //添加提交记录
    public void addSub(Status status);
    //根据runId获取信息
    public Status getMessageById(String runId);
    //获取最后一个runId+1
    public String getLastRunId();
    //获取未添加到用户ac题目里的提交记录状态
    public List<Status> selectAllNotAdd();
    //题目已进行添加修改isadd状态
    public void updateIsadd(String runId);
}
