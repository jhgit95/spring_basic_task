package com.basic.spring_basic_task.entity;

import com.basic.spring_basic_task.dto.ScheduleSearchDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Schedule {
    private int scheduleId;
    private String assignee;
    private String pw;
    private String content;
    private String regDate;
    private String modDate;

    public Schedule(ScheduleSearchDto searchDto) {
        this.scheduleId = searchDto.getScheduleId();
        this.assignee = searchDto.getAssignee();
        this.content = searchDto.getContent();
        this.regDate = searchDto.getRegDate();
        this.modDate = searchDto.getModDate();
    }

    public Schedule() {

    }
}
