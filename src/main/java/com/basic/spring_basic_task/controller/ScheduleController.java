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

    @GetMapping("/zz")
    public String test(){
        return "test test test test get zz test ";
    }

    // 할 일 전부 조회
    @GetMapping("/get")
    public ResponseEntity<List<ScheduleResponseDto>> getAllSchedules() {
        return ResponseEntity.status(200).body(scheduleService.getAllSchedules());
    }


    // 일정 등록
    @PostMapping("/post")
    public ResponseEntity<Integer> addSchedule(@RequestBody ScheduleAddDto AddDto) {
//        System.out.println(scheduleService.addSchedule(AddDto));
        return ResponseEntity.status(200).body(scheduleService.addSchedule(AddDto));
    }

    // 할 일 단일 조회
    @GetMapping("/get/{id}")
    public ScheduleSingleDto getSingleSchedule(@PathVariable int id){
        return scheduleService.getSingleSchedule(id);

    }

    // 담당자와 수정일로 검색
    @GetMapping("/get/search")
    public List<ScheduleResponseDto> getScheduleSearch(@RequestBody ScheduleSearchDto searchDto){
        return scheduleService.getScheduleSearch(searchDto);
    }


    // 일정 수정
    // 바디도 받고 페스베리어블도 받는 방법에 대해서 고민해야 함
    @PutMapping("/get/{id}")
    public String updateSchedule(@RequestBody ScheduleRequestDto sReqDto){
        int result = scheduleService.updateSchedule(sReqDto);
//        System.out.println("원래 1이 나왔네"+scheduleService.updateSchedule(sReqDto));
        if(result==500){
            return  "틀린 비밀번호 또는 없는 일정입니다.";
        }
        if (result == 1) {
            // 그냥 메서드에서 스트링으로 값을 보여주게 하자
            return scheduleService.getSingleSchedule(sReqDto.getScheduleId()).data();
        }

        return "오류가 발생";

    }

    //선택한 일정 삭제
    @DeleteMapping("/delete")
    public int deleteTest(@RequestBody ScheduleRequestDto sReqDto){
        return scheduleService.deleteSchedule(sReqDto);
    }

    // 컨트롤러
    // todo: 9단계에 @valid 이거 사용 안했음
    // 담당자 이메일 정보를 확인할 방법?이 어디있지
    // 일정 가져올 때, 조인해서 담당자 정보도 가져와야 된다는 내용인가?
    // 페이지네이션 남음
    //

    // 서비스
    // getScheduleSearch 여기에 "" 이거 입력됐는데, 왜 그냥 넘어가지는 거지?
    //



}
