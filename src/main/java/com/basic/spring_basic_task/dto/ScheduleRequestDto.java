package com.basic.spring_basic_task.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleRequestDto {
    private int scheduleId;
    private String pw;
    private int assigneeId;
    private String content;
    private String modDate;


}



