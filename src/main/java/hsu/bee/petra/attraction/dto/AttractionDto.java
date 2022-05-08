package hsu.bee.petra.attraction.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import hsu.bee.petra.attraction.entity.Attraction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AttractionDto {
	private long id;
	private String name;
	private String address;
	private String detail;
	private String area;
	private String sigungu;
	private String category;

	public static AttractionDto convertAttractionDto(Attraction attraction) {
		return AttractionDto
			.builder()
			.id(attraction.getId())
			.name(attraction.getName())
			.address(attraction.getAddress().getAddress())
			.category(attraction.getTheme().getMainCategory().getName())
			.build();
	}
}
