package com.basic.spring_basic_task.service;

import com.basic.spring_basic_task.dto.*;
import com.basic.spring_basic_task.entity.Schedule;
import com.basic.spring_basic_task.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public List<ScheduleResponseDto> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public int addSchedule(ScheduleAddDto AddDto) {
        return scheduleRepository.save(AddDto);
    }

    public ScheduleSingleDto getSingleSchedule(int id){
        return scheduleRepository.getSingleSchedule(id);
    }

    // 여기서 겟 바디 안의 값에 따라서 분기처리 해줘야 돼
    // 일단 수정일 기준으로 조회한 것은 완료~
    // 담당자명, 담당자명+수정일, 아무것도 없는 경우
    // 이렇게 3가지 더 만들면 3단계도 완료 ~
    public List<ScheduleResponseDto> getScheduleSearch(ScheduleSearchDto searchDto){
//        return scheduleRepository.getScheduleSearch(searchDto);
        System.out.println(searchDto.getAssignee());
        System.out.println(searchDto.getMod_date());

        Schedule scd = new Schedule(searchDto);

        if(searchDto.getMod_date()==null&&searchDto.getAssignee()==null){
            System.out.println("담당자와 수정일이 전부 null");
            return scheduleRepository.findAll();
        }
        else if(searchDto.getMod_date()==null){
            System.out.println("수정일이 null");
            return scheduleRepository.getScheduleSearchAssignee(scd);


        }
        else if(searchDto.getAssignee()==null){
            System.out.println("담당자가 null");
            return scheduleRepository.getScheduleSearch( scd);

        }
        else{
            System.out.println("둘 다 값이 있을 경우");
            return scheduleRepository.getScheduleSearchAssigneeMod(scd);


        }
    }

    // 선택한 일정 수정
    public int updateSchedule(ScheduleRequestDto sReqDto){
        System.out.println(scheduleRepository.idPwCheck(sReqDto));

        if(scheduleRepository.idPwCheck(sReqDto)==0){
            return 500;
        }




        // 할 일과 담당자 수정
        if(sReqDto.getContent()!=null && sReqDto.getAssignee()!=null){
            System.out.println("할 일과 담당자 변경");
            System.out.println(scheduleRepository.updateScheduleAssigneeContent(sReqDto));
            return scheduleRepository.updateScheduleAssigneeContent(sReqDto);
        }
        // 담당자만 수정
        else if(sReqDto.getAssignee()!=null){
            System.out.println("담당자 변경");
            return scheduleRepository.updateScheduleAssignee(sReqDto);


        }
        // 할 일만 수정
        else if(sReqDto.getContent()!=null){
            System.out.println("할 일 변경");
            return scheduleRepository.updateScheduleContent(sReqDto);

        }
        else{
            return 500;
        }

    }



    // 선택한 일정 삭제












}
