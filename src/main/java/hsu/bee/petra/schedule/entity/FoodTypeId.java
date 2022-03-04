package hsu.bee.petra.schedule.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@EqualsAndHashCode
@Embeddable
@NoArgsConstructor
public class FoodTypeId implements Serializable {

    @Column(name="schedule_id")
    private Long scheduleId;

    @Column(name="code_id")
    private Long codeId;
}
