package com.hcoj.hcoj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hcoj.hcoj.domain.Inout;
import com.hcoj.hcoj.mapper.InoutMapper;
import com.hcoj.hcoj.service.InoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.List;

@Service("inoutService")
public class InoutServiceImpl implements InoutService {

    @Autowired
    private InoutMapper inoutMapper;

    @Override
    public void insertInoutDate(List<Inout> list) {
        try {
            for(Inout inout:list){
                inoutMapper.insert(inout);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteByTpcId(String tpcId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("tpc_id",tpcId);
        inoutMapper.delete(wrapper);
    }

    @Override
    public List<Inout> selectInoutById(String tpcId) {
        List<Inout> list=inoutMapper.selectInoutByTpcid(tpcId);
        return list;
    }
}
