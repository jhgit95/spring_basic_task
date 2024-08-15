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
    private void validateValue(String value) {
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
    public ScheduleSingleDto addSchedule(ScheduleAddDto AddDto) {
        // 요청 변수 존재여부 확인
        validateValue(AddDto.getAssignee());
        validateValue(AddDto.getPw());
        validateValue(AddDto.getContent());

        // 할 일 크기 확인
        validContent(AddDto.getContent());

        int checkInt = scheduleRepository.save(AddDto);
        if (checkInt == 1) {
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
//        return scheduleRepository.getScheduleSearch(searchDto);
        System.out.println(searchDto.getAssignee());
        System.out.println(searchDto.getMod_date());

        Schedule scd = new Schedule(searchDto);

        if ((searchDto.getMod_date() == null || searchDto.getMod_date().isEmpty()) && (searchDto.getAssignee() == null || searchDto.getAssignee().isEmpty())) {
            System.out.println("담당자와 수정일이 전부 null");
            return scheduleRepository.findAll();
        } else if (searchDto.getMod_date() == null || searchDto.getMod_date().isEmpty()) {
            System.out.println("수정일이 null");
            return scheduleRepository.getScheduleSearchAssignee(scd);


        } else if (searchDto.getAssignee() == null || searchDto.getAssignee().isEmpty()) {
            System.out.println("담당자가 null");
            return scheduleRepository.getScheduleSearch(scd);

        } else {
            System.out.println("둘 다 값이 있을 경우");
            return scheduleRepository.getScheduleSearchAssigneeMod(scd);
        }
    }

    // 일정 수정
    public int updateSchedule(ScheduleRequestDto sReqDto) {

        if (sReqDto.getScheduleId() == 0) {
            throw new ScheduleException("검색할 일정 id를 입력하세요. : service.updateSchedule");
        }
        validateValue(sReqDto.getPw());
        idPwCheck(sReqDto.getScheduleId(), sReqDto.getPw());

        // 할 일과 담당자 수정
        if (sReqDto.getContent() != null && sReqDto.getAssignee() != null) {
            System.out.println("할 일과 담당자 변경");
            System.out.println(scheduleRepository.updateScheduleAssigneeContent(sReqDto));
            return scheduleRepository.updateScheduleAssigneeContent(sReqDto);
        }
        // 담당자만 수정
        else if (sReqDto.getAssignee() != null) {
            System.out.println("담당자 변경");
            return scheduleRepository.updateScheduleAssignee(sReqDto);
        }
        // 할 일만 수정
        else if (sReqDto.getContent() != null) {
            System.out.println("할 일 변경");
            return scheduleRepository.updateScheduleContent(sReqDto);

        } else {
            throw new ScheduleException("변경할 내용을 입력하세요.(담당자 또는 할 일) : service.updateSchedule");
        }
    }

    // 선택한 일정 삭제
    public int deleteSchedule(ScheduleRequestDto sReqDto) {
        System.out.println(sReqDto.getPw());
        System.out.println(sReqDto.getScheduleId());
        validateValue(sReqDto.getPw());
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


}
