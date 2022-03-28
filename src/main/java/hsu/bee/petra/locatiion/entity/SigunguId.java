package hsu.bee.petra.locatiion.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class SigunguId implements Serializable {

	private int id;

	@Column(name = "area_id")
	private int areaId;
}
