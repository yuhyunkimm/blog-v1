package shop.mtcoding.blogv1.dto.user;

import lombok.Getter;
import lombok.Setter;

public class userReq {

    @Getter
    @Setter
    public static class JoinReqDto {
        private String username;
        private String password;
        private String email;
    }

}
