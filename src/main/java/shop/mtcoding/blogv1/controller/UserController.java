package shop.mtcoding.blogv1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.blogv1.dto.user.userReq.JoinReqDto;

@Controller
public class UserController {

    @PostMapping("/join")
    public String join(JoinReqDto joinReqDto) {
        if (joinReqDto.getUsername() == null || joinReqDto.getUsername().isEmpty()) {
            System.out.println(joinReqDto.getUsername());

        }
        if (joinReqDto.getPassword() == null || joinReqDto.getPassword().isEmpty()) {
            System.out.println(joinReqDto.getPassword());

        }
        if (joinReqDto.getEmail() == null || joinReqDto.getEmail().isEmpty()) {
            System.out.println(joinReqDto.getEmail());

        }
        userService.회원가입(joinReqDto);

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
