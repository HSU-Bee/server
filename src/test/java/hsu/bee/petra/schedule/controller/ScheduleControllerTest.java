package hsu.bee.petra.schedule.controller;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.google.gson.*;
import hsu.bee.petra.schedule.dto.CopyScheduleDto;
import hsu.bee.petra.schedule.dto.PlanDto;
import hsu.bee.petra.schedule.dto.ScheduleDto;
import hsu.bee.petra.schedule.service.PlanService;
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

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static util.ApiDocumentUtils.getDocumentRequest;
import static util.ApiDocumentUtils.getDocumentResponse;

@WebMvcTest(controllers = ScheduleController.class)
@AutoConfigureRestDocs
class ScheduleControllerTest {

    @MockBean
    private PlanService planService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Schedule Id를 받아서 새 스케쥴 생성 후 Plan 복사")
    public void testCopyPlanToNewSchedule() throws Exception {

        //given
        Long newScheduleId = 0L;
        CopyScheduleDto csd = createTestCSD(newScheduleId);
        ScheduleDto scd = createTestSCD(csd.getUserId(), 2L);
        String today = "2022-04-27";
        String testCSD = "{\"userId\":\"park1\",\"planIdList\":[13,60,61],\"scheduleId\":1}";

        List<Long> planIdList =
                Arrays.stream(csd.getPlanIdList())
                        .boxed()
                        .collect(Collectors.toList());

        System.out.println(new Gson().toJson(csd));
        given(
                planService.copyAndSavePlan(csd.getUserId(), csd.getScheduleId(), csd.getNewScheduleId(), planIdList
                )).willReturn(scd);

        //when
        ResultActions result = this.mockMvc.perform(RestDocumentationRequestBuilders.post("/schedules/copy-create-schedule")
                .contentType(MediaType.APPLICATION_JSON)
                .content(testCSD)
        );

        //then
        result.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("data.userId").value(scd.getUserId()))
                .andExpect(MockMvcResultMatchers.jsonPath("data.scheduleId").value(scd.getScheduleId()))
                .andExpect(MockMvcResultMatchers.jsonPath("data.planList[0].planId").value(124))
                .andExpect(MockMvcResultMatchers.jsonPath("data.planList[0].memo").value("plan1"))
                .andExpect(MockMvcResultMatchers.jsonPath("data.planList[0].order").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("data.planList[0].startDate").value(today))
                .andExpect(MockMvcResultMatchers.jsonPath("data.planList[0].endDate").value(today))
                .andExpect(MockMvcResultMatchers.jsonPath("data.planList[0].attractionName").value("국립 청태산자연휴양림"))
                .andExpect(MockMvcResultMatchers.jsonPath("data.planList[0].scheduleId").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("data.planList[1].planId").value(125))
                .andExpect(MockMvcResultMatchers.jsonPath("data.planList[1].memo").value("plan1"))
                .andExpect(MockMvcResultMatchers.jsonPath("data.planList[1].order").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("data.planList[1].startDate").value(today))
                .andExpect(MockMvcResultMatchers.jsonPath("data.planList[1].endDate").value(today))
                .andExpect(MockMvcResultMatchers.jsonPath("data.planList[1].attractionName").value("국립 청태산자연휴양림"))
                .andExpect(MockMvcResultMatchers.jsonPath("data.planList[1].scheduleId").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("data.planList[2].planId").value(126))
                .andExpect(MockMvcResultMatchers.jsonPath("data.planList[2].memo").value("plan1"))
                .andExpect(MockMvcResultMatchers.jsonPath("data.planList[2].order").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("data.planList[2].startDate").value(today))
                .andExpect(MockMvcResultMatchers.jsonPath("data.planList[2].endDate").value(today))
                .andExpect(MockMvcResultMatchers.jsonPath("data.planList[2].attractionName").value("국립 청태산자연휴양림"))
                .andExpect(MockMvcResultMatchers.jsonPath("data.planList[2].scheduleId").value(2))
                .andDo(document("Copy Plan to New Schedule",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        resource(
                                ResourceSnippetParameters.builder()
                                        .description("기존 스케줄에서 Plan들을 복사하여 새 스케줄을 생성 후 삽입합니다.")
                                        .summary("기존 스케줄의 Plan -> 새 스케줄 생성 후 삽입")
                                        .requestFields(
                                                fieldWithPath("userId").description("사용자 ID"),
                                                fieldWithPath("planIdList").description("복사할 PlanId 리스트"),
                                                fieldWithPath("scheduleId").description("PlanId가 위치한 Schedule Id")
                                        )
                                        .responseFields(
                                                fieldWithPath("code").description("응답코드"),
                                                fieldWithPath("message").description("응답메시지"),
                                                fieldWithPath("data.userId").description("사용자 ID"),
                                                fieldWithPath("data.scheduleId").description("새로 생성한 Schedule Id"),
                                                fieldWithPath("data.planList[*].planId").description("복사 생성된 plan id"),
                                                fieldWithPath("data.planList[*].memo").description("plan의 메모"),
                                                fieldWithPath("data.planList[*].order").description("schedule 내부에서 plan의 순서"),
                                                fieldWithPath("data.planList[*].startDate").description("plan의 시작일"),
                                                fieldWithPath("data.planList[*].endDate").description("plan의 종료일"),
                                                fieldWithPath("data.planList[*].attractionName").description("plan 속 관광지 이름"),
                                                fieldWithPath("data.planList[*].scheduleId").description("plan이 저장된 schedule Id")
                                        ).build()
                        ))
                );
    }

    @Test
    @DisplayName("Schedule Id를 받아서 기존 스케쥴에 Plan 복사")
    public void testCopyPlanToOldSchedule() throws Exception {

        //given
        Long newScheduleId = 2L;
        String today = "2022-04-27";
        CopyScheduleDto csd = createTestCSD(newScheduleId);
        ScheduleDto scd = createTestSCD(csd.getUserId(), newScheduleId);

        List<Long> planIdList =
                Arrays.stream(csd.getPlanIdList())
                        .boxed()
                        .collect(Collectors.toList());
        given(
                planService.copyAndSavePlan(csd.getUserId(), csd.getScheduleId(), csd.getNewScheduleId(), planIdList
                )).willReturn(scd);

        //when
        ResultActions result = this.mockMvc.perform(RestDocumentationRequestBuilders.post("/schedules/copy-paste-schedule")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(csd))
        );

        //then
        result.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("data.userId").value(scd.getUserId()))
                .andExpect(MockMvcResultMatchers.jsonPath("data.scheduleId").value(scd.getScheduleId()))
                .andExpect(MockMvcResultMatchers.jsonPath("data.planList[0].planId").value(124))
                .andExpect(MockMvcResultMatchers.jsonPath("data.planList[0].memo").value("plan1"))
                .andExpect(MockMvcResultMatchers.jsonPath("data.planList[0].order").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("data.planList[0].startDate").value(today))
                .andExpect(MockMvcResultMatchers.jsonPath("data.planList[0].endDate").value(today))
                .andExpect(MockMvcResultMatchers.jsonPath("data.planList[0].attractionName").value("국립 청태산자연휴양림"))
                .andExpect(MockMvcResultMatchers.jsonPath("data.planList[0].scheduleId").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("data.planList[1].planId").value(125))
                .andExpect(MockMvcResultMatchers.jsonPath("data.planList[1].memo").value("plan1"))
                .andExpect(MockMvcResultMatchers.jsonPath("data.planList[1].order").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("data.planList[1].startDate").value(today))
                .andExpect(MockMvcResultMatchers.jsonPath("data.planList[1].endDate").value(today))
                .andExpect(MockMvcResultMatchers.jsonPath("data.planList[1].attractionName").value("국립 청태산자연휴양림"))
                .andExpect(MockMvcResultMatchers.jsonPath("data.planList[1].scheduleId").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("data.planList[2].planId").value(126))
                .andExpect(MockMvcResultMatchers.jsonPath("data.planList[2].memo").value("plan1"))
                .andExpect(MockMvcResultMatchers.jsonPath("data.planList[2].order").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("data.planList[2].startDate").value(today))
                .andExpect(MockMvcResultMatchers.jsonPath("data.planList[2].endDate").value(today))
                .andExpect(MockMvcResultMatchers.jsonPath("data.planList[2].attractionName").value("국립 청태산자연휴양림"))
                .andExpect(MockMvcResultMatchers.jsonPath("data.planList[2].scheduleId").value(2))
                .andDo(document("Copy Plan to existing Schedule",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        resource(
                                ResourceSnippetParameters.builder()
                                        .description("기존 스케줄에서 Plan들을 복사하여 기존의 다른 스케줄에 삽입합니다.")
                                        .summary("기존 스케줄의 Plan -> 기존의 다른 스케줄에 삽입")
                                        .requestFields(
                                                fieldWithPath("userId").description("사용자 ID"),
                                                fieldWithPath("planIdList").description("복사할 PlanId 리스트"),
                                                fieldWithPath("scheduleId").description("PlanId가 위치한 Schedule Id"),
                                                fieldWithPath("newScheduleId").description("plan들을 복사할 기존의 Schedule Id")
                                        )
                                        .responseFields(
                                                fieldWithPath("code").description("응답코드"),
                                                fieldWithPath("message").description("응답메시지"),
                                                fieldWithPath("data.userId").description("사용자 ID"),
                                                fieldWithPath("data.scheduleId").description("새로 생성한 Schedule Id"),
                                                fieldWithPath("data.planList[*].planId").description("복사 생성된 plan id"),
                                                fieldWithPath("data.planList[*].memo").description("plan의 메모"),
                                                fieldWithPath("data.planList[*].order").description("schedule 내부에서 plan의 순서"),
                                                fieldWithPath("data.planList[*].startDate").description("plan의 시작일"),
                                                fieldWithPath("data.planList[*].endDate").description("plan의 종료일"),
                                                fieldWithPath("data.planList[*].attractionName").description("plan 속 관광지 이름"),
                                                fieldWithPath("data.planList[*].scheduleId").description("plan이 저장된 schedule Id")
                                        ).build()
                        ))
                );
    }

    public CopyScheduleDto createTestCSD(long newScheduleId) {

        return new CopyScheduleDto("park1", new long[]{13,60,61}, 1L, newScheduleId);
    }

    private ScheduleDto createTestSCD(String userId, Long newScheduleId) {

        List<PlanDto> planList = new ArrayList<>();

        for(int i=0; i<3; i++) {
            PlanDto pld = new PlanDto(124L+i, "plan1", i+1, LocalDate.now(),LocalDate.now(), newScheduleId, "국립 청태산자연휴양림");
            planList.add(pld);
        }

        ScheduleDto scd = new ScheduleDto(userId, newScheduleId, planList);

        return scd;
    }

}