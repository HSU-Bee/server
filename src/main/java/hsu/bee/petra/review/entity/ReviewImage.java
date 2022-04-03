package hsu.bee.petra.review.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import hsu.bee.petra.image.entity.Image;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "review_image")
public class ReviewImage {

	@EmbeddedId
	private ReviewImageId id;

	@MapsId("reviewId")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "review_id")
	private Review review;

	@MapsId("imageId")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "image_id")
	private Image image;

	public void changeReview(Review review) {
		if(this.review != null) {
			this.review.getReviewImageList().remove(this);
		}
		this.review = review;
		if(!review.getReviewImageList().contains(this)) {
			review.getReviewImageList().add(this);
		}
	}
}
