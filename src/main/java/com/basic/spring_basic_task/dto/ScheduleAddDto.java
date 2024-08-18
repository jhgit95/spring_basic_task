package com.basic.spring_basic_task.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ScheduleAddDto {
    private int assigneeId;
    private String pw;
    private String content;
    private String regDate;
}
