package com.basic.spring_basic_task.dto;

import com.basic.spring_basic_task.entity.Schedule;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleRequestDto {
    private int scheduleId;
    private String pw;
    private String assignee;
    private String content;

    private String modDate;

    public ScheduleRequestDto() {

    }

    public ScheduleRequestDto(ScheduleSearchDto searchDto) {

    }

    public ScheduleRequestDto(Schedule schedule) {

        this.scheduleId = schedule.getScheduleId();
        this.assignee = schedule.getAssignee();
        this.content = schedule.getContent();

        this.modDate = schedule.getModDate();

    }

}



