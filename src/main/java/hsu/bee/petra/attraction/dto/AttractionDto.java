package hsu.bee.petra.attraction.dto;

import hsu.bee.petra.attraction.entity.Address;
import hsu.bee.petra.attraction.entity.Theme;
import hsu.bee.petra.locatiion.entity.Sigungu;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttractionDto {
	private long id;
	private String name;
	private Address address;
	private Sigungu sigungu;
	private Theme theme;
}
