<<<<<<< HEAD:src/main/java/hsu/bee/petra/common/review/entity/ReviewImageId.java
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
=======
package hsu.bee.petra.review.entity;

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
public class ReviewImageId implements Serializable {

	@Column(name = "review_id")
	private Long reviewId;

	@Column(name = "image_id")
	private Long imageId;
>>>>>>> 018761abe327c441ffed52f498c4e232323fb6f5:src/main/java/hsu/bee/petra/review/entity/ReviewImageId.java
}
