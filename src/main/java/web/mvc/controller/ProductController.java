package web.mvc.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import web.mvc.domain.Product;
import web.mvc.service.ProductService;

@Controller
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService service;
	@RequestMapping("/{url}")
	public void test() {}
	/**
	@RequestMapping("/insert")
	public String insertProduct(Product product) {

		service.insertProduct(product);

		return "";
	}

	@RequestMapping("/update")
	public String updateProdut(Product product) {
		service.updateProduct(product);
		return "";
	}

	@RequestMapping("/delete")
	public String deleteByProductCode(String productCode) {
		service.deleteByProductCode(productCode);
		return "";
	}

	@RequestMapping("/select")
	public List<Product> selectAllProduct() {
		return null;
	};

	@RequestMapping("/select/{productCode}")
	public Product selectByProductCode(@PathVariable String productCode, Boolean flag) {
		return null;
	};

	@RequestMapping("/decreaseProductStock")
	public String decreaseProductStock(String productCode, int qty) {
		return "";
	}
*/
}
