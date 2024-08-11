package com.basic.spring_basic_task.repository;

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

    public List<Schedule> findAll() {
        String sql = "SELECT * FROM schedule";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Schedule schedule = new Schedule();
            schedule.setScheduleId(rs.getInt("schedule_id"));
            schedule.setAssignee(rs.getString("assignee"));
            schedule.setPw(rs.getString("pw"));
            schedule.setContent(rs.getString("content"));
            schedule.setRegDate(rs.getString("reg_date"));
            schedule.setUpdateDate(rs.getString("update"));
            schedule.setDeleteFlag(rs.getBoolean("delete"));
            return schedule;
        });
    }

    public int save(Schedule schedule) {
        String sql = "INSERT INTO schedule (assignee, pw, content, reg_date, update, delete) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                schedule.getAssignee(),
                schedule.getPw(),
                schedule.getContent(),
                schedule.getRegDate(),
                schedule.getUpdateDate(),
                schedule.isDeleteFlag());
    }

    // 다른 CRUD 메서드도 추가할 수 있습니다 (예: update, delete)
}
