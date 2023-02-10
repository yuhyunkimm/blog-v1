package shop.mtcoding.blogv1.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.blogv1.dto.ResponsDto;
import shop.mtcoding.blogv1.dto.board.BoardReq.BoardSaveReqDto;
import shop.mtcoding.blogv1.dto.board.BoardReq.BoardUpdateReqDto;
import shop.mtcoding.blogv1.handler.ex.CustomApiException;
import shop.mtcoding.blogv1.handler.ex.CustomException;
import shop.mtcoding.blogv1.model.Board;
import shop.mtcoding.blogv1.model.BoardRepository;
import shop.mtcoding.blogv1.model.User;
import shop.mtcoding.blogv1.service.BoardService;

@Controller
public class BoardController {

    @Autowired
    private HttpSession session;

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepository boardRepository;

    @PutMapping("/board/{id}")
    public @ResponseBody ResponseEntity<?> update(@PathVariable int id,
            @RequestBody BoardUpdateReqDto boardUpdateReqDto, HttpServletResponse response) {
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomApiException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }
        if (boardUpdateReqDto.getTitle() == null || boardUpdateReqDto.getTitle().isEmpty()) {
            throw new CustomApiException("title을 작성해주세요");
        }
        if (boardUpdateReqDto.getContent() == null || boardUpdateReqDto.getContent().isEmpty()) {
            throw new CustomApiException("content를 작성해주세요");
        }
        boardService.게시글수정(id, boardUpdateReqDto, principal.getId());

        return new ResponseEntity<>(new ResponsDto<>(1, "게시글수정성공", null), HttpStatus.OK);
    }

    @DeleteMapping("/board/{id}")
    public @ResponseBody ResponseEntity<?> delete(@PathVariable int id) {

        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomApiException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }

        // boardService.게시글삭제(id, principal.getId());

        return new ResponseEntity<>(new ResponsDto<>(1, "삭제성공", null), HttpStatus.OK);

    }

    @PostMapping("/board")
    public String save(BoardSaveReqDto boardSaveReqDto) {
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }
        if (boardSaveReqDto.getTitle() == null || boardSaveReqDto.getTitle().isEmpty()) {
            throw new CustomException("title을 작성해주세요");
        }
        if (boardSaveReqDto.getContent() == null || boardSaveReqDto.getContent().isEmpty()) {
            throw new CustomException("content를 작성해주세요");
        }
        if (boardSaveReqDto.getTitle().length() > 100) {
            throw new CustomException("title의 길이가 100자 이하여야 합니다");
        }

        boardService.글쓰기(boardSaveReqDto, principal.getId());

        return "redirect:/";
    }

    @GetMapping({ "/", "/board" })
    public String main(Model model) {
        model.addAttribute("dtos", boardRepository.findAllWithUser());
        return "/board/main";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable int id, Model model) {
        model.addAttribute("dto", boardRepository.findAllWithUser());
        return "board/detail";
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }

    @GetMapping("/board/{id}/UpdateForm")
    public String boardUpdateForm(@PathVariable int id, Model model) {
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomApiException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }
        Board boardPS = boardRepository.findById(id);
        if (boardPS == null) {
            throw new CustomException("없는 게시글을 수정할 수 없습니다.");
        }
        if (boardPS.getUserId() != principal.getId()) {
            throw new CustomException("게시글을 수정할 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }
        model.addAttribute("board", boardPS);
        return "board/boardUpdateForm";

    }
}
