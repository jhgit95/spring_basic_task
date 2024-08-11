package com.basic.spring_basic_task.repository;

import com.basic.spring_basic_task.entity.Schedule;
import com.basic.spring_basic_task.entity.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestRepository {

    private final JdbcTemplate jdbcTemplate;

    public TestRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Test> findTest(){
        String sql = "SELECT * FROM test_table";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Test test = new Test();
            test.setName(rs.getString("name"));
            test.setPw(rs.getString("pw"));
            return test;
        });
    }


    public int save(Test test) {
        String sql = "INSERT INTO test_table (name,pw) VALUES (?, ?)";
        return jdbcTemplate.update(sql,
                test.getName(),
                test.getPw());

    }










}
