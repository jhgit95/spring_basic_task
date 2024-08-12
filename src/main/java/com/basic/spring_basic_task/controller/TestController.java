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
        return testService.getAll();
    }

    @PostMapping("/post")
    public String addPost(@RequestBody RequestTestDto requestTestDto){
        testService.addTest(requestTestDto);
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

    // 이거 아이디 넣어서하면 하나 특정해서 가능하니까 그렇게 하지
    @PutMapping("/put")
    public int updateTest(@RequestBody RequestTestDto requestTestDto){
        return testService.updateTestService(requestTestDto);
    }

    @DeleteMapping("/delete")
    public int deleteTest(@RequestBody TestDeleteDto deleteDto){
        return testService.deleteTestService(deleteDto);
    }




}
