package hsu.bee.petra.user.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hsu.bee.petra.response.Response;
import hsu.bee.petra.user.dto.LogInDto;
import hsu.bee.petra.user.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

	private static final String AUTH_COOKIE = "userCookie";
	private static final Response successResponse = new Response();
	private final UserService userService;

	@PostMapping("/login")
	public Response signIn(HttpServletResponse response, @RequestBody LogInDto logInDto) {
		userService.checkUserId(logInDto);
		Cookie cookie = makeCookie(AUTH_COOKIE, logInDto.getId());
		response.addCookie(cookie);

		return successResponse;
	}

	public Cookie makeCookie(String cookieName, String userId) {
		Cookie cookie = new Cookie(cookieName, userId);
		cookie.setMaxAge(18000);
		return cookie;
	}

}
