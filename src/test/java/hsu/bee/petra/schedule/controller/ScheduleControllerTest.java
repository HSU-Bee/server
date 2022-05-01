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
import java.util.stream.Stream;

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
//        String today = "2022-04-27";
        CopyScheduleDto csd = createTestCSD(newScheduleId);
        ScheduleDto scd = createTestSCD(csd.getUserId(), 2L);
        String testCSD = "{\"userId\":\"park1\",\"planIdList\":[13,60,61],\"scheduleId\":1}";

        List<Long> planIdList =
                Stream.of(csd.getPlanIdList()).collect(Collectors.toList());

        given(
                planService.copyAndSavePlan(csd.getUserId(), csd.getScheduleId(), csd.getNewScheduleId(), planIdList
                )).willReturn(scd.getScheduleId());


        //when
        ResultActions result = this.mockMvc.perform(RestDocumentationRequestBuilders.post("/schedules/plans/copy")
                        .contentType(MediaType.APPLICATION_JSON)
//                .content(new Gson().toJson(csd))
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
                                                .description("기존 스케줄에서 Plan들을 복사하여 다른 스케줄에 복사합니다. json의 newScheduleId키에 특정 schedule Id를 포함하여 전송하면 있으면 기존에 존재하던 스케줄에 plan을 추가해주고" +
                                                        " newScheduleId키에 값을 넣지 않으면 새로 스케줄을 생성한 뒤 그 스케줄에 Plan을 추가하여 줍니다")
                                                .summary("기존 스케줄의 Plan -> 다른 (기존 or 신규생성된 )스케줄에 복사")
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
        Long newScheduleId = 2L;
        String today = "2022-04-27";
        CopyScheduleDto csd = createTestCSD(newScheduleId);
        ScheduleDto scd = createTestSCD(csd.getUserId(), 2L);
        String testCSD = "{\"userId\":\"park1\",\"planIdList\":[13,60,61],\"scheduleId\":1}";

        List<Long> planIdList =
                Stream.of(csd.getPlanIdList()).collect(Collectors.toList());

        given(
                planService.copyAndSavePlan(csd.getUserId(), csd.getScheduleId(), csd.getNewScheduleId(), planIdList
                )).willReturn(scd.getScheduleId());


        //when
        ResultActions result = this.mockMvc.perform(RestDocumentationRequestBuilders.post("/schedules/plans/copy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(csd))
//                        .content(testCSD)
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

        return new CopyScheduleDto("park1", new Long[]{new Long(13), new Long(60), new Long(61)}, 1L, newScheduleId);
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