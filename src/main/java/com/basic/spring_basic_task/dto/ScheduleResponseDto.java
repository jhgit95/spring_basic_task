package com.basic.spring_basic_task.dto;

import com.basic.spring_basic_task.entity.Schedule;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleResponseDto {
    private int scheduleId;
    private String assignee;
    private String content;
    private String regDate;
    private String modDate;

    public ScheduleResponseDto() {

    }

    public ScheduleResponseDto(ScheduleSearchDto searchDto) {

    }

    public ScheduleResponseDto(Schedule schedule) {

        this.scheduleId = schedule.getScheduleId();
        this.assignee = schedule.getAssignee();
        this.content = schedule.getContent();
        this.regDate = schedule.getRegDate();
        this.modDate = schedule.getModDate();

    }
}
