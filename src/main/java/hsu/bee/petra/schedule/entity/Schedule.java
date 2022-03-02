package hsu.bee.petra.schedule.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import hsu.bee.petra.code.entity.Status;
import hsu.bee.petra.common.entity.Timestamp;
import hsu.bee.petra.user.entity.User;

@Entity
public class Schedule extends Timestamp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private Integer adult;
	private Integer child;
	private LocalDate startDate;
	private LocalDate endDate;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToOne
	private Status status;
}
