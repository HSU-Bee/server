package hsu.bee.petra.mainpage.controller;

import hsu.bee.petra.response.Response;
import hsu.bee.petra.response.ResponseCode;
import hsu.bee.petra.response.ResponseMessage;
import hsu.bee.petra.schedule.dto.OtherUserScheduleDto;
import hsu.bee.petra.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
public class MainPageController {

    private final ScheduleService scheduleService;

    @GetMapping("/mainpage/famous-schedules")
    public Response getFamousSchedules(@RequestParam(value="page", required=false, defaultValue= "0") Integer page) {

        int size = 10;

        ArrayList<OtherUserScheduleDto> result = scheduleService.getFamousSchedules(size, page);

        return new Response(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, result);
    }

    @GetMapping("/mainpage/newest-schedules")
    public Response getNewestSchedules(@RequestParam(value="page", required=false, defaultValue= "0") Integer page) {

        int size = 10;

        ArrayList<OtherUserScheduleDto> result = scheduleService.getNewestSchedules(size, page);

        return new Response(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, result);
    }

    @GetMapping("/mainpage/long-period-schedules")
    public Response getLongestPeriodSchedules(@RequestParam(value="page", required=false, defaultValue= "0") Integer page) {

        int size = 10;

        ArrayList<OtherUserScheduleDto> result = scheduleService.getLongestPeriodSchedules(size, page);

        return new Response(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, result);
    }

    @GetMapping("/mainpage/long-distance-schedules")
    public Response getLongestDistanceSchedules(@RequestParam(value="page", required=false, defaultValue= "0") Integer page) {

        int size = 10;

        ArrayList<OtherUserScheduleDto> result = scheduleService.getLongestDistanceSchedules(size, page);

        return new Response(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, result);
    }
}
