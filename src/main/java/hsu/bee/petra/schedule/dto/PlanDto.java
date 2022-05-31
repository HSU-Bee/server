package hsu.bee.petra.schedule.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import hsu.bee.petra.attraction.dto.AttractionDto;
import hsu.bee.petra.schedule.entity.Plan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@EqualsAndHashCode
public class PlanDto {

	private Long id;
	private String memo;
	private Integer order;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate endDate;
	private Long scheduleId;
	private String attractionName;
	private AttractionDto attraction;

	public PlanDto() { }

	public PlanDto(Plan plan) {
		this.id = plan.getId();
		this.memo = plan.getMemo();
		this.order = plan.getOrder();
		this.startDate = plan.getStartDate();
		this.endDate = plan.getEndDate();
		this.scheduleId = plan.getSchedule().getId();
		this.attractionName = plan.getAttraction().getName();
	}

	public PlanDto(Long id, String memo, int order, LocalDate startDate, LocalDate endDate, Long scheduleId,
		String attractionName) {
		this.id = id;
		this.memo = memo;
		this.order = order;
		this.startDate = startDate;
		this.endDate = endDate;
		this.scheduleId = scheduleId;
		this.attractionName = attractionName;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public static PlanDto convertPlanDto(Plan plan) {
		return PlanDto.builder()
			.id(plan.getId())
			.memo(plan.getMemo())
			.order(plan.getOrder())
			.startDate(plan.getStartDate())
			.endDate(plan.getEndDate())
			.attractionName(null)
			.scheduleId(null)
			.attraction(AttractionDto.convertAttractionDto(plan.getAttraction()))
			.build();
	}

//	@Override
//	public boolean equals(Object obj) {
//
//		if (obj == this) return true;
//		if (!(obj instanceof PlanDto)) return false;
//		PlanDto other = (PlanDto) obj;
//		if (!other.canEqual((Object)this)) return false;
//
//		// id, memo,, order, startDate, endDate, attractionName,scheduleId, attraction
//		if(Double.compare(this.id, other.getId()) != 0) return false;
//		if (this.getMemo() == null ? other.getMemo() != null : !this.getMemo().equals(other.getMemo())) return false;
//		if
//		//if (Double.compare(this.score, other.score) != 0) return false;
//		if (!Arrays.deepEquals(this.tags, other.tags)) return false;
//		return true;
//
//	}
//	protected boolean canEqual(Object other) {
//		return other instanceof EqualsAndHashCodeExample;
//	}
}

