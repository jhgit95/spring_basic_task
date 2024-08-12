package com.basic.spring_basic_task.controller;

import com.basic.spring_basic_task.dto.RequestTestDto;
import com.basic.spring_basic_task.dto.ResponseTestDto;
import com.basic.spring_basic_task.dto.TestDto;
import com.basic.spring_basic_task.entity.ApiTest;
import com.basic.spring_basic_task.entity.ApiTest;
import com.basic.spring_basic_task.repository.TestRepository;
import com.basic.spring_basic_task.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
@Service
public class TestController {

    @Autowired
    private final TestRepository testRepository;
    @Autowired
    private TestService testService;


    public TestController(TestRepository testRepository) {
        this.testRepository = testRepository;
    }


    @GetMapping("/get/all")
    public List<ResponseTestDto> getAllTest(){
        return testRepository.findTest();
    }

    @PostMapping("/post")
    public String addPost(@RequestBody ApiTest apiTest){
        testRepository.save(apiTest);
        return "test 등록 완료";
    }

    @PostMapping("/post/post")
    public String postTest(){
        return "post/post test";
    }

    @GetMapping("/get/some")
    public List<ResponseTestDto> getSomeTest(@RequestBody RequestTestDto requestTestDto){
        return testService.getSome(requestTestDto);
    }

    // 바디에 들어온 값에 따라서 다르게 출력해줄 수 있는 기능 과제 3단계
    // name or pw 에 특정값을 넣어서 조회하는 법
//    @GetMapping("/get/some")
//    public List<Test> getSomeTest(){
//        return testRepository.someTest();
//    }
//    public String getSomeTest(){
//        return testRepository.someTest();
//    }


    // 이미 있는 내용을 수정하는 기능

    // 이미 있는 내용을 삭제하는 기능




}
