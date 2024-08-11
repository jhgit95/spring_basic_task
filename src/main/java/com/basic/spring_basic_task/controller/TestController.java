package com.basic.spring_basic_task.controller;

import com.basic.spring_basic_task.entity.Test;
import com.basic.spring_basic_task.repository.TestRepository;
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


    public TestController(TestRepository testRepository) {
        this.testRepository = testRepository;
    }


    @GetMapping("/get")
    public List<Test> getTest(){
        return testRepository.findTest();
    }

    @PostMapping("/post")
    public String addPost(@RequestBody Test test){
        testRepository.save(test);
        return "test 등록 완료";
    }


}
