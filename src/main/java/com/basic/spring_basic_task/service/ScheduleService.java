package com.basic.spring_basic_task.service;

import com.basic.spring_basic_task.dto.*;
import com.basic.spring_basic_task.entity.Schedule;
import com.basic.spring_basic_task.exception.ScheduleException;
import com.basic.spring_basic_task.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    ///////////////// 내부 메서드

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    // 스트링 변수도 하나 더 받아서 해야할 듯;
    private void validateString(String value) {
        if (value == null || value.isEmpty()) {
            throw new ScheduleException("누락된 필수 입력 항목이 있습니다. : service.validateValue");
        }
    }

    // 할 일의 내용 200글자 제한
    private void validContent(String content) {
        if (content.length() > 200) {
            throw new ScheduleException("content : 최대 200글자 입력 가능 : service.validContent");
        }
    }

    // id, pw 일치 검증
    private void idPwCheck(int id, String pw) {
        if (scheduleRepository.idPwCheck(id, pw) == 0) {
            throw new ScheduleException("해당 일정과 pw가 일치하지 않음. : service.idPwCheck");
        }
    }


    ///////////////// 외부 메서드


    // 모든 일정 조회
    public List<ScheduleResponseDto> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    // 일정 추가
    public ScheduleSingleDto postSchedule(ScheduleAddDto AddDto) {
        // 요청 변수 존재여부 확인
//        validateString(AddDto.getAssigneeId());
        validateString(AddDto.getPw());
        validateString(AddDto.getContent());

        // 할 일 크기 확인
        validContent(AddDto.getContent());

        boolean checkInt = scheduleRepository.save(AddDto);
        if (checkInt) {
            return scheduleRepository.getRecentSchedule();
        } else {
            throw new ScheduleException("알 수 없는 오류 : service.addSchedule");
        }
    }

    // id로 일정 조회
    public ScheduleSingleDto getSingleSchedule(int id) {
        return scheduleRepository.getSingleSchedule(id);
    }

    // 바디에 들어온 값에 따라 조회(담당자, 수정일)
    public List<ScheduleResponseDto> getScheduleSearch(ScheduleSearchDto searchDto) {

        if(searchDto.getAssigneeId()==0&&searchDto.getModDate()==null){
            throw new ScheduleException("담당자 또는 수정일을 선택하세요.");
        }

        return scheduleRepository.getScheduleSearchAssigneeMod(searchDto);
    }



    // 선택한 일정 삭제
    public int deleteSchedule(ScheduleRequestDto sReqDto) {
        System.out.println(sReqDto.getPw());
        System.out.println(sReqDto.getScheduleId());
        validateString(sReqDto.getPw());
        if (sReqDto.getScheduleId() == 0) {
            throw new ScheduleException("검색할 일정 id를 입력하세요. : service.deleteSchedule");
        }
        idPwCheck(sReqDto.getScheduleId(), sReqDto.getPw());

        return scheduleRepository.deleteSchedule(sReqDto);
    }

    // 페이지네이션
    public List<ScheduleResponseDto> getPaginationSchedules(int page, int size) {
        return scheduleRepository.getPaginationSchedules(page, size);
    }

    public boolean updateSchedule(ScheduleRequestDto sReqDto){

        if (sReqDto.getScheduleId() == 0) {
            throw new ScheduleException("검색할 일정 id를 입력하세요. : service.updateSchedule");
        }
        validateString(sReqDto.getPw());
        if(sReqDto.getAssigneeId()==0&&sReqDto.getContent()==null){
            throw new ScheduleException("담당자 또는 내용 변경을 하세요.");

        }
        idPwCheck(sReqDto.getScheduleId(), sReqDto.getPw());

        return scheduleRepository.updateSchedule(sReqDto);

    }
}
