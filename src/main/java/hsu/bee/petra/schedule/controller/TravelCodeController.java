package hsu.bee.petra.schedule.controller;

import hsu.bee.petra.response.Response;
import hsu.bee.petra.response.ResponseCode;
import hsu.bee.petra.response.ResponseMessage;
import hsu.bee.petra.schedule.dto.BalanceGameAnswerDto;
import hsu.bee.petra.schedule.dto.TravelCodeDto;
import hsu.bee.petra.schedule.service.TravelCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class TravelCodeController {

    private final TravelCodeService travelCodeService;

    @PostMapping("/users/travel-types")
    public Response<TravelCodeDto> grantTravelCode(@Valid @RequestBody BalanceGameAnswerDto answer) {

        // user의 성향 계산
        TravelCodeDto travelCodeDto = travelCodeService.createTravelCode(answer.getId(), answer.getAnswer());

        return new Response(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, travelCodeDto);
    }
}
