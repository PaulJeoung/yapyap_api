package yapyap.popol.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yapyap.popol.dto.BoardDTO;
import yapyap.popol.dto.RequestPageDTO;
import yapyap.popol.dto.ResponsePageDTO;
import yapyap.popol.service.BoardService;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public ResponsePageDTO<BoardDTO> getBoardList(@ModelAttribute RequestPageDTO requestPageDTO) {
      log.info("Get board list 컨트롤러 조회");
      return boardService.getAllBoardsList(requestPageDTO);
    }
}
