package com.basic.spring_basic_task.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleSearchDto {
    int assigneeId;
    int scheduleId;
    String assignee;
    String content;
    String regDate;
    String modDate;
}
