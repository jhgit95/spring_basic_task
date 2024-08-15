package com.basic.spring_basic_task.controller;

import com.basic.spring_basic_task.dto.*;
import com.basic.spring_basic_task.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    // 할 일 전부 조회
    @GetMapping("/get")
    public ResponseEntity<List<ScheduleResponseDto>> getAllSchedules() {
        return ResponseEntity.status(200).body(scheduleService.getAllSchedules());
    }


    // 일정 등록
    @PostMapping("/post")
    public ResponseEntity<ScheduleSingleDto> addSchedule(@RequestBody ScheduleAddDto AddDto) {
        return ResponseEntity.status(200).body(scheduleService.addSchedule(AddDto));
    }


    // 할 일 단일 조회
    @GetMapping("/get/{id}")
    public ScheduleSingleDto getSingleSchedule(@PathVariable int id) {
        return scheduleService.getSingleSchedule(id);
    }


    // 담당자와 수정일로 검색
    @GetMapping("/get/search")
    public List<ScheduleResponseDto> getScheduleSearch(@RequestBody ScheduleSearchDto searchDto) {
        return scheduleService.getScheduleSearch(searchDto);
    }


    // 일정 수정
    @PutMapping("/get/{id}")
    public String updateSchedule(@RequestBody ScheduleRequestDto sReqDto) {
        if (scheduleService.updateSchedule(sReqDto) == 1) {
            return scheduleService.getSingleSchedule(sReqDto.getScheduleId()).data();
        } else {
            return "오류가 발생";
        }
    }


    //선택한 일정 삭제
    @DeleteMapping("/delete")
    public String deleteSchedule(@RequestBody ScheduleRequestDto sReqDto) {
        if (scheduleService.deleteSchedule(sReqDto) == 1) {
            return "삭제 완료";
        } else {
            return "알 수 없는 오류 : deleteTest";
        }
    }


    // api명세서, erd 작성
    // schedule.sql 파일 만드는 법 확인하기

    // 디렉토리
    // test랑 schedule 이랑 분리시켜주기

    // 컨트롤러
    // todo: 9단계(검증)에 @valid 이거 사용 안했음
    // 담당자 이메일 정보를 확인할 방법?이 어디있지? 일정 가져올 때, 조인해서 담당자 정보도 가져와야 된다는 내용인가?
    // 7단계 : 페이지네이션 남음
    //


    // 내가 만드는 추가 기능
    // 한 담당자가 얼만큼의 일을 가지고 있는지 join해서 가져오는 기능
    // 어떤 일의 담당자에 대한 정보를 가져오는 기능

    // 진짜 할 일
    // 테이블 만들고
    // 담당자 테이블에 더미 좀 넣고
    // 넣은 데이터랑 스케쥴이랑 매핑해주고
    // 그 다음에 조인문 2개 만들어서 기능 2개 만들어주고
    // 페이징 해주면 일단 기능은 끝

    // 그럼 이제 문서작업
    // 리드미 > erd > sql파일 > test 분리

    // 그럼 이제 밀어둔 작업
    // @valid 이거 안 썼던거 이제 써줘야하고


}
