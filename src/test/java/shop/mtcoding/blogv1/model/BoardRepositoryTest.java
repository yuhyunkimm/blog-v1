package shop.mtcoding.blogv1.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.blogv1.dto.board.BoardReq.BoardMainRespDto;

@MybatisTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void findAllWithUser_test() throws Exception {

        ObjectMapper om = new ObjectMapper();

        List<BoardMainRespDto> BoardMainRespDto = boardRepository.findAllWithUser();
        String responseBody = om.writeValueAsString(BoardMainRespDto);
        System.out.println("테스트 : " + responseBody);

        assertThat(BoardMainRespDto.get(5).getUsername()).isEqualTo("love");
    }
}
