package shop.mtcoding.blogv1.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.blogv1.dto.board.BoardResp.BoardDetailRespDto;
import shop.mtcoding.blogv1.dto.board.BoardResp.BoardMainRespDto;

@Mapper
public interface BoardRepository {

        public List<BoardMainRespDto> findAllWithUser();

        public BoardDetailRespDto findByidWithUser(int id);

        public List<Board> findAll();

        public Board findById(int id);

        public List<Board> findByUserId(int userId);

        public int insert(@Param("title") String title, @Param("content") String content,
                        @Param("thumbnail") String thumbnail,
                        @Param("userId") int userId);

        public int updateById(@Param("id") int id, @Param("title") String title, @Param("thumbnail") String thumbnail,
                        @Param("content") String content);

        public int deleteById(int id);
}
