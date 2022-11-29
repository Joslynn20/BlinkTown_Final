package web.mvc.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "category")
@Inheritance(strategy = InheritanceType.JOINED)
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String productCode;

	@Column(nullable = false)
	private String productName;

	@Column(nullable = false)
	private String productEngName;

	private int productPrice;

	@Column(nullable = true)
	private int productStock;
	private String productSize;

	@CreationTimestamp
	private LocalDate productRegDate;

	@Column(nullable = false)
	private String productMainImg;
	@Column(nullable = false)
	private String productDetailImg;
	@Column(nullable = false)
	private int productReadNo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryCode")
	private Category category;

}
