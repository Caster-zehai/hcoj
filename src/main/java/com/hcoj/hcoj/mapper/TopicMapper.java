package com.hcoj.hcoj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcoj.hcoj.domain.Topic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TopicMapper extends BaseMapper<Topic> {
    @Select("select * from topic where tpc_state=#{state}")
    IPage<Topic> selectPageVo(Page<Topic> page, Integer state);

    @Select("select * from topic")
    IPage<Topic> selectPageAll(Page<Topic> page);

    @Select("SELECT tpc_id FROM topic ORDER BY tpc_id DESC LIMIT 1;")
    String selectLastId();

    @Select("select * from topic where tpc_state=0 and cts_id is null")
    List<Topic> selectAllNoOpen();

}
