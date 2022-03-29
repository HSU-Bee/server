package hsu.bee.petra.schedule.controller;

import hsu.bee.petra.schedule.dto.BalanceGameAnswerDto;
import hsu.bee.petra.schedule.dto.TravelCodeDto;
import hsu.bee.petra.schedule.service.TravelCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


@RequiredArgsConstructor
@RestController
public class TravelCodeController {

    private final TravelCodeService travelTypeService;

    @PostMapping("/travel-types")
    public TravelCodeDto grantTravelCode(@RequestBody BalanceGameAnswerDto answer) {

        // user의 성향 계산
        TravelCodeDto travelCodeDto = travelTypeService.createTravelCode(answer.getId(), answer.getAnswer());

        return travelCodeDto;
    }
}
