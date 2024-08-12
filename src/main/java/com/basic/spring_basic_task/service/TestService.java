package com.basic.spring_basic_task.service;


import com.basic.spring_basic_task.dto.RequestTestDto;
import com.basic.spring_basic_task.dto.ResponseTestDto;
import com.basic.spring_basic_task.entity.ApiTest;
import com.basic.spring_basic_task.repository.TestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    private final TestRepository testRepository;


    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    private List<ResponseTestDto> getAll(){
        return testRepository.findTest();
    }

    public void addTest(ApiTest test){
        testRepository.save(test);
    }


    public List<ResponseTestDto> getSome(RequestTestDto requestTestDto){

        ApiTest apiTest = new ApiTest(requestTestDto);

        if(requestTestDto.getName()==null&&requestTestDto.getPw()==null){
            return testRepository.findTest();
        }
        else if(requestTestDto.getName()==null){
            return testRepository.findPwTest(apiTest);


        }
        else if(requestTestDto.getPw()==null){
            return testRepository.findNameTest( apiTest);

        }
        else{
            return testRepository.findSingleTest(apiTest);


        }
    }








}
