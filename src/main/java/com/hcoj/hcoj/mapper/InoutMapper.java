package com.hcoj.hcoj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hcoj.hcoj.domain.Inout;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface InoutMapper extends BaseMapper<Inout> {
    @Select("select * from datainout where tpc_id=#{tpcId}")
    public List<Inout> selectInoutByTpcid(String tpcId);
}
