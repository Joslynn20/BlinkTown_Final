package web.mvc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;
import web.mvc.domain.Product;
import web.mvc.service.ProductService;

@Controller
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService service;

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
	@ResponseBody
	public List<Product> selectAllProduct(String categoryCode, Integer GoodsMembershipOnly, String orderCondition) {
		List<Product> productList = service.selectAllProduct(categoryCode, GoodsMembershipOnly, orderCondition);

		/*
		 * Map<String, String> categoryCodeList = new HashMap<>();
		 * 
		 * for(Product product :productList) {
		 * categoryCodeList.put(product.getProductCode(),
		 * product.getCategory().getCategoryCode()); }
		 */
		/*
		 * Map<String, Object> map= new HashMap<String, Object>();
		 * map.put("productList", productList); map.put("categoryCodeList",
		 * categoryCodeList);
		 */

		return productList;
	};

	@RequestMapping("/select/{productCode}")
	public ModelAndView selectByProductCode(@PathVariable String productCode, Boolean flag) {
		System.out.println("productCode"+productCode);
		Product product = service.selectByProductCode(productCode, flag);
		return new ModelAndView("shop/details"+product.getCategory().getCategoryCode().toUpperCase(), "product", product);
	};

	@RequestMapping("/decreaseProductStock")
	public String decreaseProductStock(String productCode, int qty) {
		return "";
	}

	@GetMapping("/{url}")
	public void url() {
	}

}
