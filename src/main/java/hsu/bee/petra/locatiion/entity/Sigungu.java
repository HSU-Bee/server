package hsu.bee.petra.locatiion.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Sigungu {

	@EmbeddedId
	private SigunguId id;

	@MapsId("areaId")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "area_id")
	private Area area;

	private String name;
}
