package com.hcoj.hcoj.service.impl;


import com.hcoj.hcoj.domain.Ctsrank;
import com.hcoj.hcoj.domain.Status;
import com.hcoj.hcoj.mapper.CtsrankMapper;
import com.hcoj.hcoj.service.CtsrankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("ctsrankService")
@Transactional
public class CtsrankServiceImpl implements CtsrankService {
    @Autowired
    private CtsrankMapper ctsrankMapper;

    @Override
    public Ctsrank getCtsrankById(String userId, Integer ctsId) {
        return ctsrankMapper.selectCtsrank(userId,ctsId);
    }

    @Override
    public void addCtsrank(Ctsrank ctsrank) {
        ctsrankMapper.insert(ctsrank);
    }

    @Override
    public void updateCtsrank(Ctsrank ctsrank) {

    }
}
