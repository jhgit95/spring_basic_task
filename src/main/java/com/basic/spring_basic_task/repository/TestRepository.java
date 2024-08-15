package com.basic.spring_basic_task.repository;

import com.basic.spring_basic_task.controller.TestDeleteDto;
import com.basic.spring_basic_task.dto.RequestTestDto;
import com.basic.spring_basic_task.dto.ResponseTestDto;
import com.basic.spring_basic_task.entity.ApiTest;
//import com.basic.spring_basic_task.entity.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestRepository {

    private final JdbcTemplate jdbcTemplate;

    public TestRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<ResponseTestDto> findTest(){
        String sql = "SELECT * FROM test_table";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            ResponseTestDto responseTestDto = new ResponseTestDto();
            responseTestDto.setName(rs.getString("name"));
            responseTestDto.setPw(rs.getString("pw"));
            return responseTestDto;
        });
    }



    public int save(RequestTestDto requestTestDto) {
        String sql = "INSERT INTO test_table (name,pw) VALUES (?, ?)";
        System.out.println("Name: " + requestTestDto.getName() + ", PW: " + requestTestDto.getPw());

        return jdbcTemplate.update(sql,
                requestTestDto.getName(),
                requestTestDto.getPw());

    }

    public List<ResponseTestDto> findSingleTest(ApiTest apiTest) {
        String sql4 = "SELECT * FROM spartaspring.test_table WHERE name=? AND pw=?;";

        // jdbcTemplate.query 메소드를 사용하여 쿼리를 실행하고, 결과를 매핑합니다.
        return jdbcTemplate.query(sql4, new Object[]{apiTest.getName(), apiTest.getPw()}, (rs, rowNum) -> {
            ResponseTestDto responseTestDto = new ResponseTestDto();
            responseTestDto.setName(rs.getString("name"));
            responseTestDto.setPw(rs.getString("pw"));
            return responseTestDto;
        });
    }




    public List<ResponseTestDto> findPwTest(ApiTest apiTest){
        String sql= "SELECT * FROM spartaspring.test_table where  pw=?;";
        return jdbcTemplate.query(sql, new Object[]{apiTest.getPw()}, (rs, rowNum) -> {
            ResponseTestDto responseTestDto = new ResponseTestDto();
            responseTestDto.setName(rs.getString("name"));
            responseTestDto.setPw(rs.getString("pw"));
            return responseTestDto;
        });
    }



    public List<ResponseTestDto> findNameTest(ApiTest apiTest){
        String sql = "SELECT * FROM spartaspring.test_table where name=?;";
        return jdbcTemplate.query(sql, new Object[]{apiTest.getName()}, (rs, rowNum) -> {
            ResponseTestDto responseTestDto = new ResponseTestDto();
            responseTestDto.setName(rs.getString("name"));
            responseTestDto.setPw(rs.getString("pw"));
            return responseTestDto;
        });

    }

    public int updateTest(ApiTest apiTest){
        String sql = "UPDATE test_table SET pw = ? WHERE name=?";
        return jdbcTemplate.update(sql, apiTest.getPw(), apiTest.getName());
    }

    public int deleteTest(TestDeleteDto delete){
        String sql = "DELETE FROM test_table WHERE id = ?";
        return jdbcTemplate.update(sql, delete.getId());

    }


    public List<ResponseTestDto> findTest44(){
        String sql = "SELECT * FROM test_table";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            ResponseTestDto responseTestDto = new ResponseTestDto();
            responseTestDto.setName(rs.getString("name"));
            responseTestDto.setPw(rs.getString("pw"));
            return responseTestDto;
        });
    }


    public List<ResponseTestDto> findfind22(){
        String sql = "SELECT * FROM test_table";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            ResponseTestDto responseTestDto = new ResponseTestDto();
            responseTestDto.setName(rs.getString("name"));
            responseTestDto.setPw(rs.getString("pw"));
            return responseTestDto;
        });
    }







    }
