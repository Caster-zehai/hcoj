package com.hcoj.hcoj.service;

import com.hcoj.hcoj.domain.Ctsrank;
import com.hcoj.hcoj.domain.Status;

public interface CtsrankService {

    //根据用户id与比赛id查询比赛排名记录
    Ctsrank getCtsrankById(String userId,Integer ctsId);
    //添加一条比赛排名记录
    void addCtsrank(Ctsrank ctsrank);
    //更新比赛排名记录
    void updateCtsrank(Ctsrank ctsrank);
}
