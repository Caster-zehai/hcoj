package com.hcoj.hcoj.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcoj.hcoj.domain.Status;

public interface StatusService {
    //分页获取提交记录列表信息，
    public IPage<Status> SelectPageStatus(Page<Status> page,Integer cid);
    //添加提交记录
    public void addSub(Status status);
    //根据runId获取信息
    public Status getMessageById(String runId);
    //获取最后一个runId+1
    public String getLastRunId();
}
