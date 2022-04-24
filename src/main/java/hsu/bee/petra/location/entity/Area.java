package hsu.bee.petra.location.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Area {

	@Id
	private int id;

	private String name;

}
