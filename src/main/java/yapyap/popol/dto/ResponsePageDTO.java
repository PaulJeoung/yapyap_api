package yapyap.popol.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class ResponsePageDTO<T> {

    private List<T> contentList;
    private int currentPage;
    private int totalPages;
    private long totalElements;
    private int size;

    public ResponsePageDTO(Page<T> page) {
        this.currentPage = page.getNumber() + 1; // index start 0
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.size = page.getSize();
        this.contentList = page.getContent();
    }
}
