package hsu.bee.petra.mypage.controller;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import hsu.bee.petra.mypage.dto.MyPageDto;
import hsu.bee.petra.response.ResponseCode;
import hsu.bee.petra.response.ResponseMessage;
import hsu.bee.petra.user.entity.User;
import hsu.bee.petra.user.repository.UserRepository;
import hsu.bee.petra.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.servlet.http.Cookie;

import java.util.Optional;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static util.ApiDocumentUtils.getDocumentRequest;
import static util.ApiDocumentUtils.getDocumentResponse;

@WebMvcTest(controllers = MyPageController.class)
@AutoConfigureRestDocs
class MyPageControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserService userService;

    @Test
    @DisplayName("mypage 그리기")
    public void testGetMyPage() throws Exception {


        //given
        Cookie cookie = new Cookie("userCookie", "park1");
        MyPageDto myPageDto = new MyPageDto("park1", 16L, 0L);

        User user = new User("park1", "park", null, null, null);

        doReturn(Optional.of(user)).when(userRepository).findById("park1");
        doReturn(myPageDto).when(userService).getMyPageInfo(user);
        //when
        ResultActions result = mockMvc.perform(
                RestDocumentationRequestBuilders.get("/mypage")
                        .cookie(cookie)
        );

        //then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("code").value(ResponseCode.SUCCESS))
                .andExpect(jsonPath("message").value(ResponseMessage.SUCCESS))
                .andDo(document("마이페이지 조회",getDocumentRequest(),
                        getDocumentResponse(),
                        resource(
                                ResourceSnippetParameters.builder()
                                        .description("mypage에서 사용자, 스케줄 개수, 리뷰 개수를 조회합니다.")
                                        .summary("mypage조회")
                                        .responseFields(
                                                fieldWithPath("code").description("응답코드"),
                                                fieldWithPath("message").description("응답메시지"),
                                                fieldWithPath("data.userId").description("내 id"),
                                                fieldWithPath("data.myScheduleCount").description("내가 작성한 schedule 수"),
                                                fieldWithPath("data.myReviewCount").description("내가 작성한 review 수")
                                        ).build()
                        )
                        ));

    }
}