package hsu.bee.petra.locatiion.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Area {

	@Id
	private Integer id;

	private String name;

}
