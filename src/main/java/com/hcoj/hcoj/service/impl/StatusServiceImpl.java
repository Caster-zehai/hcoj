package com.hcoj.hcoj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcoj.hcoj.domain.Status;
import com.hcoj.hcoj.mapper.StatusMapper;
import com.hcoj.hcoj.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("statusService")
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusMapper statusMapper;

    @Override
    public IPage<Status> SelectPageStatus(Page<Status> page,Integer cid) {
        return statusMapper.selectPageVo(page,cid);
    }

    @Override
    public IPage<Status> SelectPageStatus(Page<Status> page, Integer cid, String userId, String tpcId) {
        return statusMapper.selectPageVo2(page,cid,userId,tpcId);
    }

    @Override
    public IPage<Status> SelectPageStatusBytpc(Page<Status> page, Integer cid, String tpcId) {
        return statusMapper.selectPageVoByTpc(page,cid,tpcId);
    }

    @Override
    public IPage<Status> SelectPageStatusByuser(Page<Status> page, Integer cid, String userId) {
        return statusMapper.selectPageVoByUser(page,cid,userId);
    }

    @Override
    public void addSub(Status status) {
        statusMapper.insert(status);
    }

    @Override
    public Status getMessageById(String runId) {
        return statusMapper.selectById(runId);
    }

    @Override
    public String getLastRunId() {
        String s=String.valueOf(Integer.parseInt(statusMapper.getLastId())+1);
        return s;
    }

    @Override
    public List<Status> selectAllNotAdd() {
        return statusMapper.selectAllNotAdd();
    }

    @Override
    public void updateIsadd(String runId) {
        statusMapper.updateIsadd(runId);
    }
}
