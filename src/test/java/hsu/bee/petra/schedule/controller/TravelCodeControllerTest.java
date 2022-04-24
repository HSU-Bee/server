package hsu.bee.petra.schedule.controller;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.google.gson.Gson;
import hsu.bee.petra.home.controller.HomeController;
import hsu.bee.petra.schedule.dto.BalanceGameAnswerDto;
import hsu.bee.petra.schedule.dto.TravelCodeDto;
import hsu.bee.petra.schedule.service.TravelCodeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static util.ApiDocumentUtils.getDocumentRequest;
import static util.ApiDocumentUtils.getDocumentResponse;

@WebMvcTest(controllers = TravelCodeController.class)
@AutoConfigureRestDocs
class TravelCodeControllerTest {

    @MockBean
    private TravelCodeService travelCodeService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("사용자 TravelCode 생성 테스트")
    public void testGrantTravelCode() throws Exception {

        //given
        BalanceGameAnswerDto balanceGameAnswerDto = createAnswer();
        TravelCodeDto travelCodeDto = new TravelCodeDto("양귀비<위로, 위안>","도비 이즈 프리!! 도비는 자유를 찾아 떠나요~ 양귀비의 꽃말 위로, 위안과 잘 어울리는 당신! 여행을 떠날때는 주로 위로와 위안을 찾아 떠납니다. 당신은 때로는 힘든 생기면 도피하고 싶은 경향이 있습니다. 힘든 일상으로부터 도망치듯이 여행을 떠나는 당신은 여행에서 휴식과 편안함을 가장 중요하게 여기네요. 당신에게 추천하는 여행은 주로 호캉스, 스파, 식물원 등이 있습니다~",
                "https://petra-bucket.s3.ap-northeast-2.amazonaws.com/psychology-test/%EB%A9%A7%EB%B0%AD%EC%A5%90.jpg");

        given(travelCodeService.createTravelCode("park1", new String[]{
                "b", "b", "b", "b", "b", "b", "b", "b", "b", "b"
        })).willReturn(travelCodeDto);

        //when
        ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders.post("/users/travel-types")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(balanceGameAnswerDto))
        );

        //then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("typeName").value("양귀비<위로, 위안>"))
                .andExpect(jsonPath("typeDesc").value("도비 이즈 프리!! 도비는 자유를 찾아 떠나요~ 양귀비의 꽃말 위로, 위안과 잘 어울리는 당신! 여행을 떠날때는 주로 위로와 위안을 찾아 떠납니다. 당신은 때로는 힘든 생기면 도피하고 싶은 경향이 있습니다. 힘든 일상으로부터 도망치듯이 여행을 떠나는 당신은 여행에서 휴식과 편안함을 가장 중요하게 여기네요. 당신에게 추천하는 여행은 주로 호캉스, 스파, 식물원 등이 있습니다~"))
                .andExpect(jsonPath("typeImageUrl").value("https://petra-bucket.s3.ap-northeast-2.amazonaws.com/psychology-test/%EB%A9%A7%EB%B0%AD%EC%A5%90.jpg"))
                .andDo(document("Grant TravelType",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        resource(
                                ResourceSnippetParameters.builder()
                                        .description("사용자의 응답을 기반으로 여행 타입을 부여합니다.")
                                        .summary("여행 타입 부여")
                                        .requestFields(
                                                //typeName, typeDesc, typeImageUrl
                                                fieldWithPath("id").description("사용자 ID"),
                                                fieldWithPath("answer").description("밸런스 게임 응답")
                                        )
                                        .responseFields(
                                                fieldWithPath("typeName").description("여행 타입 이름"),
                                                fieldWithPath("typeDesc").description("여행 타입 설명"),
                                                fieldWithPath("typeImageUrl").description("여행 타입 이미지")
                                        ).build()
                        ))
                );

    }

    private BalanceGameAnswerDto createAnswer() {
        BalanceGameAnswerDto bgAnswer = new BalanceGameAnswerDto();
        bgAnswer.setId("park1");
        bgAnswer.setAnswer(new String[]{
                "b", "b", "b", "b", "b", "b", "b", "b", "b", "b"
        });

        return bgAnswer;
    }

}