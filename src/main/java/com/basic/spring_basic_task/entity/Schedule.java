package com.basic.spring_basic_task.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Schedule{
    private int scheduleId;
    private String assignee;
    private String pw;
    private String content;
    private String regDate;
    private String modDate;

}
