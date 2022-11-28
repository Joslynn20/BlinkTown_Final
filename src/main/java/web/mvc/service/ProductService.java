package web.mvc.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import web.mvc.domain.Album;
import web.mvc.domain.Goods;
import web.mvc.domain.Product;

public interface ProductService {

	/**
	 * 멤버쉽 카드 등록
	 * @param 카테고리, 상품코드, 상품명, 상품영문명, 가격, 대표이미지, 상세이미지
	 */
	void insertProduct(Product product);
	
	/**
	 * 앨범 등록
	 * @param 카테고리, 상품코드, 상품명, 상품영문명, 가격, 재고, 사이즈, 대표이미지, 상세이미지, 구성, 발매일
	 */
	void insertAlbum(Album album);
	
	/**
	 * 굿즈 등록
	 * @param 카테고리, 상품코드, 상품명, 상품영문명, 가격, 재고, 사이즈, 대표이미지, 상세이미지, 재질, 제조국, 멤버쉽 온리(유료회원전용) 여부
	 */
	void insertGoods(Goods goods);

	/**
	 * 상품 수정: 멤버쉽, 앨범, 굿즈 수정
	 * @param 가격, 상품 상세정보, 재고량
	 */
	void updateProduct(Product product);

	/**
	 * 상품 삭제
	 * @param 상품코드
	 */
	void deleteByProductCode(String productCode);

	/**
	 * 상품 전체 조회
	 * @param pageable(페이지 설정), 정렬 조건
	 * @return 전체 상품 리스트
	 */
	List<Product> selectAllProduct(Pageable pageable, String orderCondition);

	/**
	 * 상품 상세 조회
	 * @param 상품코드
	 * @return 상품 정보
	 */
	Product selectByProductCode(String productCode);
	
	/**
	 * 카테고리별 조회: 카테고리 - 멤버쉽온리, 굿즈, 앨범, 멤버쉽 카드
	 * @param 카테고리 코드, 정렬 조건
	 * @return 카테고리별 전체 상품 정보
	 */
	List<Product> selectByCategoryCode(String categoryCode, String orderCondition);

	
}
