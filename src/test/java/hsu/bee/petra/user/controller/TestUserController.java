package hsu.bee.petra.user.controller;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.*;
import static com.epages.restdocs.apispec.ResourceDocumentation.*;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static util.ApiDocumentUtils.*;

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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.google.gson.Gson;

import hsu.bee.petra.response.ResponseCode;
import hsu.bee.petra.response.ResponseMessage;
import hsu.bee.petra.user.dto.LogInDto;
import hsu.bee.petra.user.repository.UserRepository;
import hsu.bee.petra.user.service.UserService;

@WebMvcTest(UserController.class)
@AutoConfigureRestDocs
public class TestUserController {

	@MockBean
	private UserRepository userRepository;

	@MockBean
	private UserService userService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("쿠키발급 성공 테스트")
	public void TestSignIn() throws Exception {
		// given
		LogInDto logInDto = logInDto();
		doNothing().when(userService).checkUserId(any(LogInDto.class));

		// when
		ResultActions result = mockMvc.perform(
				RestDocumentationRequestBuilders.post("/login")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new Gson().toJson(logInDto))
		);

		// then
		result.andExpect(status().isOk())
			.andExpect(jsonPath("code").value(ResponseCode.SUCCESS))
			.andExpect(jsonPath("message").value(ResponseMessage.SUCCESS))
			/*.andDo(document("LogIn and Get Cookie",
				requestFields(
					fieldWithPath("id").description("사용자 ID"),
					fieldWithPath("name").description("사용자 이름").optional()
				),
				responseFields(
					fieldWithPath("code").description("응답코드"),
					fieldWithPath("message").description("응답메시지")
				))
			)*/
			.andDo(document("LogIn and Get Cookie",
				getDocumentRequest(),
				getDocumentResponse(),
				resource(
					ResourceSnippetParameters.builder()
						.description("로그인 후 쿠키를 발급받을 수 있습니다.")
						.summary("로그인 후 쿠키 발급")
						/*.requestFields(
							fieldWithPath("id").description("사용자 ID"),
							fieldWithPath("name").description("사용자 이름").optional()
						)*/
						.responseFields(
							fieldWithPath("code").description("응답코드"),
							fieldWithPath("message").description("응답메시지")
						).build()
				))
			);
	}

	// @Test
	// @DisplayName("id 미입력으로 쿠키 발급 실패")
	// public void TestSignInToFail() throws Exception {
	// 	// given
	// 	LogInDto logInDto = logInDto();
	// 	logInDto.setId(null);
	// 	doThrow(new IllegalStateException("FAIL to checkUserId(LogInDto user)!")).when(userService)
	// 		.checkUserId(any(LogInDto.class));
	//
	// 	// when
	// 	ResultActions resultActions = mockMvc.perform(
	// 		MockMvcRequestBuilders.post("/login")
	// 			.contentType(MediaType.APPLICATION_JSON)
	// 			.content(new Gson().toJson(logInDto))
	// 	);
	//
	// 	// them
	//
	// }

	private LogInDto logInDto() {
		LogInDto logInDto = new LogInDto();
		logInDto.setId("heather");
		logInDto.setName("한인주");
		return logInDto;
	}
}