package web.mvc.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 주문 테이블
 * 참조 : Users-users_id
 * @author 강주형
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Orders_gen")
	@SequenceGenerator(name="Orders_gen", allocationSize=1, sequenceName="Orders_seq")
	private Long ordersNo;
	
//	@JsonIgnore
//	@ManyToOne(fetch = FetchType.LAZY)
	@ManyToOne
	@JoinColumn(name="users_id")
	private Users users; //코드 합칠때 객체 주소 주의
	
	@Column(nullable=false, length = 40)
	private String ordersReceiverName;
	@Column(nullable=false, length = 11)
	private String ordersReceiverPhone;
	@Column(nullable=false, length = 250)
	private String ordersAddr;
	@Column(nullable=false, length = 6)
	private String ordersZipcode; 
	@Column(nullable=false, length = 20)
	private String ordersStatus;
	
	@Column(nullable=false)
	@CreationTimestamp
	private LocalDateTime ordersDate;
	
	//LAZY , 주문결제-취소시 삭제 필요하여 cascade설정
//	@JsonManagedReference //안됨
	@JsonIgnore 
	@OneToMany(mappedBy = "orders", cascade = CascadeType.ALL) 
	private List<Orderdetails> orderdetailsList;
}
