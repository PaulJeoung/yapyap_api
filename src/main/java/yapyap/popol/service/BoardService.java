package yapyap.popol.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import yapyap.popol.dao.Board;
import yapyap.popol.dto.BoardDTO;
import yapyap.popol.dto.RequestPageDTO;
import yapyap.popol.dto.ResponsePageDTO;
import yapyap.popol.handler.DataNotFoundException;
import yapyap.popol.repository.BoardRepository;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {

    private final BoardRepository boardRepository;

    // 게시글 전체 조회
    public ResponsePageDTO<BoardDTO> getAllBoardsList(RequestPageDTO requestPageDTO) {
        log.info("getAllBoardsList 시작");
        Pageable pageable = PageRequest.of(requestPageDTO.getPage() - 1
                ,requestPageDTO.getSize()
                ,Sort.by(Sort.Direction.DESC, "id"));
        Page<BoardDTO> boardPage = boardRepository.findAll(pageable).map(this::entityToDTO);
        log.info("getAllBoardsList 리턴");
        return new ResponsePageDTO<>(boardPage);
    }

    // 게시글 아이디 조회
    public BoardDTO getBoardById(Long id) {
        log.info("getBoardById 조회");
        Board board = boardRepository.findById(id).orElse(null);
        if (board == null) {
            throw new DataNotFoundException("board not found");
        }
        log.info("getBoardById 리턴");
        return entityToDTO(board);
    }

    // 게시글 등록
    public BoardDTO addNewBoard(BoardDTO boardDTO) {
        log.info("addNewBoard 시작, title {}", boardDTO.getTitle());
        Board saveResult = boardRepository.save(dtoToEntity(boardDTO));
        log.info("addNewBoard 리턴, id {} ", saveResult.getId());
        return entityToDTO(saveResult);
    }

    // 게시글 수정
    public BoardDTO updateBoardById(Long id, BoardDTO boardDTO) {
        log.info("updateBoardById 시작, id {} ", id);
        Board board = boardRepository.findById(id).orElse(null);
        if (board != null) {
            board.setTitle(boardDTO.getTitle());
            board.setContent(boardDTO.getContent());
            board.setAuthor(board.getAuthor());
            board.setType(boardDTO.getType());
            board.setStatus(boardDTO.getStatus());
            board.setCreateAt(board.getCreateAt());
            Board saveResult = boardRepository.save(board);
            return entityToDTO(saveResult);
        }
        log.info("updateBoardById 리턴, id {} ", board.getId());
        return entityToDTO(board);
    }

    // 게시글 삭제
    public Map<String, String> deleteBoardById(Long id) {
        log.info("deleteBoardById, {}", id);
        boardRepository.deleteById(id);
        Optional<Board> searchById = boardRepository.findById(id);
        if (searchById.isEmpty()) {
            log.info("deleteBoardById 리턴, id 없음");
            return Map.of("result", "delete");
        } else {
            log.info("deleteBoardById 리턴, 삭제 실패");
            return Map.of("result", "delete was fail");
        }
    }

    private BoardDTO entityToDTO(Board board) {
        return BoardDTO.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .author(board.getAuthor())
                .createAt(board.getCreateAt())
                .type(board.getType())
                .status(board.getStatus())
                .build();
    }

    private Board dtoToEntity(BoardDTO boardDTO) {
        return Board.builder()
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .author(boardDTO.getAuthor())
                .createAt(LocalDateTime.now())
                .type(boardDTO.getType())
                .status(boardDTO.getStatus())
                .build();
    }
}
