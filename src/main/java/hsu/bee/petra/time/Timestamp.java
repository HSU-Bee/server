package hsu.bee.petra.time;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Getter;

@Getter
@MappedSuperclass
public abstract class Timestamp {

	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime createdAt;

	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime modifiedAt;
}
