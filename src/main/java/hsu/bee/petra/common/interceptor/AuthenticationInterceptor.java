package hsu.bee.petra.common.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.WebUtils;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@NoArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {

	private final String AUTH_COOKIE = "userCookie";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		Cookie cookie = WebUtils.getCookie(request, AUTH_COOKIE);
		if(cookie == null) {
			IllegalStateException illegalStateException = new IllegalStateException("NOT AUTHENTICATED");
			log.error("Cookie Doesn't EXIST!", illegalStateException);
			throw illegalStateException;
		}
		return true;
	}

}
