package com.mildlamb.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mildlamb.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RoleDao extends BaseMapper<Role> {
}
