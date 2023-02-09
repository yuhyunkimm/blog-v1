package shop.mtcoding.blogv1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BoardController {

    @GetMapping({ "/", "/board" })
    public String main() {
        return "/board/main";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable int id) {
        return "board/detail";
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }

    @GetMapping("/board/{id}/UpdateForm")
    public String boardUpdateForm(@PathVariable int id) {
        return "board/boardUpdateForm";
    }
}
