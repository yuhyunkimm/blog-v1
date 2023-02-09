package shop.mtcoding.blogv1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

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
