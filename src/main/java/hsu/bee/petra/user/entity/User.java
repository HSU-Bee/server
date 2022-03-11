package hsu.bee.petra.user.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import hsu.bee.petra.code.entity.TravelCode;
import hsu.bee.petra.common.entity.Timestamp;

@Entity
public class User extends Timestamp {

	@Id
	private String id;

	private String name;
	private String nickname;
	private String introduce;

	@OneToOne(fetch = FetchType.LAZY)
	private TravelCode travelCode;
}
