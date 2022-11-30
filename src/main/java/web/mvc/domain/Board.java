package web.mvc.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;

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
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "boardNo_seq")
	@SequenceGenerator(name = "boardNo_seq", allocationSize = 1, sequenceName = "boardNo_seq")
	private Long boardNo;
	
	@Column(nullable = false)
	private String userId;
	
	@Column(nullable = false)
	private String boardTitle;
	
	@Column(nullable = true)
	private String boardContent;
	
	@Column(nullable = false)
	private String boardImg;

	@CreationTimestamp
	private LocalDate boardRegDate;
	
	@Column(nullable = false)
	private int boardLikeNo;


}
