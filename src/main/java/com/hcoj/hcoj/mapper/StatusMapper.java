package com.hcoj.hcoj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcoj.hcoj.domain.Status;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StatusMapper extends BaseMapper<Status> {
    @Select("select s.*,t.tpc_name,r.res_c,r.res_class,l.lang_name from `status` s left join topic t on s.pno=t.tpc_id left join result r on s.result=r.res_id left join languages l on s.`language`=l.lang_id where s.cid=#{cid} order by s.submit_date desc")
    IPage<Status> selectPageVo(Page<Status> page,Integer cid);
    @Select("select run_id from `status` ORDER BY submit_date desc limit 1")
    String getLastId();
    @Select("select s.*,t.tpc_name,r.res_c,r.res_class,l.lang_name from `status` s left join topic t on s.pno=t.tpc_id left join result r on s.result=r.res_id left join languages l on s.`language`=l.lang_id where s.run_id=#{runId}")
    Status selectById(String runId);
    @Select("select * from `status` where isadd=0 and result != 10 and result !=11")
    List<Status> selectAllNotAdd();
    @Update("update status set isadd=1 where run_id = #{runId}")
    void  updateIsadd(String runId);

    @Select("select s.*,t.tpc_name,r.res_c,r.res_class,l.lang_name from `status` s left join topic t on s.pno=t.tpc_id left join result r on s.result=r.res_id left join languages l on s.`language`=l.lang_id where s.cid=#{cid} and s.username=#{userId} and s.pno=#{tpcId} order by s.submit_date desc")
    IPage<Status> selectPageVo2(Page<Status> page,Integer cid,String userId,String tpcId);
    @Select("select s.*,t.tpc_name,r.res_c,r.res_class,l.lang_name from `status` s left join topic t on s.pno=t.tpc_id left join result r on s.result=r.res_id left join languages l on s.`language`=l.lang_id where s.cid=#{cid} and s.username=#{userId} order by s.submit_date desc")
    IPage<Status> selectPageVoByUser(Page<Status> page,Integer cid,String userId);
    @Select("select s.*,t.tpc_name,r.res_c,r.res_class,l.lang_name from `status` s left join topic t on s.pno=t.tpc_id left join result r on s.result=r.res_id left join languages l on s.`language`=l.lang_id where s.cid=#{cid} and  s.pno=#{tpcId} order by s.submit_date desc")
    IPage<Status> selectPageVoByTpc(Page<Status> page,Integer cid,String tpcId);
}
