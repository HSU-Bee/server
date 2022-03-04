package hsu.bee.petra.review.entity;

import hsu.bee.petra.image.entity.Image;

import javax.persistence.*;

@Entity
@Table(name = "review_image")
public class ReviewImage {
	// rivew, image 복합키 매핑

    @MapsId("reviewId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="review_id")
    private Review review;

    @MapsId("imageId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="image_id")
    private Image image;

    @EmbeddedId
    private ReviewImageId reviewImageId;
}
