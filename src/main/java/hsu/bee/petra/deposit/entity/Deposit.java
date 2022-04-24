package hsu.bee.petra.deposit.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import hsu.bee.petra.time.Timestamp;
import hsu.bee.petra.schedule.entity.Schedule;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Deposit extends Timestamp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition = "DATE")
	private LocalDate date;
	private String city;

	@Column(name = "\"usage\"")
	private String usage;

	@Column(columnDefinition = "int")
	private Integer price;

	@Column(columnDefinition = "text")
	private String detail;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "schedule_id")
	private Schedule schedule;

	@OneToMany(mappedBy = "id.depositId")
	private List<Receipt> receiptList = new ArrayList<>();
}