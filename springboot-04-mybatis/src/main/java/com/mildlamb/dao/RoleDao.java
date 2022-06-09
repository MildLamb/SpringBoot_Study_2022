package com.mildlamb.dao;

import com.mildlamb.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RoleDao {
    @Select("select * from users where id = #{rid}")
    Role getRolesById(@Param("rid") Integer id);
}
