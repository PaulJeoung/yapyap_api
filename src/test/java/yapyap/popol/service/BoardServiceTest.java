package yapyap.popol.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import yapyap.popol.dto.BoardDTO;

import java.time.LocalDateTime;

@SpringBootTest
@Slf4j
class BoardServiceTest {

    @Autowired BoardService boardService;
    @Test
    void 게시글100() {

        for (int i = 1; i < 101; i++) {
            BoardDTO data = BoardDTO.builder()
                    .title("테스트 타이틀 " + i)
                    .content("테스트 컨텐트 " + i)
                    .author("manager " + i)
                    .createAt(LocalDateTime.now())
                    .status("prepare")
                    .type("Question")
                    .build();
            boardService.addNewBoard(data);
        }
    }



}