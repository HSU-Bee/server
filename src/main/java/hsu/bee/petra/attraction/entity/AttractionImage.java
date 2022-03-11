package hsu.bee.petra.attraction.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import hsu.bee.petra.image.entity.Image;

@Entity
@Table(name = "attraction_image")
public class AttractionImage {

	@EmbeddedId
	private AttractionImageId id;

	@MapsId("attractionId")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "attraction_id")
	private Attraction attraction;

	@MapsId("imageId")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "image_id")
	private Image image;
}
