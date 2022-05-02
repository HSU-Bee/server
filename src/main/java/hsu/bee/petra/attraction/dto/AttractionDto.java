package hsu.bee.petra.attraction.dto;

import hsu.bee.petra.attraction.entity.Attraction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
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
			.detail(attraction.getAddress().getDetail())
			.area(attraction.getSigungu().getArea().getName())
			.sigungu(attraction.getSigungu().getName())
			.category(attraction.getTheme().getMainCategory().getName())
			.build();
	}
}
