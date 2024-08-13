package com.basic.spring_basic_task.controller;

import com.basic.spring_basic_task.dto.*;
import com.basic.spring_basic_task.entity.Schedule;
import com.basic.spring_basic_task.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
    public List<ScheduleResponseDto> getAllSchedules() {
        return scheduleService.getAllSchedules();
    }

    // 할 일 추가
    // 1단계 4번 항목 : 등록된 일정의 정보를 반환 받아 확인할 수 있습니다.
    // 이거 뭔지 모르겠다. 포스트맨에서 바로 내용을 보여줘야 하는 것인가?
    // 등록할 때, 바디 안에 시간을 넣어서 보내게 하는 것이 아니라
    // 스프링에서 현재 시간을 기록해서 db에 넣어주는 기능 필요
    // 이 기능 성공하면, 수정에서도 수정한 날짜를 스프링에서 넣어줄 수 있음
    @PostMapping("/post")
    public int addSchedule(@RequestBody ScheduleAddDto AddDto) {
        System.out.println(scheduleService.addSchedule(AddDto));
        return scheduleService.addSchedule(AddDto);
    }

    // 할 일 단일 조회
    @GetMapping("/get/{id}")
    public ScheduleSingleDto getSingleSchedule(@PathVariable int id){
        return scheduleService.getSingleSchedule(id);

    }

    @GetMapping("/get/search")
    public List<ScheduleResponseDto> getScheduleSearch(@RequestBody ScheduleSearchDto searchDto){
        return scheduleService.getScheduleSearch(searchDto);
    }

    // 선택한 일정 수정
    // pw랑 id 값이 일치하는 것이 없다면, 비밀번호가 틀리거나 id가 없습니다 라고 보내주자
    @PutMapping("/get/{id}")
    public String updateSchedule(@RequestBody ScheduleRequestDto sReqDto){
        int result = scheduleService.updateSchedule(sReqDto);
        System.out.println("원래 1이 나왔네"+scheduleService.updateSchedule(sReqDto));
        if(result==500){
            return  "틀린 비밀번호 또는 없는 일정입니다.";
        }
        if (result == 1) {
            return "답 = "+scheduleService.getSingleSchedule(sReqDto.getScheduleId()).toString();
        }

        return "오류가 발생";

    }

    //선택한 일정 삭제
    @DeleteMapping("/delete")
    public int deleteTest(@RequestBody ScheduleRequestDto sReqDto){
        return scheduleService.deleteSchedule(sReqDto);
    }



}
