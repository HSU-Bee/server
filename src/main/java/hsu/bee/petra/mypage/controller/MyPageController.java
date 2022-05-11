package hsu.bee.petra.mypage.controller;

import hsu.bee.petra.common.annotation.AuthenticatedUser;
import hsu.bee.petra.mypage.dto.MyPageDto;
import hsu.bee.petra.response.Response;
import hsu.bee.petra.response.ResponseCode;
import hsu.bee.petra.response.ResponseMessage;
import hsu.bee.petra.user.entity.User;
import hsu.bee.petra.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MyPageController {

    private final UserService userService;

    @GetMapping("/mypage")
    public Response<MyPageDto> getMyPage(@AuthenticatedUser User user) {

        MyPageDto result = userService.getMyPageInfo(user);

        return new Response(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, result);
    }

}
