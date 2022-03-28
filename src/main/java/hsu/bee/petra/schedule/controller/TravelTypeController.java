package hsu.bee.petra.schedule.controller;

import hsu.bee.petra.code.entity.TravelCode;
import hsu.bee.petra.schedule.dto.BalanceGameAnswerDto;
import hsu.bee.petra.schedule.dto.TravelCodeDto;
import hsu.bee.petra.schedule.service.TravelTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class TravelTypeController {

    private final TravelTypeService travelTypeService;

    @PostMapping("/travel-types")
    public TravelCodeDto grantTravelType(@RequestBody BalanceGameAnswerDto answer) {

        /*
         [컨트롤러] 프론트 -> 컨트롤러 (user id + 질문 답 ) 전송
         [컨트롤러] 컨트롤러에서 서비스로 함수(user id + 질문 답) 호출 -> 여행 타입 계산, user 테이블에 저장
         [서비스] 서비스 함수에서 유저 답 계산해서 max 값인 여행 타입 계산
         [서비스] 계산한 여행 값 repo에 저장
         [서비스] 컨트롤러에 리턴
         [컨트롤러] 리턴값 받아서 json으로 전송
         */

        TravelCode travelCode = travelTypeService.setTravelType(answer.getId(), answer.getAnswer());

        //return new TravelCodeDto(travelCode.getCode(), t)
    }
}
