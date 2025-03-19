package yapyap.popol.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import yapyap.popol.dto.BoardDTO;
import yapyap.popol.dto.RequestPageDTO;
import yapyap.popol.dto.ResponsePageDTO;
import yapyap.popol.service.BoardService;

import java.util.Map;

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

    @PostMapping("/")
    public BoardDTO addNewBoard(@RequestBody BoardDTO boardDTO) {
        log.info("createBoard 컨트롤러 생성 {}", boardDTO);
        if (boardDTO.getTitle() == null || boardDTO.getTitle().isEmpty()){
            log.info("createBoard 제목 누락으로 리턴");
            return null;
        }
        return boardService.addNewBoard(boardDTO);
    }
}
