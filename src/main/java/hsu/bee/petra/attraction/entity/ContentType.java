package hsu.bee.petra.attraction.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ContentType {

	@Id
	private Long id;

	private String name;
}
