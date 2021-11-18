package com.hcoj.hcoj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcoj.hcoj.domain.Status;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface StatusMapper extends BaseMapper<Status> {
    @Select("select s.*,t.tpc_name,r.res_c,r.res_class,l.lang_name from `status` s left join topic t on s.pno=t.tpc_id left join result r on s.result=r.res_id left join languages l on s.`language`=l.lang_id where s.cid=#{cid}")
    IPage<Status> selectPageVo(Page<Status> page,Integer cid);
    @Select("select run_id from `status` ORDER BY run_id desc limit 1")
    String getLastId();
    @Select("select s.*,t.tpc_name,r.res_c,r.res_class,l.lang_name from `status` s left join topic t on s.pno=t.tpc_id left join result r on s.result=r.res_id left join languages l on s.`language`=l.lang_id where s.run_id=#{runId}")
    Status selectById(String runId);
}
