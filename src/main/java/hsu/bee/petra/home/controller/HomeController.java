package hsu.bee.petra.home.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hsu.bee.petra.response.Response;
import hsu.bee.petra.user.dto.UserLogInDto;

@RestController
public class HomeController {

    private static final String AUTH_COOKIE = "userCookie";
    private static final Response successResponse = new Response();

    @GetMapping("/")
    public ResponseEntity index() {
        return new ResponseEntity("Welcome to PETRA", HttpStatus.OK);
    }

    @PostMapping("/index/login")
    public Response signIn(HttpServletResponse response, @RequestBody UserLogInDto user) {
        Cookie cookie = new Cookie(AUTH_COOKIE, user.getId());
        cookie.setMaxAge(18000);
        response.addCookie(cookie);
        return successResponse;
    }
}
