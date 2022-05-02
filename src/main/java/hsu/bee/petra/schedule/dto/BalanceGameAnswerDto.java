package hsu.bee.petra.schedule.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class BalanceGameAnswerDto {

    @NotNull(message="userId가 필요합니다.")
    String id;

    @NotNull(message="유저의 응답 결과가 필요합니다.")
    String[] answer;

}
