package hsu.bee.petra.time;

import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;

import lombok.Getter;

@Getter
@MappedSuperclass
public abstract class Timestamp {

	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;
}