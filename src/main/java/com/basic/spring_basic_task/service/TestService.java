package com.basic.spring_basic_task.service;


import com.basic.spring_basic_task.entity.Test;
import com.basic.spring_basic_task.repository.TestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    private final TestRepository testRepository;


    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    private List<Test> getAll(){
        return testRepository.findTest();
    }

    public void addTest(Test test){
        testRepository.save(test);
    }








}
