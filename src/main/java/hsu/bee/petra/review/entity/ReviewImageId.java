package hsu.bee.petra.review.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
public class ReviewImageId implements Serializable {

	@Column(name = "review_id")
	private Long reviewId;

	@Column(name = "image_id")
	private Long imageId;
}
