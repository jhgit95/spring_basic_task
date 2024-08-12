package com.basic.spring_basic_task.controller;

import com.basic.spring_basic_task.dto.ScheduleAddDto;
import com.basic.spring_basic_task.dto.ScheduleSingleDto;
import com.basic.spring_basic_task.entity.Schedule;
import com.basic.spring_basic_task.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Schedule> getAllSchedules() {
        return scheduleService.getAllSchedules();
    }

    // 할 일 추가
    // 1단계 4번 항목 : 등록된 일정의 정보를 반환 받아 확인할 수 있습니다.
    // 이거 뭔지 모르겠다. 포스트맨에서 바로 내용을 보여줘야 하는 것인가?
    @PostMapping("/post")
    public int addSchedule(@RequestBody ScheduleAddDto AddDto) {
        return scheduleService.addSchedule(AddDto);
    }

    // 할 일 단일 조회
    @GetMapping("/get/{id}")
    public ScheduleSingleDto getSingleSchedule(@PathVariable int id){
        return scheduleService.getSingleSchedule(id);

    }




}
