package hsu.bee.petra.schedule.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerDto {
	private int when;
	private String period;

	private int adult;
	private int child;
	private int status;

	private String[] travelStyle;

	private String[] foodStyle;
}
