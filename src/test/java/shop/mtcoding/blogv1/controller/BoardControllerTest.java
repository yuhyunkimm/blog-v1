package shop.mtcoding.blogv1.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.blogv1.dto.board.BoardReq.BoardUpdateReqDto;
import shop.mtcoding.blogv1.model.User;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class BoardControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    private MockHttpSession mockSession;

    @BeforeEach
    public void setUp() {
        // 데이터 인서트

        // 세션 주입
        User user = new User();
        user.setId(1);
        user.setUsername("ssar");
        user.setPassword("1234");
        user.setEmail("ssar@nate.com");
        user.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));

        mockSession = new MockHttpSession();
        mockSession.setAttribute("principal", user);
    }

    @Test
    public void update_test() throws Exception {
        // given
        int id = 1;
        BoardUpdateReqDto boardUpdateReqDto = new BoardUpdateReqDto();
        boardUpdateReqDto.setTitle("제목1-수정");
        boardUpdateReqDto.setContent("내용1-수정");

        String requestBody = om.writeValueAsString(boardUpdateReqDto);
        System.out.println("테스트 : " + requestBody);

        // when
        ResultActions resultActions = mvc.perform(
                post("/board/" + id)
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .session(mockSession));

        // then
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.code").value(1));
    }

    @Test
    public void save_test() throws Exception {
        String title = "";
        for (int i = 0; i < 99; i++) {
            title += "가";
        }

        String requestBody = "title=" + title + "&content=내용1";
        // when
        ResultActions resultActions = mvc.perform(
                post("/board")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .session(mockSession));

        resultActions.andExpect(status().is3xxRedirection());
    }

    @Test
    public void delete_test() throws Exception {
        int id = 1;

        ResultActions resultActions = mvc.perform(delete("/board/" + id).session(mockSession));
        String reponseBody = resultActions.andReturn().getResponse().getContentAsString(null);
        System.out.println("테스트 : " + reponseBody);

    }
}
