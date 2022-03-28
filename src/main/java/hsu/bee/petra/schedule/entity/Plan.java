package hsu.bee.petra.schedule.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import hsu.bee.petra.attraction.entity.Attraction;
import hsu.bee.petra.time.Timestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Plan extends Timestamp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition = "text")
	private String memo;

	@Column(columnDefinition = "int", name = "\"order\"")
	private Integer order;

	@Column(columnDefinition = "DATE")
	private LocalDate startDate;

	@Column(columnDefinition = "DATE")
	private LocalDate endDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "schedule_id")
	private Schedule schedule;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "attraction_id")
	private Attraction attraction;

	public void changeSchedule(Schedule schedule) {
		if(this.schedule != null) {
			this.schedule.getPlanList().remove(this);
		}
		this.schedule = schedule;
		if (!schedule.getPlanList().contains(this)) {
			schedule.getPlanList().add(this);
		}
	}
}
