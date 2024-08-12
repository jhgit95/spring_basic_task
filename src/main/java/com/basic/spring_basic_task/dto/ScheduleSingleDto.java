package com.basic.spring_basic_task.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleSingleDto {
    int id;
    String assignee;
    String content;
    String reg_date;
    String mod_date;

}
