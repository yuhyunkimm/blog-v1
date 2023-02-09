package shop.mtcoding.blogv1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.blogv1.dto.user.userReq.JoinReqDto;
import shop.mtcoding.blogv1.handler.ex.CustomException;
import shop.mtcoding.blogv1.service.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/join")
    public String join(JoinReqDto joinReqDto) {
        if (joinReqDto.getUsername() == null || joinReqDto.getUsername().isEmpty()) {
            throw new CustomException("username을 작성해주세요");
        }
        if (joinReqDto.getPassword() == null || joinReqDto.getPassword().isEmpty()) {
            throw new CustomException("password을 작성해주세요");
        }
        if (joinReqDto.getEmail() == null || joinReqDto.getEmail().isEmpty()) {
            throw new CustomException("email을 작성해주세요");
        }
        int result = userService.회원가입(joinReqDto);
        if (result != 1) {
            throw new CustomException("회원가입실패");
        }
        return "redirect:/loginForm";
    }

    @PostMapping("/joinForm")
    public String join() {
        return "user/joinForm";
    }

    @PostMapping("/loginForm")
    public String login() {
        return "user/loginForm";
    }

    @PostMapping("/user/updateForm")
    public String updateForm() {
        return "user/joinForm";
    }

    @PostMapping("/logout")
    public String logout() {
        return "redired:/";
    }
}
