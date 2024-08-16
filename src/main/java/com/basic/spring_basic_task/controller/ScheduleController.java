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
    public ResponseEntity<ScheduleSingleDto> postSchedule(@RequestBody ScheduleAddDto AddDto) {
        return ResponseEntity.status(200).body(scheduleService.postSchedule(AddDto));
    }


    // 할 일 단일 조회
    @GetMapping("/get/{id}")
    public ResponseEntity<ScheduleSingleDto> getSingleSchedule(@PathVariable int id) {
        return ResponseEntity.status(200).body(scheduleService.getSingleSchedule(id));
    }


    // 담당자와 수정일로 검색
    @GetMapping("/get/search")
    public ResponseEntity<List<ScheduleResponseDto>> getScheduleSearch(@RequestBody ScheduleSearchDto searchDto) {
        return ResponseEntity.status(200).body(scheduleService.getScheduleSearch(searchDto));
    }


    // 일정 수정
    @PutMapping("/get/{id}")
    public ResponseEntity<String> updateSchedule(@RequestBody ScheduleRequestDto sReqDto) {
        if (scheduleService.updateSchedule(sReqDto) == 1) {
            return ResponseEntity.status(200).body(scheduleService.getSingleSchedule(sReqDto.getScheduleId()).data());
        } else {
            return ResponseEntity.status(400).body("알 수 없는 오류 : controller.updateSchedule");
        }
    }


    // 선택한 일정 삭제
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteSchedule(@RequestBody ScheduleRequestDto sReqDto) {
        if (scheduleService.deleteSchedule(sReqDto) == 1) {
            return ResponseEntity.status(200).body("삭제 완료");
        } else {
            return ResponseEntity.status(400).body("알 수 없는 오류 : controller.deleteSchedule");
        }
    }

    // 페이지네이션
    @GetMapping("/get/pagination")
    public List<ScheduleResponseDto> getPaginationSchedules(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size
    ) {
        if (page == null) {
            page = 0;
        }
        if (size == null) {
            size = 10;
        }
        System.out.println("page = " + page + ", size = " + size);
        return scheduleService.getPaginationSchedules(page, size);
    }
}
