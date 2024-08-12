package com.basic.spring_basic_task.service;

import com.basic.spring_basic_task.dto.ScheduleAddDto;
import com.basic.spring_basic_task.dto.ScheduleSingleDto;
import com.basic.spring_basic_task.entity.Schedule;
import com.basic.spring_basic_task.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public int addSchedule(ScheduleAddDto AddDto) {
        return scheduleRepository.save(AddDto);
    }

    public ScheduleSingleDto getSingleSchedule(int id){
        return scheduleRepository.getSingleSchedule(id);
    }


}
