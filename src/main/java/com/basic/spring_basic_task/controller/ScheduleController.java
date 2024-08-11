package com.basic.spring_basic_task.controller;

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

    @GetMapping
    public List<Schedule> getAllSchedules() {
        return scheduleService.getAllSchedules();
    }

    @PostMapping
    public void addSchedule(@RequestBody Schedule schedule) {
        scheduleService.addSchedule(schedule);
    }
}
