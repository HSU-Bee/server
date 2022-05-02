package hsu.bee.petra.user.dto;

import hsu.bee.petra.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserDto {
	private String id;
	private String name;
	private String nickname;
	private String introduce;

	public static UserDto convertToUserDto(User user) {
		return UserDto.builder()
			.id(user.getId())
			.name(user.getName())
			.nickname(user.getNickname())
			.introduce(user.getIntroduce())
			.build();
	}
}
