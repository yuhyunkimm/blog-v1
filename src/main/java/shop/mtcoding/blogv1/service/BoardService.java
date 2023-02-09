package shop.mtcoding.blogv1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.blogv1.dto.board.BoardReq.BoardSaveReqDto;
import shop.mtcoding.blogv1.handler.ex.CustomException;
import shop.mtcoding.blogv1.model.BoardRepository;

@Transactional(readOnly = true)
@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public void 글쓰기(BoardSaveReqDto boadSaveReqDto, int useId) {
        String thumbnail = "";

        int result = boardRepository.insert(boadSaveReqDto.getTitle(), boadSaveReqDto.getContent(), thumbnail, useId);

        result = boardRepository.insert(boadSaveReqDto.getTitle(), boadSaveReqDto.getContent(), thumbnail, useId);
        if (result != 1) {
            throw new CustomException("글쓰기 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
