package shop.mtcoding.blogv1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ResponsDto<T> {
    private int code;
    private String msg;
    private T data;
}
