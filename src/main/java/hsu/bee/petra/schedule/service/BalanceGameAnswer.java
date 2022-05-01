package hsu.bee.petra.schedule.service;

import hsu.bee.petra.schedule.dto.TravelCodeDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BalanceGameAnswer {

    private static final ArrayList<String> q1 = new ArrayList<>(Arrays.asList("6","3","7"));
    private static final ArrayList<String> q2 = new ArrayList<>(Arrays.asList("4","3","5"));
    private static final ArrayList<String> q3 = new ArrayList<>(Arrays.asList("53","4","0"));
    private static final ArrayList<String> q4 = new ArrayList<>(Arrays.asList("12789","0","46"));
    private static final ArrayList<String> q5 = new ArrayList<>(Arrays.asList("1","2","89"));
    private static final ArrayList<String> q6 = new ArrayList<>(Arrays.asList("5","267","0"));
    private static final ArrayList<String> q7 = new ArrayList<>(Arrays.asList("1","8","9"));
    private static final ArrayList<String> q8 = new ArrayList<>(Arrays.asList("145","89","6"));
    private static final ArrayList<String> q9 = new ArrayList<>(Arrays.asList("7","",""));
    private static final ArrayList<String> q10 = new ArrayList<>(Arrays.asList("3","2","0"));

    static Map<Integer, ArrayList<String>> questions = new HashMap<>() {{
        put(1, q1);
        put(2, q2);
        put(3, q3);
        put(4, q4);
        put(5, q5);
        put(6, q6);
        put(7, q7);
        put(8, q8);
        put(9, q9);
        put(10, q10);
    }};

    static final TravelCodeDto[] infoList = {
                new TravelCodeDto("루피너스<삶의 욕구>",
                    "산해진미는 다 내꺼!! 여행은 자고로 먹으러 다니는 것이지~! 금강산도 식후경 먹는 것이 가장 중요한 먹보 미식가인 당신!" +
                            " 루피너스의 꽃말 삶의 욕구와 정말 잘 어울리네요~ 꼬르륵... 배가 고픈 것은 못 참는 성격이지 않으신가요? 유명한 곳이나 먹고 싶은 것은 꼭 먹어봐야 직성이 풀립니다. 나도 어디서 꿀리진 않아~ 어디에서도 맛에 관해서는 꿀리지 않죠! 당신에게 추천하는 여행은 먹는 것 위주의 먹방 여행입니다.)",
                    "https://petra-bucket.s3.ap-northeast-2.amazonaws.com/psychology-test/%EB%A9%A7%EB%B0%AD%EC%A5%90.jpg"),
            new TravelCodeDto("샤프란<아름다운 청춘>",
                    "안녕히 계세요 여러분 전 세상의 모든 굴레와 속박을 벗어던지고 제 행복을 찾아 떠납니다~! 샤프란의 꽃말 아름다운 청춘과 정말 잘 어울네요. 당신의 마음에 걸리는 것은 참을 수 없는 당신!! 화끈한 성격의 소유자인 당신은 고민보다는 Go! 호탕한 성격을 가지고 있어요. 홧김에 물건을 사거나 화끈하고 갑작스럽게 " +
                            "여행을 떠나는 경향이 있습니다. 불타는 청춘을 즐긴는 당신은 때로는 갑작스러운 여행에 행복을 느낀답니다. 당신에게 추천하는 여행은 축제, 밤바다, 서핑입니다.",
                    "https://petra-bucket.s3.ap-northeast-2.amazonaws.com/psychology-test/%EB%A9%A7%EB%B0%AD%EC%A5%90.jpg"),
            new TravelCodeDto("양귀비<위로, 위안>",
                    "도비 이즈 프리!! 도비는 자유를 찾아 떠나요~ 양귀비의 꽃말 위로, 위안과 잘 어울리는 당신! 여행을 떠날때는 주로 위로와 위안을 찾아 떠납니다. 당신은 때로는 힘든 생기면 도피하고 싶은 경향이 있습니다. 힘든 일상으로부터 도망치듯이 여행을 떠나는 당신은 여행에서 휴식과 편안함을 가장 중요하게 여기네요." +
                            " 당신에게 추천하는 여행은 주로 호캉스, 스파, 식물원 등이 있습니다~",
                    "https://petra-bucket.s3.ap-northeast-2.amazonaws.com/psychology-test/%EB%A9%A7%EB%B0%AD%EC%A5%90.jpg"),
            new TravelCodeDto("붉은 장미<열정>",
                    "난 여행에서도 배울점을 찾지 탐험하면서 나만의 길을 간다. 붉은 장미의 꽃말 열정과 잘 어울리는 당신! 여행을 떠날때는 주로 배움을 찾아서 떠납니다. 많은 사람들을 많나보기 위해서나 기존과 다른 곳에서 다른 풍경, 다른 문화들을 배우러 여행을 가지는 않나요? 스스로 찾아서 가는 것을 좋아하겠지만" +
                            " 굳이 추천해드리자면 당신에게 잘 어울리는 여행은 박물관, 유적지, 문화체험관 등이 있습니다.",
                    "https://petra-bucket.s3.ap-northeast-2.amazonaws.com/psychology-test/%EB%A9%A7%EB%B0%AD%EC%A5%90.jpg"),
            new TravelCodeDto("원추리<매혹>",
                    "내가 제일 잘나가~! 여행은 자고로 사진을 위한 것이지 난 핫한 사람이니까. 원추리의 꽃말 매혹과 잘 어울리는 당신! 당신은 화려하게 꾸미고 사진을 찍고 어여쁜 당신의 모습을 보는 것을 즐깁니다. 주로 주변 사람들의 평판에 신경을 많이 쓰는 편이며," +
                            " 반짝거리는 예쁜 것들을 특히 좋아하는 경향이 있네요. 당신에게 추천하는 여행은 뮤지엄, 전시회, 바다, 예술랜드 등이 있습니다.",
                    "https://petra-bucket.s3.ap-northeast-2.amazonaws.com/psychology-test/%EB%A9%A7%EB%B0%AD%EC%A5%90.jpg"),
            new TravelCodeDto("프리지아<새로운 도전>",
                    "나를 막을 것은 없다!! 도전은 나의 삶이지 짜릿한 도전을 즐기는 당신~! 프리지아의 꽃말 새로운 도전과 잘 어울리는 당신! 여행을 떠날때는 자극적이고 일상에서 만날 수 없는 체험을 하기 위해서 떠납니다. 도전 정신이 뛰어난 당신은 새로운 길을 개척해서" +
                            " 여행을 가고 스릴만점 액티비티 여행을 즐기네요.  당신에게 추천하는 여행은 익스트림체험, 놀이공원, 등산 등이 있습니다.",
                    "https://petra-bucket.s3.ap-northeast-2.amazonaws.com/psychology-test/%EB%A9%A7%EB%B0%AD%EC%A5%90.jpg"),
            new TravelCodeDto("로즈메리<기억>",
                    "두 유 리멤버 시원한 바람 속에~~ 여행은 추억을 위한 것이지. 로즈메리의 꽃말 기억과 잘 어울리는 당신! 여행을 떠날때는 주로 가족, 친구, 연인과의 소중한 추억을 만들기 위해서 떠나네요. 당신은 주변 사람들을 소중히 여기고 주변 사람들의 행복에 관심이 많습니다." +
                            " 주변 사람과의 여행이 소중한 당신에게 추천하는 여행은 체험식 여행, 지역관광, 별보러가기, 차박 등이 있습니다.",
                    "https://petra-bucket.s3.ap-northeast-2.amazonaws.com/psychology-test/%EB%A9%A7%EB%B0%AD%EC%A5%90.jpg"),
            new TravelCodeDto("캐모마일<숨겨진 아름다움>",
                    "이 퓨 원어 프리티 에블 원어 프리티 어디서나 당당하게 걷기 혼자서도 당당하게 자신에게 집중하는 여행합니다. 혼자서 여행하며 자신의 숨겨진 아름다움을 찾는 캐모마일과 잘 어울리는 당신! 혼자서는 순탄하지 않은 외길을 당당하게 떠날 수 " +
                            "있는 용감함과 높은 자신감을 지니고 있습니다. 당신에게 추천하는 여행은 전시회, 산책, 호캉스 등이 있습니다.",
                    "https://petra-bucket.s3.ap-northeast-2.amazonaws.com/psychology-test/%EB%A9%A7%EB%B0%AD%EC%A5%90.jpg"),
            new TravelCodeDto("라벤더<기대>",
                    "어딘가에는 나를 위한 파라다이스가 있겠지. 항상 기대를 품고 살아가는 라벤더와 잘 어울리는 당신! 여행을 떠날때는 항상 행복의 기대를 품고 떠나네요. 주로 착하다는 말을 많이 들으며 소심한 성격을 지니고 있습니다. 그리고 힘든 일이 있어도 긍정적으로 " +
                            "생각하며 극복해 나갑니다. 당신에게 추천하는 여행은 별보기, 호캉스, 뮤지엄, 바다, 문화체험등이 있습니다.",
                    "https://petra-bucket.s3.ap-northeast-2.amazonaws.com/psychology-test/%EB%A9%A7%EB%B0%AD%EC%A5%90.jpg"),
            new TravelCodeDto("메리골드<반드시 오고야말 행복>",
                    "숨쉬고 싶어 이곳이 싫어형 답답한 이곳을 떠나 어디로든 좋다~!! 힘든 일상에 지쳐있는 당신! 반드시 오고야말 행복과 잘 어울리네요. 주로 호구 같다는 말을 꽤 듣네요. 하고 싶은 말을 잘 못하고 그냥 참아버리는 경우가 많습니다. 주로 여행은 힘든 일이 " +
                            "있으면 피난처로 떠나는 경우가 많아요. 당신에게 추천하는 여행은 별보기, 스파, 식물원, 놀이공원등이 있습니다.",
                    "https://petra-bucket.s3.ap-northeast-2.amazonaws.com/psychology-test/%EB%A9%A7%EB%B0%AD%EC%A5%90.jpg")
    };


}
