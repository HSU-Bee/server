package hsu.bee.petra.common.entity;

import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Timestamp {

	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;
}
