package com.group.libraryapp.repository.user;

import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean isUserNotExists(long id) {
        String readSql = "SELECT * FROM user WHERE ID= ?";
        return jdbcTemplate.query(readSql, (rs, rowNum)-> 0, id).isEmpty();
    }

    public void updateUserName(String name, long id) {
        String sql = "UPDATE USER SET NAME = ? WHERE ID =?";
        jdbcTemplate.update(sql, name, id);
    }

    public boolean isUserNotExist(String name) {
        String readSql = "SELECT * FROM user WHERE NAME= ?";
        return jdbcTemplate.query(readSql, (rs, rowNum)-> 0, name).isEmpty();
    }

    public void deleteUser(String name) {
        String sql = "DELETE FROM USER WHERE NAME = ?";
        jdbcTemplate.update(sql, name);
    }

    public void saveUser(String name, Integer id) {
        String sql = "INSERT INTO USER(NAME, AGE) VALUES (?, ?)";
        jdbcTemplate.update(sql, name, id);
    }

    public List<UserResponse> getUsers(){
        String sql = "SELECT * FROM USER";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            return new UserResponse(id, name, age);
        });
    }
}
