package hsu.bee.petra.deposit.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import hsu.bee.petra.common.entity.Timestamp;
import hsu.bee.petra.schedule.entity.Schedule;

@Entity
public class Deposit extends Timestamp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDate date;
	private String city;
	private String usage;
	private Integer price;

	@Column(columnDefinition = "text")
	private String detail;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "schedule_id")
	private Schedule schedule;
}
