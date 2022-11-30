package web.mvc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import web.mvc.domain.Board;
import web.mvc.repository.BoardRepository;


@SpringBootTest
class BlinkTownFinalApplicationTests {

	@Autowired
	private BoardRepository boardRep;
	
	@Test
	void contextLoads() {
		//Board에 30개 레코드 추가
		for(int i=1; i<=30; i++) {
			boardRep.save(Board.builder()
					.boardTitle("제목"+i)
					.userId("User"+i)
					.boardContent("Board Test"+i)
					.boardImg(null)
					.boardLikeNo(0)
					.build());		
		}
	}
}
