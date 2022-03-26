package hsu.bee.petra.user.service;

import org.springframework.stereotype.Service;

import hsu.bee.petra.user.dto.LogInDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

	public void checkUserId(LogInDto user) {
		if (user.getId() == null) {
			log.error("FAIL to checkUserId(LogInDto user)! \n "
					+ "Because user.id is NULL", new IllegalStateException());
			throw new IllegalStateException("FAIL to checkUserId(LogInDto user)! \n Because user.id is NULL");
		}
	}
}
