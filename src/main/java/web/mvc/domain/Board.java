package web.mvc.domain;

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
	private String boardTitle;
	private String boardImg;
	private long boardLikeNo;
	
	@Column(nullable = true)
	private String boardContent;

	@CreationTimestamp
	private String boardRegDate;
}
