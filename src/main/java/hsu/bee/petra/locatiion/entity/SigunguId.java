package hsu.bee.petra.locatiion.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class SigunguId implements Serializable {

	@Column(name = "area_id")
	private int areaId;

	@Column(name = "sigungu_id")
	private int sigunguId;
}
