package hsu.bee.petra.schedule.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import hsu.bee.petra.attraction.entity.Attraction;
import hsu.bee.petra.common.entity.Timestamp;

@Entity
public class Plan extends Timestamp {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition = "text")
	private String memo;

	private Integer order;

	private LocalDate startDate;
	private LocalDate endDate;

	@ManyToOne
	@JoinColumn(name = "schedule_id")
	private Schedule schedule;

	@ManyToOne
	@JoinColumn(name = "attraction_id")
	private Attraction attraction;
}
