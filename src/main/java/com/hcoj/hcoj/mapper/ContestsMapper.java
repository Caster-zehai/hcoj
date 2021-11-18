package com.hcoj.hcoj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcoj.hcoj.domain.Contests;
import com.hcoj.hcoj.domain.Topic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ContestsMapper extends BaseMapper<Contests> {
    @Select("select * from contests order by cts_state")
    IPage<Contests> selectPageVo(Page<Contests> page);
    @Select("select * from contests where cts_state != 4")
    List<Contests> selectAllNoEnd();
}
