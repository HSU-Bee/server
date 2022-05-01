package hsu.bee.petra.attraction.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@EqualsAndHashCode
@Embeddable
@NoArgsConstructor
public class AttractionImageId implements Serializable {

    private static final long serialVersionUID = 830566898929056999L;
    @Column(name="attraction_id")
    private Long attractionId;

    @Column(name="image_id")
    private Long imageId;
}
