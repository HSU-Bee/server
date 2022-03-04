package hsu.bee.petra.schedule.entity;

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

}
