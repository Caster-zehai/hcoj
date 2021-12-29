package com.hcoj.hcoj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcoj.hcoj.domain.Ctsrank;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CtsrankMapper extends BaseMapper<Ctsrank> {
    //根据比赛分页查询排名，先根据通过题数进行排序，题数相同则比较通过率
    @Select("select * from ctsrank where cts_id=#{ctsId} order by ran_ac desc , ran_total desc")
    IPage<Ctsrank> selectPageVo(Page<Ctsrank> page,Integer ctsId);
    //根据用户id与比赛id查询比赛排名记录
    @Select("select * from ctsrank where user_id=#{userId} and cts_id=#{ctsId}")
    Ctsrank selectCtsrank(String userId, Integer ctsId);
}
