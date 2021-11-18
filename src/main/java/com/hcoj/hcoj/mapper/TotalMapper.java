package com.hcoj.hcoj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hcoj.hcoj.domain.Total;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TotalMapper extends BaseMapper<Total> {
}
