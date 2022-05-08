package hsu.bee.petra.common.argumentresolver;

import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.util.WebUtils;

import hsu.bee.petra.common.annotation.AuthenticatedUser;
import hsu.bee.petra.user.entity.User;
import hsu.bee.petra.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

	private final UserRepository userRepository;
	private final String AUTH_COOKIE = "userCookie";

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(AuthenticatedUser.class);
	}

	@Override
	public User resolveArgument(MethodParameter parameter,
		                           ModelAndViewContainer mavContainer,
								   NativeWebRequest webRequest,
								   WebDataBinderFactory binderFactory) {

		HttpServletRequest request = (HttpServletRequest)webRequest.getNativeRequest();
		Cookie cookie = WebUtils.getCookie(request, AUTH_COOKIE);
		String userId = cookie.getValue();

		return getValidUser(userId);
	}

	private User getValidUser(String userId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		if(optionalUser.isEmpty()) {
			IllegalArgumentException illegalArgumentException = new IllegalArgumentException(
				"유효하지 않은 userId 입니다. (입력된 userId : " + userId + ")");
			log.error(
				"UserArgumentResolver :: getValidUser(String userId) "
					+ "- 유효하지 않은 userId 입니다. (입력된 userId : " + userId + ")",
				illegalArgumentException);
		}
		return optionalUser.get();
	}

}
