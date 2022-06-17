package com.mildlamb;

import com.mildlamb.dao.RoleDao;
import com.mildlamb.pojo.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@SpringBootTest
class Springboot12SqlApplicationTests {

//    @Autowired
//    private RoleDao roleDao;

    @Test
    void contextLoads(@Autowired JdbcTemplate jdbcTemplate) {
        String sql = "select * from users where id = 1";
//        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);

        RowMapper<Role> rm = new RowMapper<Role>() {
            @Override
            public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setName(rs.getString("name"));
                role.setAge(rs.getInt("age"));
                return role;
            }
        };

        List<Role> roles = jdbcTemplate.query(sql, rm);
        System.out.println(roles);
    }

}
