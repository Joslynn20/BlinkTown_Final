package web.mvc.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;
import web.mvc.domain.Album;
import web.mvc.domain.Category;
import web.mvc.domain.Goods;
import web.mvc.domain.Product;
import web.mvc.service.ProductService;

@Controller
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService service;
	private static final String MAIN_DIR = "/save/shopImg/title";
	private static final String DETAIL_DIR = "/save/shopImg/detail";

	@RequestMapping("/insertAlbum")
	public String insertProduct(Album album, Category category, MultipartFile mainImg, MultipartFile detailImg,
			HttpSession session) {
		album.setCategory(category);

		String mainDir = session.getServletContext().getRealPath(MAIN_DIR);
		String mainFileName = mainImg.getOriginalFilename();

		String detailDir = session.getServletContext().getRealPath(DETAIL_DIR);
		String detailFileName = detailImg.getOriginalFilename();

		try {
			mainImg.transferTo(new File(mainDir + "/" + mainFileName));
			detailImg.transferTo(new File(detailDir + "/" + detailFileName));

			album.setProductMainImg(mainFileName);
			album.setProductDetailImg(detailFileName);

		} catch (Exception e) {
			e.printStackTrace();
		}

		service.insertProduct(album);

		return "admin/main";
	}

	@RequestMapping("/insertGoods")
	public String insertProduct(Goods goods, Category category, MultipartFile mainImg, MultipartFile detailImg,
			HttpSession session) {
		goods.setCategory(category);

		String mainDir = session.getServletContext().getRealPath(MAIN_DIR);
		String mainFileName = mainImg.getOriginalFilename();

		String detailDir = session.getServletContext().getRealPath(DETAIL_DIR);
		String detailFileName = detailImg.getOriginalFilename();

		try {
			mainImg.transferTo(new File(mainDir + "/" + mainFileName));
			detailImg.transferTo(new File(detailDir + "/" + detailFileName));

			goods.setProductMainImg(mainFileName);
			goods.setProductDetailImg(detailFileName);

		} catch (Exception e) {
			e.printStackTrace();
		}

		service.insertProduct(goods);

		return "admin/main";
	}

	@RequestMapping("/update")
	@ResponseBody
	public Product updateProdut(Product product) {
		return service.updateProduct(product);
	}

	@RequestMapping("/delete")
	public String deleteByProductCode(String productCode) {
		service.deleteByProductCode(productCode);
		return "admin/main";
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
		System.out.println("productCode" + productCode);
		Product product = service.selectByProductCode(productCode, flag);
		return new ModelAndView("shop/details" + product.getCategory().getCategoryCode().toUpperCase(), "product",
				product);
	};

	@RequestMapping("/decreaseProductStock")
	public String decreaseProductStock(String productCode, int qty) {
		return "";
	}

	@GetMapping("/{url}")
	public void url() {
	}

}
