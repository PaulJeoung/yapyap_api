package yapyap.popol.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestPageDTO {
    private int page = 1;
    private int size = 10;
}
