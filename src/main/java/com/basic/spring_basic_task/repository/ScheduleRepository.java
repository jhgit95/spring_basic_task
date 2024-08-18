package com.basic.spring_basic_task.repository;

import com.basic.spring_basic_task.dto.*;
import com.basic.spring_basic_task.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 시간 저장할 때 사용하는 시간 문자열 : yyyy-MM-dd HH:mm:ss
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String formattedDate = now.format(formatter);

    // SQL 문에서 LIKE를 사용할 수 있게 문자열을 변환하는 기능
    public String LikeString(String s) {
        return s = s + "%";
    }

    // 가장 최근에 작성된 글을 조회. 할 일 작성 후에 사용됨
    public ScheduleSingleDto getRecentSchedule() {
        String sql = "SELECT s.*,a.name,a.assignee_id,a.email FROM schedule as s   INNER JOIN assignee as a " +
                "ON s.assignee_id = a.assignee_id ORDER BY schedule_id DESC LIMIT 1;";

        // queryForObject 메소드를 사용하여 단일 객체를 조회하고 반환합니다.
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            ScheduleSingleDto singleDto = new ScheduleSingleDto();

            singleDto.setScheduleId(rs.getInt("schedule_id"));
            singleDto.setAssigneeName(rs.getString("name"));
            singleDto.setContent(rs.getString("content"));
            singleDto.setRegDate(rs.getString("reg_date"));
            singleDto.setModDate(rs.getString("mod_date"));
            singleDto.setAssigneeId(rs.getInt("assignee_id"));
            singleDto.setEmail(rs.getString("email"));

            return singleDto;
        });
    }

    // 모든 할 일 조회
    public List<ScheduleResponseDto> findAll() {
        String sql = "SELECT s.*, a.* " +
                "FROM schedule AS s " +
                "INNER JOIN assignee AS a ON s.assignee_id = a.assignee_id " +
                "order by s.mod_date desc;";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {

            ScheduleResponseDto schedule = new ScheduleResponseDto();

            schedule.setScheduleId(rs.getInt("schedule_id"));
            schedule.setAssigneeId(rs.getInt("assignee_id"));
            schedule.setContent(rs.getString("content"));
            schedule.setRegDate(rs.getString("reg_date"));
            schedule.setModDate(rs.getString("mod_date"));
            schedule.setAssigneeName(rs.getString("name"));
            schedule.setEmail(rs.getString("email"));


            return schedule;
        });
    }

    // 할 일 저장
    public boolean save(ScheduleAddDto AddDto) {
        String sql = "INSERT INTO schedule (assignee_id, pw, content, reg_date, mod_date) VALUES (?, ?, ?, ?, ?)";
        boolean isSave = false;
        int value = jdbcTemplate.update(sql,
                AddDto.getAssigneeId(),
                AddDto.getPw(),
                AddDto.getContent(),
                formattedDate,
                formattedDate);
        if (value > 0) {
            isSave = true;
        }
        return isSave;
    }

    // id 값과 일치하는 일정 조회
    public ScheduleSingleDto getSingleSchedule(int id) {
        String sql = "SELECT s.*, a.assignee_id, a.name, a.email FROM schedule as s INNER JOIN assignee as a ON s.assignee_id = a.assignee_id WHERE schedule_id=?";

        // queryForObject 메소드를 사용하여 단일 객체를 조회하고 반환합니다.
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            ScheduleSingleDto singleDto = new ScheduleSingleDto();

            singleDto.setScheduleId(rs.getInt("schedule_id"));
            singleDto.setAssigneeName(rs.getString("name"));
            singleDto.setAssigneeId(rs.getInt("assignee_id"));
            singleDto.setEmail(rs.getString("email"));
            singleDto.setContent(rs.getString("content"));
            singleDto.setRegDate(rs.getString("reg_date"));
            singleDto.setModDate(rs.getString("mod_date"));

            return singleDto;
        });
    }


    // 담당자명과 수정일로 조회
    public List<ScheduleResponseDto> getScheduleSearchAssigneeMod(ScheduleSearchDto scd) {
        StringBuilder sql = new StringBuilder("SELECT s.*, a.name, a.email, a.assignee_id FROM schedule as s " +
                "INNER JOIN assignee as a ON s.assignee_id = a.assignee_id ");
        // sql문 안에 넣을 변수 리스트
        List<Object> params = new ArrayList<>();


        if (scd.getAssigneeId() != 0) {
            sql.append("WHERE s.assignee_id=? ");
            params.add(scd.getAssigneeId());
        }
        if (scd.getModDate() != null) {
            sql.append("and s.mod_date LIKE ? ");
            params.add(LikeString(scd.getModDate()));
        }

        sql.append("order by  mod_date desc");

        // queryForObject 메소드를 사용하여 단일 객체를 조회하고 반환합니다.
        return jdbcTemplate.query(sql.toString(), params.toArray(), (rs, rowNum) -> {
            ScheduleResponseDto schedule = new ScheduleResponseDto();

            schedule.setScheduleId(rs.getInt("schedule_id"));
            schedule.setAssigneeId(rs.getInt("assignee_id"));
            schedule.setAssigneeName(rs.getString("name"));
            schedule.setEmail(rs.getString("email"));
            schedule.setContent(rs.getString("content"));
            schedule.setRegDate(rs.getString("reg_date"));
            schedule.setModDate(rs.getString("mod_date"));

            return schedule;
        });
    }


    // 스케쥴 id와 pw가 일치하는지 확인하는 기능. 검증에서 사용됨
    public int idPwCheck(int id, String pw) {
        String sql = "SELECT COUNT(*) FROM schedule WHERE schedule_id = ? AND pw = ?";
        // jdbcTemplate.queryForObject를 사용하여 결과를 Integer로 반환받습니다.
        return jdbcTemplate.queryForObject(sql, Integer.class, id, pw);
    }


    // 선택한 일정 삭제
    public int deleteSchedule(ScheduleRequestDto sReqDto) {
        String sql = "DELETE FROM schedule WHERE schedule_id = ? and pw = ?";
        return jdbcTemplate.update(sql, sReqDto.getScheduleId(), sReqDto.getPw());
    }


    // 페이지네이션
    public List<ScheduleResponseDto> getPaginationSchedules(int page, int size) {
        System.out.println("page = " + page + ", size = " + size);
        // 계산된 offset을 사용하여 SQL 쿼리를 수행
        int offset = page * size;
        String sql = "SELECT s.*,a.assignee_id,a.name,a.email FROM schedule as s INNER JOIN assignee as a ON s.assignee_id = a.assignee_id " +
                "ORDER BY schedule_id ASC LIMIT ? OFFSET ?";

        return jdbcTemplate.query(sql, new Object[]{size, offset}, (rs, rowNum) -> {
                    ScheduleResponseDto schedule = new ScheduleResponseDto();

                    schedule.setAssigneeId(rs.getInt("assignee_id"));
                    schedule.setContent(rs.getString("content"));
                    schedule.setRegDate(rs.getString("reg_date"));
                    schedule.setModDate(rs.getString("mod_date"));
                    schedule.setModDate(rs.getString("mod_date"));
                    schedule.setModDate(rs.getString("mod_date"));
                    schedule.setEmail(rs.getString("email"));
                    schedule.setAssigneeName(rs.getString("name"));

                    return schedule;
                }
        );
    }

    public boolean updateSchedule(ScheduleRequestDto sReqDto) {

        StringBuilder sql = new StringBuilder("UPDATE schedule SET mod_date = ?");

        // sql문 안에 넣을 변수 리스트
        List<Object> params = new ArrayList<>();

        params.add(formattedDate);

        if (sReqDto.getContent() != null) {
            sql.append(", content = ?");
            params.add(sReqDto.getContent());
        }

        if (sReqDto.getAssigneeId() != 0) {
            sql.append(", assignee_id = ?");
            params.add(sReqDto.getAssigneeId());
        }

        sql.append(" WHERE schedule_id = ? AND pw = ?");
        params.add(sReqDto.getScheduleId());
        params.add(sReqDto.getPw());

        int isUpdate = jdbcTemplate.update(sql.toString(), params.toArray());
        boolean returnBoolean = false;
        if (isUpdate > 0) {
            returnBoolean = true;
        }
        return returnBoolean;
    }

}
