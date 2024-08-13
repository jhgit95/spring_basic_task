package com.basic.spring_basic_task.repository;

import com.basic.spring_basic_task.dto.*;
import com.basic.spring_basic_task.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ScheduleResponseDto> findAll() {
        String sql = "SELECT * FROM schedule order by  mod_date desc";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Schedule schedule = new Schedule();
            schedule.setScheduleId(rs.getInt("schedule_id"));
            schedule.setAssignee(rs.getString("assignee"));
            schedule.setPw(rs.getString("pw"));
            schedule.setContent(rs.getString("content"));
            schedule.setRegDate(rs.getString("reg_date"));
            schedule.setModDate(rs.getString("mod_date"));

            ScheduleResponseDto sResDto = new ScheduleResponseDto(schedule);
            return sResDto;
        });
    }

    public int save(ScheduleAddDto AddDto) {
        String sql = "INSERT INTO schedule (assignee, pw, content, reg_date, mod_date) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                AddDto.getAssignee(),
                AddDto.getPw(),
                AddDto.getContent(),
                AddDto.getRegDate(),
                AddDto.getRegDate());

    }

    public ScheduleSingleDto getSingleSchedule(int id) {
        String sql = "SELECT * FROM schedule WHERE schedule_id=?";

        // queryForObject 메소드를 사용하여 단일 객체를 조회하고 반환합니다.
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            ScheduleSingleDto singleDto = new ScheduleSingleDto();
            singleDto.setScheduleId(rs.getInt("schedule_id"));
            singleDto.setAssignee(rs.getString("assignee"));
            singleDto.setContent(rs.getString("content"));
            singleDto.setReg_date(rs.getString("reg_date"));
            singleDto.setMod_date(rs.getString("mod_date"));
            return singleDto;
        });
    }

    // 수정일로 조회
    public List<ScheduleResponseDto> getScheduleSearch(Schedule scd){

        String sql = "SELECT * FROM schedule WHERE mod_date=? order by  mod_date desc";
        return jdbcTemplate.query(sql, new Object[]{scd.getModDate()}, (rs, rowNum) -> {
            Schedule schedule = new Schedule();

            schedule.setScheduleId(rs.getInt("schedule_id"));
            schedule.setAssignee(rs.getString("assignee"));
            schedule.setAssignee(rs.getString("content"));
            schedule.setAssignee(rs.getString("reg_date"));
            schedule.setAssignee(rs.getString("mod_date"));

            ScheduleResponseDto sResDto = new ScheduleResponseDto(schedule);
            return sResDto;
        });
    }

    //담당자명으로만 조회
    public List<ScheduleResponseDto> getScheduleSearchAssignee(Schedule scd){
        System.out.println("문제를 파악해보자. 어사이니 = "+scd.getAssignee());

        String sql = "SELECT * FROM schedule WHERE assignee=? order by  mod_date desc";
        return jdbcTemplate.query(sql, new Object[]{scd.getAssignee()}, (rs, rowNum) -> {
            Schedule schedule = new Schedule();

            schedule.setScheduleId(rs.getInt("schedule_id"));
            schedule.setAssignee(rs.getString("assignee"));
            schedule.setContent(rs.getString("content"));
            schedule.setRegDate(rs.getString("reg_date"));
            schedule.setModDate(rs.getString("mod_date"));

            ScheduleResponseDto sResDto = new ScheduleResponseDto(schedule);
            return sResDto;
        });
    }


    // 담당자명과 수정일로 조회
    public List<ScheduleResponseDto> getScheduleSearchAssigneeMod(Schedule scd) {
        String sql = "SELECT * FROM schedule WHERE assignee=? and mod_date=? order by  mod_date desc";

        // queryForObject 메소드를 사용하여 단일 객체를 조회하고 반환합니다.
        return jdbcTemplate.query(sql, new Object[]{scd.getAssignee(),scd.getModDate()}, (rs, rowNum) -> {
            Schedule schedule = new Schedule();
            schedule.setScheduleId(rs.getInt("schedule_id"));
            schedule.setAssignee(rs.getString("assignee"));
            schedule.setContent(rs.getString("content"));
            schedule.setRegDate(rs.getString("reg_date"));
            schedule.setModDate(rs.getString("mod_date"));
            ScheduleResponseDto sResDto = new ScheduleResponseDto(schedule);
            return sResDto;
        });
    }


//    public ScheduleSingleDto getSingleSchedule(int id){
//        String sql4 = "SELECT * FROM spartaspring.test_table WHERE id=?;";
//
//        // jdbcTemplate.query 메소드를 사용하여 쿼리를 실행하고, 결과를 매핑합니다.
//        return jdbcTemplate.query(sql4, Object[]{int id}, (rs, rowNum) -> {
//            ScheduleSingleDto singleDto = new ResponseTestDto();
//            singleDto.setAssignee(rs.getString("assignee"));
//            singleDto.setContent(rs.getString("content"));
//            return singleDto;
//        });
//    }
}
