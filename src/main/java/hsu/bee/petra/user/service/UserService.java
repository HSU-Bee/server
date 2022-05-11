package hsu.bee.petra.user.service;

import hsu.bee.petra.mypage.dto.MyPageDto;
import hsu.bee.petra.review.repository.ReviewRepository;
import hsu.bee.petra.schedule.repository.ScheduleRepository;
import hsu.bee.petra.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import hsu.bee.petra.user.dto.LogInDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

	private final ReviewRepository reviewRepository;
	private final ScheduleRepository scheduleRepository;

	public void checkUserId(LogInDto user) {
		if (user.getId() == null) {
			log.error("FAIL to checkUserId(LogInDto user)! \n "
					+ "Because user.id is NULL", new IllegalStateException());
			throw new IllegalStateException("FAIL to checkUserId(LogInDto user)! \n Because user.id is NULL");
		}
	}

	public MyPageDto getMyPageInfo(User user) {

		MyPageDto result = new MyPageDto();

		result.setUserId(user.getId());
		result.setMyScheduleCount(scheduleRepository.countByUser(user));
		result.setMyReviewCount(reviewRepository.countByUser(user));

		return result;
	}
}
