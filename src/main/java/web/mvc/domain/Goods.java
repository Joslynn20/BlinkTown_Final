package web.mvc.domain;

import java.time.LocalDate;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Goods extends Product {
	
	

	

	public Goods(String productCode, String productName, String productEngName, int productPrice, int productStock,
			String productSize, LocalDate productRegDate, String productMainImg, String productDetailImg,
			int productReadNo, Category category, Integer productMembershipOnly, String goodsMaterial,
			String goodsCountry) {
		super(productCode, productName, productEngName, productPrice, productStock, productSize, productRegDate,
				productMainImg, productDetailImg, productReadNo, category, productMembershipOnly);
		this.goodsMaterial = goodsMaterial;
		this.goodsCountry = goodsCountry;
	}
	private String goodsMaterial;
	private String goodsCountry;

}
