package hsu.bee.petra.schedule.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import hsu.bee.petra.code.entity.Status;
import hsu.bee.petra.time.Timestamp;
import hsu.bee.petra.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Schedule extends Timestamp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private int adult;
	private int child;
	private LocalDate startDate;
	private LocalDate endDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToOne(fetch = FetchType.LAZY)
	private Status status;

	/** 양방향 매핑 */
	@OneToMany(mappedBy = "schedule")
	private List<Plan> planList = new ArrayList<>();

	@OneToMany(mappedBy = "id.scheduleId")
	private List<FoodType> foodTypeList = new ArrayList<>();

	@OneToMany(mappedBy = "id.scheduleId")
	private List<TravelType> travelTypeList = new ArrayList<>();
}
