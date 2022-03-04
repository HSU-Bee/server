package hsu.bee.petra.attraction.entity;

import javax.persistence.*;

import hsu.bee.petra.common.entity.Timestamp;
import hsu.bee.petra.image.entity.Image;

@Entity
@Table(name = "attraction_image")
public class AttractionImage {
	// attraction image 복합키 매핑

    @MapsId("attractionId")
    @ManyToOne
    @JoinColumn(name="attraction_id")
    private Attraction attraction;

    @MapsId("imageId")
    @ManyToOne
    @JoinColumn(name="image_id")
    private Image image;

    @EmbeddedId
    private AttractionImageId attractionImageId;
}
