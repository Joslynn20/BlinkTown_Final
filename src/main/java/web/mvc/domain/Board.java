package web.mvc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long boardNo;
	
	@Column(nullable = false)
	private String userId;
	private String boardTitle;
	private String boardImg;
	private String boardRegDate;
	private int boardLikeNo;
	
	@Column(nullable = true)
	private String boardContent;
}
