package yapyap.popol.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import yapyap.popol.dao.Board;
import yapyap.popol.dto.BoardDTO;
import yapyap.popol.dto.RequestPageDTO;
import yapyap.popol.dto.ResponsePageDTO;
import yapyap.popol.repository.BoardRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {

    private final BoardRepository boardRepository;

    // 게시글 전체 조회
    public ResponsePageDTO<BoardDTO> getAllBoardsList(RequestPageDTO requestPageDTO) {
        log.info("getAllBoardsList 시작");
        Pageable pageable = PageRequest.of(requestPageDTO.getPage() - 1, requestPageDTO.getSize());
        Page<BoardDTO> boardPage = boardRepository.findAll(pageable).map(this::entityToDTO);
        log.info("getAllBoardsList 리턴");
        return new ResponsePageDTO<>(boardPage);
    }

    // 게시글 등록
    public BoardDTO addNewBoard(BoardDTO boardDTO) {
        log.info("addNewBoard 시작, title {} : ", boardDTO.getTitle());
        Board saveResult = boardRepository.save(dtoToEntity(boardDTO));
        log.info("addNewBoard 리턴, id {} : ", saveResult.getId());
        return entityToDTO(saveResult);
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
