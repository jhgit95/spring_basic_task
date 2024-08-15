package com.basic.spring_basic_task.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleSingleDto {
    int scheduleId;
    String assignee;
    String content;
    String reg_date;
    String mod_date;

    public String data(){
        return "{ \"scheduleId\": " + this.scheduleId + ", " +
                "\"assignee\": \"" + this.assignee + "\", " +
                "\"content\": \"" + this.content + "\", " +
                "\"regDate\": \"" + this.reg_date + "\", " +
                "\"modDate\": \"" + this.mod_date + "\" }";
    }
}
