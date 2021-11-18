package com.hcoj.hcoj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcoj.hcoj.domain.Topic;
import com.hcoj.hcoj.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where user_id = #{user_id}")
    public User findUserById(String user_id);

    @Select("select * from user where user_phone = #{user_phone}")
    public User findUserByPhone(String user_phone);

    @Insert("insert into user(user_id,user_pwd,user_name,user_phone,user_email,user_ac,user_submit) values( #{user_id},#{user_pwd},#{user_name},#{user_phone},#{user_email},0,0)")
    public int registerUser(String user_id,String user_pwd,String user_name,String user_phone,String user_email);

    @Select("select user_id from user order by user_id desc limit 1")
    public String findLastId();

    @Select("select * from user order by user_ac desc")
    IPage<User> selectPageVo(Page<User> page);

}
