package com.basic.spring_basic_task.repository;

import com.basic.spring_basic_task.dto.ScheduleAddDto;
import com.basic.spring_basic_task.dto.ScheduleRequestDto;
import com.basic.spring_basic_task.dto.ScheduleResponseDto;
import com.basic.spring_basic_task.dto.ScheduleSingleDto;
import com.basic.spring_basic_task.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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


    // 혹시 이 메서드를 사용하는 곳이 있을 것 같아서 만들었음
    public String time() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  // 포매터 설정
        String formattedDate = now.format(formatter);  // 현재 날짜를 "yyyy-MM-dd" 형식으로 포매팅
        return formattedDate;
    }

    // SQL 문에서 LIKE를 사용할 수 있게 문자열을 변환하는 기능
    public String LikeString(String s) {
        return s = s + "%";
    }


    // 가장 최근에 작성된 글을 조회. 할 일 작성 후에 사용됨
    public ScheduleSingleDto getRecentSchedule() {
        String sql = "SELECT * FROM spartaspring.schedule ORDER BY schedule_id DESC LIMIT 1;";

        // queryForObject 메소드를 사용하여 단일 객체를 조회하고 반환합니다.
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            ScheduleSingleDto singleDto = new ScheduleSingleDto();

            singleDto.setScheduleId(rs.getInt("schedule_id"));
            singleDto.setAssignee(rs.getString("assignee"));
            singleDto.setContent(rs.getString("content"));
            singleDto.setReg_date(rs.getString("reg_date"));
            singleDto.setMod_date(rs.getString("mod_date"));

            return singleDto;
        });
    }

    // 모든 할 일 조회
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

    // 할 일 저장
    public int save(ScheduleAddDto AddDto) {
        String sql = "INSERT INTO schedule (assignee, pw, content, reg_date, mod_date) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                AddDto.getAssignee(),
                AddDto.getPw(),
                AddDto.getContent(),
                formattedDate,
                formattedDate);
    }

    // id 값과 일치하는 일정 조회
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
    public List<ScheduleResponseDto> getScheduleSearch(Schedule scd) {

        String sql = "SELECT * FROM schedule WHERE mod_date LIKE ? order by  mod_date desc";
        return jdbcTemplate.query(sql, new Object[]{LikeString(scd.getModDate())}, (rs, rowNum) -> {
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

    // 담당자명으로만 조회
    public List<ScheduleResponseDto> getScheduleSearchAssignee(Schedule scd) {
        System.out.println("문제를 파악해보자. 어사이니 = " + scd.getAssignee());

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
        String sql = "SELECT * FROM schedule WHERE assignee=? and mod_date LIKE ? order by  mod_date desc";

        // queryForObject 메소드를 사용하여 단일 객체를 조회하고 반환합니다.
        return jdbcTemplate.query(sql, new Object[]{scd.getAssignee(), LikeString(scd.getModDate())}, (rs, rowNum) -> {
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


    // 할 일 수정
    public int updateScheduleContent(ScheduleRequestDto sReqDto) {
        String sql = "UPDATE schedule SET mod_date=?, content = ? WHERE schedule_id=? and pw=?";
        return jdbcTemplate.update(sql, formattedDate, sReqDto.getContent(), sReqDto.getScheduleId(), sReqDto.getPw());
    }

    // 담당자만 수정
    public int updateScheduleAssignee(ScheduleRequestDto sReqDto) {
        String sql = "UPDATE schedule SET mod_date=?, assignee = ? WHERE schedule_id=? and pw=?";
        return jdbcTemplate.update(sql, formattedDate, sReqDto.getAssignee(), sReqDto.getScheduleId(), sReqDto.getPw());
    }

    // 할 일과 담당자 수정
    public int updateScheduleAssigneeContent(ScheduleRequestDto sReqDto) {
        String sql = "UPDATE schedule SET mod_date=?, assignee = ?, content=? WHERE schedule_id=? and pw=?";
        return jdbcTemplate.update(sql, formattedDate, sReqDto.getAssignee(), sReqDto.getContent(), sReqDto.getScheduleId(), sReqDto.getPw());
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
        String sql = "SELECT * FROM schedule ORDER BY schedule_id ASC LIMIT ? OFFSET ?";

        return jdbcTemplate.query(sql, new Object[]{size, offset}, (rs, rowNum) -> {
                    Schedule schedule = new Schedule();
                    schedule.setScheduleId(rs.getInt("schedule_id"));
                    schedule.setAssignee(rs.getString("assignee"));
                    schedule.setContent(rs.getString("content"));
                    schedule.setRegDate(rs.getString("reg_date"));
                    schedule.setModDate(rs.getString("mod_date"));

                    ScheduleResponseDto sResDto = new ScheduleResponseDto(schedule);
                    return sResDto;
                }
        );
    }

}
