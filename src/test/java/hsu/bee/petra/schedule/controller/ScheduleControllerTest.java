package hsu.bee.petra.schedule.controller;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.*;
import static com.epages.restdocs.apispec.ResourceDocumentation.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static util.ApiDocumentUtils.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.Cookie;

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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.google.gson.Gson;

import hsu.bee.petra.attraction.dto.AttractionDto;
import hsu.bee.petra.response.ResponseCode;
import hsu.bee.petra.response.ResponseMessage;
import hsu.bee.petra.schedule.dto.BasicScheduleInfoDto;
import hsu.bee.petra.schedule.dto.CopyScheduleDto;
import hsu.bee.petra.schedule.dto.PlanDto;
import hsu.bee.petra.schedule.dto.ScheduleDto;
import hsu.bee.petra.schedule.service.PlanService;
import hsu.bee.petra.schedule.service.ScheduleService;
import hsu.bee.petra.user.repository.UserRepository;

@WebMvcTest(controllers = ScheduleController.class)
@AutoConfigureRestDocs
class ScheduleControllerTest {

	@MockBean
	private UserRepository userRepository;

	@MockBean
	private ScheduleService scheduleService;

	@MockBean
	private PlanService planService;

	@Autowired
	private MockMvc mockMvc;


	@Test
	@DisplayName("plan 데이터 받아서 새로운 plan 생성")
	public void createPlan() throws Exception{

		//given
		Long scheduleId = 2L;
		Cookie cookie = new Cookie("userCookie", "park1");
		PlanDto planDto = PlanDto.builder()
				.memo("밥부터 먹자")
				.startDate(LocalDate.parse("2022-04-08", DateTimeFormatter.ISO_LOCAL_DATE))
				.endDate(LocalDate.parse("2022-04-08", DateTimeFormatter.ISO_LOCAL_DATE))
				.scheduleId(scheduleId)
				.attractionName("광화문")
				.build();

		String tempApi = "{\n" +
				"    \"scheduleId\": 2,\n" +
				"    \"attractionName\": \"광화문\",\n" +
				"    \"memo\": \"밥부터 먹자\",\n" +
				"    \"startDate\": \"2022-04-08\",\n" +
				"    \"endDate\": \"2022-04-08\"\n" +
				"}";

		doReturn(scheduleId).when(planService).createPlan(planDto);

		//when
		ResultActions result = mockMvc.perform(
				RestDocumentationRequestBuilders.post("/schedule/plan")
						.contentType(MediaType.APPLICATION_JSON)
						.cookie(cookie)
						.content(tempApi)
//						.content(new Gson().toJson(planDto))
		);

		//then
		result.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("data").value(scheduleId))
				.andDo(document("Create Plan",
						getDocumentRequest(),
						getDocumentResponse(),
						resource(
								ResourceSnippetParameters.builder()
										.description("plan에 들어가는 정보를 받아와서 plan을 생성합니다.")
										.summary("plan 생성")
										// .requestHeaders(
										// 	headerWithName("Cookie").description("로그인 시 발급받은 쿠키")
										// )
//										.requestFields(
//												fieldWithPath("memo").description("plan에 저장할 메모"),
//												fieldWithPath("startDate").description("plan을 시작하는 날짜"),
//												fieldWithPath("endDate").description("plan을 끝내는 날짜"),
//												fieldWithPath("scheduleId").description("저장할 schedule의 id"),
//												fieldWithPath("attractionName").description("plan의 목적지")
//										)
										.responseFields(
												fieldWithPath("code").description("응답코드"),
												fieldWithPath("message").description("응답메시지"),
												fieldWithPath("data").description("Plan을 저장한 scheduleId")
										).build()
						))
				);
	}

	@Test
	@DisplayName("ScheduleId 받아서 단일 스케줄 건 조회")
	public void testGetSchedule() throws Exception {
		// given
		Cookie cookie = new Cookie("userCookie", "heather");
		Long scheduleId = 1L;
		AttractionDto attractionDto = AttractionDto.builder()
				.id(1L)
				.name("강릉초당순두부")
				.address("강릉시 123-1")
				.category("식당")
				.build();

		PlanDto planDto = PlanDto.builder()
				.id(1L)
				.memo("밥부터 먹자")
				.order(1)
				.startDate(LocalDate.parse("2022-04-08", DateTimeFormatter.ISO_LOCAL_DATE))
				.endDate(LocalDate.parse("2022-04-08", DateTimeFormatter.ISO_LOCAL_DATE))
				.attraction(attractionDto)
				.build();

		List<PlanDto> planDtoList = new ArrayList<>();
		planDtoList.add(planDto);

		ScheduleDto scheduleDto = ScheduleDto.builder()
				.id(scheduleId)
				.title("1박 2일 강릉 여행")
				.startDate(LocalDate.now())
				.endDate(LocalDate.now())
				.planList(planDtoList)
				.build();

		doReturn(scheduleDto).when(scheduleService).getSchedule(scheduleId);

		// when
		ResultActions result = mockMvc.perform(
				RestDocumentationRequestBuilders.get("/schedule/{scheduleId}", 1)
						.cookie(cookie)
		);

		// then
		result.andExpect(status().isOk())
				.andExpect(jsonPath("code").value(ResponseCode.SUCCESS))
				.andExpect(jsonPath("message").value(ResponseMessage.SUCCESS))
				.andDo(document("개별 스케줄 조회",
								pathParameters(
										parameterWithName("scheduleId").description("조회를 원하는 스케줄 idx")
								),
								// requestHeaders(
								// 	headerWithName("cookie").description("로그인 시 발급받은 쿠키")
								// ),
								responseFields(
										fieldWithPath("code").description("응답코드"),
										fieldWithPath("message").description("응답메시지"),
										fieldWithPath("data.id").description("스케줄 idx"),
										fieldWithPath("data.title").description("스케줄 제목"),
										fieldWithPath("data.adult").description("어른 인원수"),
										fieldWithPath("data.child").description("아이 인원수"),
										fieldWithPath("data.startDate").description("스케줄 시작일자"),
										fieldWithPath("data.endDate").description("스케줄 종료일자"),
										fieldWithPath("data.planList[0].id").description("계획 idx"),
										fieldWithPath("data.planList[0].memo").description("계획 메모"),
										fieldWithPath("data.planList[0].order").description("계획의 순서"),
										fieldWithPath("data.planList[0].startDate").description("계획 시작일자"),
										fieldWithPath("data.planList[0].endDate").description("계획 종료일자"),
										fieldWithPath("data.planList[0].attraction.id").description("관광지 idx"),
										fieldWithPath("data.planList[0].attraction.name").description("관광지 이름"),
										fieldWithPath("data.planList[0].attraction.address").description("관광지 주소"),
										fieldWithPath("data.planList[0].attraction.category").description("관광지 카테고리")
								)
						)
				)
				.andDo(document("개별 스케줄 조회",
						getDocumentRequest(),
						getDocumentResponse(),
						resource(
								ResourceSnippetParameters.builder()
										.description("스케줄 idx로 스케줄에 대한 상세정보를 조회합니다.")
										.summary("개별 스케줄 조회")
										.pathParameters(
												parameterWithName("scheduleId").description("조회를 원하는 스케줄 idx")
										)
										// .requestHeaders(
										// 	headerWithName("cookie").description("로그인 시 발급받은 쿠키")
										// )
										.responseFields(
												fieldWithPath("code").description("응답코드"),
												fieldWithPath("message").description("응답메시지"),
												fieldWithPath("data.id").description("스케줄 idx"),
												fieldWithPath("data.title").description("스케줄 제목"),
												fieldWithPath("data.adult").description("어른 인원수"),
												fieldWithPath("data.child").description("아이 인원수"),
												fieldWithPath("data.startDate").description("스케줄 시작일자"),
												fieldWithPath("data.endDate").description("스케줄 종료일자"),
												fieldWithPath("data.planList[0].id").description("계획 idx"),
												fieldWithPath("data.planList[0].memo").description("계획 메모"),
												fieldWithPath("data.planList[0].order").description("계획의 순서"),
												fieldWithPath("data.planList[0].startDate").description("계획 시작일자"),
												fieldWithPath("data.planList[0].endDate").description("계획 종료일자"),
												fieldWithPath("data.planList[0].attraction.id").description("관광지 idx"),
												fieldWithPath("data.planList[0].attraction.name").description("관광지 이름"),
												fieldWithPath("data.planList[0].attraction.address").description("관광지 주소"),
												fieldWithPath("data.planList[0].attraction.category").description("관광지 카테고리")
										)
										.build()
						))
				);
	}

	@Test
	@DisplayName("Schedule Id를 받아서 새 스케쥴 생성 후 Plan 복사")
	public void testCopyPlanToNewSchedule() throws Exception {

		//given
		Cookie cookie = new Cookie("userCookie", "park1");
		Long newScheduleId = 0L;
		//        String today = "2022-04-27";
		CopyScheduleDto csd = createTestCSD(newScheduleId);
		BasicScheduleInfoDto scd = createTestSCD(csd.getUserId(), 2L);
		String testCSD = "{\"userId\":\"park1\",\"planIdList\":[13,60,61],\"scheduleId\":1}";

		List<Long> planIdList =
			Stream.of(csd.getPlanIdList()).collect(Collectors.toList());

		given(
			planService.copyAndSavePlan(csd.getUserId(), csd.getScheduleId(), csd.getNewScheduleId(), planIdList
			)).willReturn(scd.getScheduleId());

		//when
		ResultActions result = this.mockMvc.perform(RestDocumentationRequestBuilders.post("/schedule/plans/copy")
			.contentType(MediaType.APPLICATION_JSON)
			.cookie(cookie)
			//.content(new Gson().toJson(csd))
			.content(testCSD)
		);

		//then
		result.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("data.scheduleId").value(scd.getScheduleId()))
			.andDo(document("Copy Plan to New Schedule",
				getDocumentRequest(),
				getDocumentResponse(),
				resource(
					ResourceSnippetParameters.builder()
						.description(
							"기존 스케줄에서 Plan들을 복사하여 다른 스케줄에 복사합니다. json의 newScheduleId키에 특정 schedule Id를 포함하여 전송하면 있으면 기존에 존재하던 스케줄에 plan을 추가해주고"
								+
								" newScheduleId키에 값을 넣지 않으면 새로 스케줄을 생성한 뒤 그 스케줄에 Plan을 추가하여 줍니다")
						.summary("기존 스케줄의 Plan -> 다른 (기존 or 신규생성된 )스케줄에 복사")
						// .requestHeaders(
						// 	headerWithName("Cookie").description("로그인 시 발급받은 쿠키")
						// )
						.requestFields(
							fieldWithPath("userId").description("사용자 ID"),
							fieldWithPath("planIdList").description("복사할 Plan의 Id 리스트"),
							fieldWithPath("scheduleId").description("Plan의 Id가 위치한 Schedule Id")
							//                                                fieldWithPath("newScheduleId").description("plan들을 복사할 기존의 Schedule Id")
						)
						.responseFields(
							fieldWithPath("code").description("응답코드"),
							fieldWithPath("message").description("응답메시지"),
							fieldWithPath("data.scheduleId").description("새로 생성한 Schedule Id")
						).build()
				))
			);
	}

	@Test
	@DisplayName("Schedule Id를 받아서 기존 스케쥴에 Plan 복사")
	public void testCopyPlanToOldSchedule() throws Exception {

		//given
		Cookie cookie = new Cookie("userCookie", "park1");
		Long newScheduleId = 2L;
		String today = "2022-04-27";
		CopyScheduleDto csd = createTestCSD(newScheduleId);
		BasicScheduleInfoDto scd = createTestSCD(csd.getUserId(), 2L);
		String testCSD = "{\"userId\":\"park1\",\"planIdList\":[13,60,61],\"scheduleId\":1}";

		List<Long> planIdList =
			Stream.of(csd.getPlanIdList()).collect(Collectors.toList());

		given(
			planService.copyAndSavePlan(csd.getUserId(), csd.getScheduleId(), csd.getNewScheduleId(), planIdList
			)).willReturn(scd.getScheduleId());

		//when
		ResultActions result = this.mockMvc.perform(RestDocumentationRequestBuilders.post("/schedule/plans/copy")
				.contentType(MediaType.APPLICATION_JSON)
				.cookie(cookie)
				.content(new Gson().toJson(csd))
			//.content(testCSD)
		);

		//then
		result.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("data.scheduleId").value(scd.getScheduleId()))
			.andDo(document("Copy Plan to Old Schedule",
				getDocumentRequest(),
				getDocumentResponse(),
				resource(
					ResourceSnippetParameters.builder()
						.description("기존 스케줄에서 Plan들을 복사하여 기존 스케줄에 삽입합니다.")
						.summary("기존 스케줄의 Plan -> 기존의 다른 스케줄")
						// .requestHeaders(
						// 	headerWithName("Cookie").description("로그인 시 발급받은 쿠키")
						// )
						.requestFields(
							fieldWithPath("userId").description("사용자 ID"),
							fieldWithPath("planIdList").description("복사할 Plan의 Id 리스트"),
							fieldWithPath("scheduleId").description("PlanId가 위치한 Schedule Id"),
							fieldWithPath("newScheduleId").description("plan들을 복사할 기존의 Schedule Id")
						)
						.responseFields(
							fieldWithPath("code").description("응답코드"),
							fieldWithPath("message").description("응답메시지"),
							fieldWithPath("data.scheduleId").description("기존의 Schedule Id")
						).build()
				))
			);
	}

	public CopyScheduleDto createTestCSD(long newScheduleId) {

		return new CopyScheduleDto("park1", new Long[] {new Long(13), new Long(60), new Long(61)}, 1L, newScheduleId);
	}

	private BasicScheduleInfoDto createTestSCD(String userId, Long newScheduleId) {

		List<PlanDto> planList = new ArrayList<>();

		for (int i = 0; i < 3; i++) {
			PlanDto pld = new PlanDto(124L + i, "plan1", i + 1, LocalDate.now(), LocalDate.now(), newScheduleId,
				"국립 청태산자연휴양림");
			planList.add(pld);
		}

		BasicScheduleInfoDto scd = new BasicScheduleInfoDto(userId, newScheduleId, planList);

		return scd;
	}

}