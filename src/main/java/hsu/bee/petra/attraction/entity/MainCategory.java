package hsu.bee.petra.attraction.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class MainCategory {

	@Id
	private String id;

	private String name;
}
