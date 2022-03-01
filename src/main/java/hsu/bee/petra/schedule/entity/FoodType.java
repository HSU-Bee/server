package hsu.bee.petra.schedule.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import hsu.bee.petra.common.entity.Timestamp;

@Entity
@Table(name = "food_type")
public class FoodType extends Timestamp {
	// schedule, food_code 복합키 매핑
}
