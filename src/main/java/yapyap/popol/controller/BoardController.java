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
    @ResponseBody
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

    @PutMapping("/update/{id}")
    @ResponseBody
    public BoardDTO updateBoard(@PathVariable("id") Long id, @RequestBody BoardDTO boardDTO) {
        log.info("updateBoard 컨트롤러 생성 {}", boardDTO);
        if (boardDTO.getTitle() == null || boardDTO.getTitle().isEmpty()){
            log.info("updateBoard 제목 누락으로 리턴");
            return null;
        }
        return boardService.updateBoardById(id, boardDTO);
    }

    @GetMapping("/list/{id}")
    @ResponseBody
    public BoardDTO getBoardDetail(@PathVariable("id") Long id) {
        log.info("Get Board Detail 컨트롤러 조회");
        return boardService.getBoardById(id);
    }

    @DeleteMapping("/list/{id}")
    public Map<String, String> deleteBoard(@PathVariable("id") Long id) {
        log.info("Delete Board 삭제 컨트롤러 조회");
        return boardService.deleteBoardById(id);
    }
}