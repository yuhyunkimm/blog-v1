package shop.mtcoding.blogv1.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import shop.mtcoding.blogv1.handler.ex.CustomException;
import shop.mtcoding.blogv1.util.Script;

@RestController
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public String customException(CustomException e) {
        return Script.back(e.getMessage());
    }
}
