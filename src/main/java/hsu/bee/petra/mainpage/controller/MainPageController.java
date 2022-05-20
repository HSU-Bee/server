package hsu.bee.petra.mainpage.controller;

import hsu.bee.petra.response.Response;
import hsu.bee.petra.response.ResponseCode;
import hsu.bee.petra.response.ResponseMessage;
import hsu.bee.petra.schedule.dto.OtherUserScheduleDto;
import hsu.bee.petra.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
public class MainPageController {

    private final ScheduleService scheduleService;

    @GetMapping("/mainpage/famous-schedules")
    public Response getFamousSchedules() {

        ArrayList<OtherUserScheduleDto> result = scheduleService.getFamousSchedule();

        return new Response(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, result);
    }

    @GetMapping("/mainpage/newest-schedules")
    public Response getNewestSchedules() {

        // 0페이지 10개 가져오기
        PageRequest pageRequest = PageRequest
                .of(0, 10)
                .withSort(Sort.Direction.DESC, "createdAt");
        ArrayList<OtherUserScheduleDto> result = scheduleService.getNewestSchedule(pageRequest);

        return new Response(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, result);
    }

    @GetMapping("/mainpage/long-period-schedules")
    public Response getLongestPeriodSchedules() {

        ArrayList<OtherUserScheduleDto> result = scheduleService.getLongestTimeSchedule();

        return new Response(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, result);
    }

    @GetMapping("/mainpage/long-distance-schedules")
    public Response getLongestDistanceSchedules() {
        int page = 0;
        int size = 10;
        ArrayList<OtherUserScheduleDto> result = scheduleService.getLongestDistanceSchedule(size, page);

        return new Response(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, result);
    }
}
