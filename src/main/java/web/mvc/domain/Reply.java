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
public class Reply {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "replyNo_seq")
	@SequenceGenerator(name = "replyNo_seq", allocationSize = 1, sequenceName = "replyNo_seq")
	private Long replyNo;
	
	@Column(nullable = false)
	private Long boardNo;
	private String userId;
	private String replyContent;
	
	@CreationTimestamp
	private LocalDate replyRegDate;

}
