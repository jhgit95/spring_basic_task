package com.basic.spring_basic_task.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleSingleDto {
    int scheduleId;
    String assignee;
    String content;
    String regDate;
    String modDate;

    public String data() {
        return "{ \"scheduleId\": " + this.scheduleId + ", " +
                "\"assignee\": \"" + this.assignee + "\", " +
                "\"content\": \"" + this.content + "\", " +
                "\"regDate\": \"" + this.regDate + "\", " +
                "\"modDate\": \"" + this.modDate + "\" }";
    }
}
