package com.hcoj.hcoj.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcoj.hcoj.domain.Contests;
import com.hcoj.hcoj.domain.Ctsrank;
import com.hcoj.hcoj.mapper.ContestsMapper;
import com.hcoj.hcoj.mapper.CtsrankMapper;
import com.hcoj.hcoj.mapper.TopicMapper;
import com.hcoj.hcoj.service.ContestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("contestsService")
@Transactional
public class ContestsServiceImpl implements ContestsService {

    @Autowired
    private ContestsMapper contestsMapper;
    @Autowired
    private CtsrankMapper ctsrankMapper;

    @Override
    public IPage<Contests> SelectPageContests(Page<Contests> page) {
        return contestsMapper.selectPageVo(page);
    }

    @Override
    public IPage<Ctsrank> selectPageCtsrank(Page<Ctsrank> page, Integer ctsId) {
        return ctsrankMapper.selectPageVo(page,ctsId);
    }

    @Override
    public Contests findContestsById(int id) {
        return contestsMapper.selectById(id);
    }

    @Override
    public List<Contests> findAllNoEnd() {
        List<Contests> lc=contestsMapper.selectAllNoEnd();
        if(lc.isEmpty()) return null;
        else return lc;
    }

    @Override
    public boolean addContests(Contests contests) {
        try{
            contestsMapper.insert(contests);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean updateContests(Contests contests) {
        try{
            contestsMapper.updateById(contests);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delContests(int id) {
        try{
            contestsMapper.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
