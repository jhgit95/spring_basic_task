package com.basic.spring_basic_task.dto;

import com.basic.spring_basic_task.entity.ApiTest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseTestDto {
    private String name;
    private String pw;

    public ResponseTestDto(ApiTest test){
        this.name= test.getName();
        this.pw=test.getPw();
    }
    public ResponseTestDto(String name, String pw){
        this.name= name;
        this.pw=pw;
    }
    public ResponseTestDto(){
    }

//    public void setTestDto(Test test){
//        this.name= test.getName();
//        this.pw=test.getPw();
//
//    }

}
