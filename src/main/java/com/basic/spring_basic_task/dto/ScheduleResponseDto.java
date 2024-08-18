package com.basic.spring_basic_task.dto;

import com.basic.spring_basic_task.entity.Schedule;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleResponseDto {
    private int scheduleId;
    private int assigneeId;
    private String assigneeName;
    private String email;
    private String content;
    private String regDate;
    private String modDate;

    public ScheduleResponseDto() {
    }

    public ScheduleResponseDto(Schedule schedule) {
        this.scheduleId = schedule.getScheduleId();
        this.assigneeName = schedule.getAssignee();
        this.content = schedule.getContent();
        this.regDate = schedule.getRegDate();
        this.modDate = schedule.getModDate();
    }
}
