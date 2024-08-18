package com.basic.spring_basic_task.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleSingleDto {
    int scheduleId;
    int assigneeId;
    String assigneeName;
    String email;
    String content;
    String regDate;
    String modDate;


    public String data() {
        return "{ \"scheduleId\": " + this.scheduleId + ", " +
                "\"assignee\": \"" + this.assigneeId + "\", " +
                "\"content\": \"" + this.content + "\", " +
                "\"regDate\": \"" + this.regDate + "\", " +
                "\"modDate\": \"" + this.modDate + "\" }";
    }
}
