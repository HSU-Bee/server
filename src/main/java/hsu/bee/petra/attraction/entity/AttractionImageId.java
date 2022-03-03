package hsu.bee.petra.attraction.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
public class AttractionImageId implements Serializable {

	@Column(name = "attraction_id")
	private Long attractionId;

	@Column(name = "image_id")
	private Long imageId;
}
