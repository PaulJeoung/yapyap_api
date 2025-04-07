package yapyap.popol.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import yapyap.popol.dto.BoardDTO;
import yapyap.popol.dto.RequestPageDTO;
import yapyap.popol.dto.ResponsePageDTO;
import yapyap.popol.handler.DataNotFoundException;
import yapyap.popol.service.BoardService;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardTController {

    private final BoardService boardService;

    @GetMapping("/board")
    public String getBoardListByPage(Model model,
                                     @RequestParam(name = "page", defaultValue = "1") Integer page,
                                     @RequestParam(name = "size", defaultValue = "10") Integer size) {
        RequestPageDTO requestPageDTO = new RequestPageDTO();
        requestPageDTO.setPage(page);
        requestPageDTO.setSize(size);
        ResponsePageDTO<BoardDTO> boardsList = boardService.getAllBoardsList(requestPageDTO);
        model.addAttribute("boardList", boardsList);
        return "board_list";
    }

    @GetMapping("/")
    public String home() {
        log.info("/ 페이지 호출");
        return "redirect:/board";
    }

    @GetMapping(value = "/board/detail/{id}")
    public String getBoardDetail(Model model, @PathVariable("id") Long id) {
        log.info("Get board detail 템플릿 조회");
        BoardDTO boardDTO = boardService.getBoardById(id);
        model.addAttribute("boardDTO", boardDTO);
        return "board_detail";
    }

    @GetMapping("/board/create")
    public String getCreateBoard(BoardDTO boardDTO) {
        log.info("게시글 작성 템플릿 호출");
        return "board_create";
    }

    @PostMapping("/board/create")
    public String addNewBoard(@RequestParam(value = "title") String title, @RequestParam(value = "content") String content,
                              @RequestParam(value = "status") String status, @RequestParam(value = "type") String type) {
        log.info("게시글 등록 호출");
        if (title.isEmpty() || content.isEmpty() || status.isEmpty()) {
            throw new DataNotFoundException("Data is not satisfied\n모든 내용을 입력했는지 다시 한번 확인해 주세요 (404 에러)");
        }
        BoardDTO boardDTO = BoardDTO.builder()
                .title(title)
                .content(content)
                .status(status)
                .type(type)
                .author("시험자")
                .build();
        BoardDTO addBoardDTO = boardService.addNewBoard(boardDTO);
        log.info("게시글 등록 결과 \n{}\n", addBoardDTO.toString());
        return "redirect:/board";
    }

    @GetMapping("/board/modify/{id}")
    public String getModifyBoardTemplate(@PathVariable("id") Long id, Model model) {
        log.info("게시글 수정 템플릿 호출 {}", id);
        BoardDTO boardDTO = boardService.getBoardById(id);
        if (boardDTO == null) {
            throw new DataNotFoundException("Board id is not found");
        }
        model.addAttribute("boardDTO", boardDTO);
        return "board_modify";
    }

    @PostMapping("/board/modify/{id}")
    public String modifyBoard(@PathVariable("id") Long id, BoardDTO boardDTO) {
        log.info("게시글 수정 템플릿 컨트롤러로 실행");
        if (boardDTO.getTitle() == null || boardDTO.getContent() == null || boardDTO.getType() == null || boardDTO.getStatus() == null) {
            throw new DataNotFoundException("Board data is not filled yet\n데이터를 확인하고 모두 채워 주세요");
        }
        BoardDTO boardResult = boardService.updateBoardById(id, boardDTO);
        log.info("수정이 완료 되었습니다 {}",boardResult.toString());
        return String.format("redirect:/board/detail/%s", id);
    }

    @GetMapping("/board/delete/{id}")
    public String deleteBoard(@PathVariable("id") Long id) {
        log.info("게시글 삭제 컨트롤러 호출");
        Map<String, String> result = boardService.deleteBoardById(id);
        log.info("삭제 결과 확인 {}", result);
        return "redirect:/board";
    }
}
