package com.basic.spring_basic_task.dto;

import com.basic.spring_basic_task.entity.ApiTest;
//import com.basic.spring_basic_task.entity.Test;
import lombok.Getter;

@Getter
public class TestDto {
    private String name;
    private String pw;

    public TestDto(ApiTest test){
        this.name= test.getName();
        this.pw=test.getPw();
    }

//    public void setTestDto(Test test){
//        this.name= test.getName();
//        this.pw=test.getPw();
//
//    }

}
