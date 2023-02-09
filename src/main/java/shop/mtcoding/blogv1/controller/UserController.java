package shop.mtcoding.blogv1.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.blogv1.dto.user.UserReq.JoinReqDto;
import shop.mtcoding.blogv1.dto.user.UserReq.LoginReqDto;
import shop.mtcoding.blogv1.handler.ex.CustomException;
import shop.mtcoding.blogv1.model.User;
import shop.mtcoding.blogv1.service.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;

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

    @PostMapping
    public String login(LoginReqDto loginReqDto) {
        if (loginReqDto.getUsername() == null || loginReqDto.getUsername().isEmpty()) {
            throw new CustomException("username을 작성해주세요");
        }
        if (loginReqDto.getPassword() == null || loginReqDto.getPassword().isEmpty()) {
            throw new CustomException("password 작성해주세요");
        }
        User principal = userService.로그인(loginReqDto);
        session.setAttribute("principal", principal);
        return "redirect:/";
    }

    @PostMapping("/loginForm")
    public String loginForm() {
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
