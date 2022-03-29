package hsu.bee.petra.schedule.service;

import hsu.bee.petra.code.entity.TravelCode;
import hsu.bee.petra.schedule.dto.TravelCodeDto;
import hsu.bee.petra.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly=true)
@Service
public class TravelCodeService {

    private final UserRepository userRepository;

    @Transactional
    public TravelCodeDto createTravelCode(String userId, String[] answer) {

        /*
        여행 타입 계산
        여행 타입 저장
        여행 타입dto 리턴
         */

        // 유저 타입 계산
        int[] answerNum = new int[answer.length];

        for(int i=0; i<answer.length; i++) {

            // a,b,c 중 해당하는 답에 대한 타입 가져오기
            String type = BalanceGameAnswer.questions
                    .get(i+1) //
                    .get(answer[i].charAt(0)-'a');

            for(int j=0;j<type.length(); j++) {
                ++answerNum[type.charAt(j)-'0'];
            }
        }

        int max = 0;
        for(int i=0; i<answerNum.length; i++) {
            System.out.print(i + " : " + answerNum[i]);
            if(answerNum[max] < answerNum[i])
                max = i;
        }

        // 유저 여행 타입 계산 완료
        TravelCodeDto userType = BalanceGameAnswer.infoList[max];

        // 유저에 여행 타입 저장
        //userRepository.saveTravelCode(userId, new TravelCode());

        // 여행 타입dto 리턴
        return userType;
    }


}
