package com.basic.spring_basic_task.entity;

import com.basic.spring_basic_task.dto.RequestTestDto;
import com.basic.spring_basic_task.dto.TestDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiTest {
    private String name;
    private String pw;

    public ApiTest(RequestTestDto requestTestDto) {
        this.name=requestTestDto.getName();
        this.pw=requestTestDto.getPw();
    }
}