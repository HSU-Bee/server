package hsu.bee.petra.attraction.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import hsu.bee.petra.common.entity.Timestamp;

@Entity
@Table(name = "attraction_image")
public class AttractionImage extends Timestamp {
	// attraction image 복합키 매핑
}
