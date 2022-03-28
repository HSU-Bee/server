package hsu.bee.petra.common.review.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@EqualsAndHashCode
@NoArgsConstructor
@Embeddable
public class ReviewImageId implements Serializable {

    @Column(name="review_id")
    private Long reviewId;

    @Column(name="image_id")
    private Long imageId;
}
