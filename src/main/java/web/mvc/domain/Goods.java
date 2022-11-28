package web.mvc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Goods extends Product {

	private String goodsMaterial;
	private String goodsCountry;

	@Column(columnDefinition = "number(1) default 0")
	private int goodsMembershipOnly;
}
