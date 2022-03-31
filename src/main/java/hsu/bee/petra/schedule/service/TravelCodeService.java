package hsu.bee.petra.schedule.service;

import hsu.bee.petra.code.entity.TravelCode;
import hsu.bee.petra.code.repository.TravelCodeRepository;
import hsu.bee.petra.schedule.dto.TravelCodeDto;
import hsu.bee.petra.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly=true)
@Service
public class TravelCodeService {

    private final UserRepository userRepository;
    private final TravelCodeRepository travelCodeRepository;

    @Transactional
    public TravelCodeDto createTravelCode(String userId, String[] answer) {

        // 유저 타입 계산
        int[] answerNum = new int[answer.length];

        for(int i=0; i<answer.length; i++) {

            String type = BalanceGameAnswer.questions
                    .get(i+1) //
                    .get(answer[i].charAt(0)-'a');

            for(int j=0;j<type.length(); j++) {
                ++answerNum[type.charAt(j)-'0'];
            }
        }

        int max = 0;
        for(int i=0; i<answerNum.length; i++) {
            if(answerNum[max] < answerNum[i])
                max = i;
        }

        // 유저 여행 타입 계산 완료
        TravelCodeDto userType = BalanceGameAnswer.infoList[max];

        // 테이블에서 해당하는 여행 타입 저장
        List<TravelCode> codeList = travelCodeRepository.findByCode(userType.getTypeName());

        userRepository.saveTravelCode(userId, codeList.get(0));

        // 여행 타입dto 리턴
        return userType;
    }

}
