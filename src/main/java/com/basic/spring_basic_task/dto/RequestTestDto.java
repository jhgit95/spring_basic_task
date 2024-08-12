package com.basic.spring_basic_task.dto;

import com.basic.spring_basic_task.entity.ApiTest;
//import com.basic.spring_basic_task.entity.Test;
import lombok.Getter;

@Getter
public class RequestTestDto {
    private String name;
    private String pw;

    public RequestTestDto(ApiTest test){
        this.name= test.getName();
        this.pw=test.getPw();
    }
    public RequestTestDto(String name, String pw){
        this.name= name;
        this.pw=pw;
    }

//    public void setTestDto(Test test){
//        this.name= test.getName();
//        this.pw=test.getPw();
//
//    }

}
