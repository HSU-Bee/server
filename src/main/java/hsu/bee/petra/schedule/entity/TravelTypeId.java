package hsu.bee.petra.schedule.entity;

<<<<<<< HEAD
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@EqualsAndHashCode
@Embeddable
@NoArgsConstructor
public class TravelTypeId implements Serializable {

    @Column(name="schedule_id")
    private Long scheduleId;

    @Column(name="code_id")
    private Long codeId;

=======
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class TravelTypeId implements Serializable {

	@Column(name = "schedule_id")
	private Long scheduleId;

	@Column(name = "code_id")
	private Long TravelCodeId;
>>>>>>> 018761abe327c441ffed52f498c4e232323fb6f5
}
