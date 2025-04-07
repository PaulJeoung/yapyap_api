package yapyap.popol.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import yapyap.popol.dto.BoardDTO;

import java.time.LocalDateTime;
import java.util.Random;

@SpringBootTest
@Slf4j
class BoardServiceTest {

    @Autowired BoardService boardService;
    @Test
    void 게시글50개_랜덤생성() {
        String[] titles = {
                "기능 요구사항 분석",
                "플랫폼 고도화 및 시스템 개선을 위한 데이터 검수 및 개선작업 수행",
                "테스트 계획/설계/케이스 작성 및 수행",
                "Postman 활용한 API테스트 및 결과보고",
                "UI자동화 도구 Cypress를 활용한 테스트 스크립트 작성 및 결과보고"
        };
        String[] contents = {
                "FactBlock은 웹3.0 에코시스템 빌더로서 블록체인 산업",
                "정보 비대칭성을 효과적으로 해결하고 시장 투명성을 높여 산업의 무결성을 향상시키는데 전념",
                "FactBlock은 한국 진출을 모색하는 글로벌 기업들에게 성장의 기회를 제공",
                "2018년부터 아시아 최대 규모의 웹3.0 행사인 Korea Blockchain Week를 주최",
                "KBW는 업계 최고의 글로벌 리더를 초청하고 다양한 커뮤니티를 유치하여 혁신적인 비즈니스가 탄생하는 허브",
                "비즈니스 개발과 자문, 컨설팅, PR, 컨퍼런스, 이벤트 등 다양한 서비스를 제공"
        };
        String[] authors = {"고애신", "오애순", "양사장", "Tommy", "TESTER", "QA-TEST", "AUTO=BOT"};
        String[] statuses = {"SUGGEST", "QNA", "OPEN", "INPROGRESS", "COMPLETE", "REVIEW", "REOPEN", "CLOSE"};
        String[] types = {"ISSUE", "ARCHITECTURE", "DESIGN", "DEVELOPED", "TASK", "REPORT", "AGENDA"};

        Random random = new Random();

        for (int i = 1; i <= 50; i++) {
            LocalDateTime randomDate = LocalDateTime.now()
                    .withMonth(random.nextInt(12) + 1)
                    .withDayOfMonth(random.nextInt(28) + 1) // 안전하게 28일까지만
                    .withHour(random.nextInt(24))
                    .withMinute(random.nextInt(60));
            BoardDTO data = BoardDTO.builder()
                    .title(titles[random.nextInt(titles.length)] + " " + i)
                    .content(contents[random.nextInt(contents.length)])
                    .author(authors[random.nextInt(authors.length)])
                    .createAt(randomDate)
                    .status(statuses[random.nextInt(statuses.length)])
                    .type(types[random.nextInt(types.length)])
                    .build();

            boardService.addNewBoard(data);
        }
    }
}