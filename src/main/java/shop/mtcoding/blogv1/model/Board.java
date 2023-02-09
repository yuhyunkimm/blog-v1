package shop.mtcoding.blogv1.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board {
    private int id;
    private String title;
    private String content;
    private String thumbnail;
    private int userId;
    private Timestamp createdAt;
}
