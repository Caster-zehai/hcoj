package com.hcoj.hcoj.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcoj.hcoj.domain.Contests;

import java.util.List;


public interface ContestsService {
    //分页查找比赛信息
    public IPage<Contests> SelectPageContests(Page<Contests> page);
    //根据id查找比赛信息
    public Contests findContestsById(int id);
    //查找所有未结束的比赛
    public List<Contests> findAllNoEnd();
    //添加比赛
    public boolean addContests(Contests contests);
    //编辑/更新比赛
    public boolean updateContests(Contests contests);
    //删除比赛
    public boolean delContests(int id);

}
