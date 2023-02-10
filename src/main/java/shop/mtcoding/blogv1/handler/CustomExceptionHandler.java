package shop.mtcoding.blogv1.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import shop.mtcoding.blogv1.dto.ResponsDto;
import shop.mtcoding.blogv1.handler.ex.CustomApiException;
import shop.mtcoding.blogv1.handler.ex.CustomException;
import shop.mtcoding.blogv1.util.Script;

@RestController
public class CustomExceptionHandler {

    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<?> CustomApiException(CustomException e) {
        return new ResponseEntity<>(new ResponsDto<>(-1, e.getMessage(), null), e.getStatus());

    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> customException(CustomException e) {
        String responseBody = Script.back(e.getMessage());
        return new ResponseEntity<>(responseBody, e.getStatus());
    }
}
