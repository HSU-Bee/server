package hsu.bee.petra.user.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hsu.bee.petra.response.Response;
import hsu.bee.petra.user.dto.UserLogInDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

	private static final String AUTH_COOKIE = "userCookie";
	private static final Response successResponse = new Response();

	@PostMapping("/login")
	public Response signIn(HttpServletResponse response, @RequestBody UserLogInDto user) {
		Cookie cookie = new Cookie(AUTH_COOKIE, user.getId());
		cookie.setMaxAge(18000);
		response.addCookie(cookie);
		return successResponse;
	}
}
