package web.mvc.domain;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Album extends Product {

	
	

	public Album(String productCode, String productName, String productEngName, int productPrice, int productStock,
			String productSize, LocalDate productRegDate, String productMainImg, String productDetailImg,
			int productReadNo, Category category, Integer productMembershipOnly, String albumComponent,
			Date albumReleaseDate) {
		super(productCode, productName, productEngName, productPrice, productStock, productSize, productRegDate,
				productMainImg, productDetailImg, productReadNo, category, productMembershipOnly);
		this.albumComponent = albumComponent;
		this.albumReleaseDate = albumReleaseDate;
	}
	private String albumComponent;
	private Date albumReleaseDate;

}
