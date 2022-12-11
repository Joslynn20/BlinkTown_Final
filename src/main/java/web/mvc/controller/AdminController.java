package web.mvc.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import web.mvc.domain.Orders;
import web.mvc.dto.Stats;
import web.mvc.service.OrdersService;
import web.mvc.service.StatsService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private OrdersService ordersService;
	@Autowired
	private StatsService statsService;
	
	/**상수관리*/
	private static final String BORN_PINK="A01"; //앨범에 해당하는 상품코드
	private static final String THE_ALBUM="A02";
	private static final String KILL_THIS_LOVE="A03";
	private static final String SQUARE_UP="A04";
	
	
	/**
	 * 경로 : admin/main.jsp
	 */
	@RequestMapping("/main")
	public void main() {}//main end
	/////////////////////////////////////////////////
	/**
	 * 관리자페이지-주문목록 출력 ajax
	 * 위치 : #tab3 / class="tbl-content" id="ordersList" / 이벤트 클릭 : #ordersTab3
	 */
	@ResponseBody
	@RequestMapping("/ordersList")
	public List<Orders> ordersList() {
		System.out.println("ordersList시작");
		//위치 : #tab3 / class="tbl-content" id="ordersList" / 이벤트 클릭 : #ordersTab3
		List<Orders> ordersList=ordersService.selectAllOrdersAdmin();
		System.out.println("ordersList="+ordersList);
		return ordersList;
	}//ordersList end
	/////////////////////////////////////////////////
	/**
	 * 관리자페이지-챠트 에 넣을 데이터 출력 ajax
	 * 위치 : #tab4 / ajax로 데이터 호출 후 챠트에 넣음 / 이벤트 클릭 : #statsTab4
	 * 전체 주문 매출 : allStats (totalPrice)
	 * 월별 주문 매출 : monthStats(month , totalPrice)
	 * 앨범 주문 매출 : albumStats(albumTotalPrice, albumTotalQty)
	 */
	@ResponseBody
	@RequestMapping("/statsChart")
	public Map<String, Object> statsChart(){
		Map<String, Object> map=new HashMap<String, Object>();
		//전체 주문 매출 ordersAllStats
		Stats allStats=statsService.findByAllStats();
		map.put("allStats", allStats);//Long totalPrice만 담김 / #allStats 위치에 표시
		
		//월별매출-날짜, 매출 : String month, Long totalPrice
		List<Stats> statsList=statsService.findByMonthStats();
		map.put("monthStats", statsList);//String month, Long totalPrice/ #monthStats 위치에 표시
		
		List<String> monthList=new ArrayList<String>();
		List<Long> priceList=new ArrayList<Long>();
		for(Stats stats:statsList) {
			monthList.add(stats.getMonth());
			priceList.add(stats.getTotalPrice());
		}
		map.put("months", monthList);//옮기기 좋게 따로 담기
		map.put("prices", priceList);
		
		//앨범매출-매출(현재 타이틀 4가지) Long albumTotalPrice / Long albumTotalQty
		Stats bornPink=statsService.findAlbumStats(BORN_PINK);
		map.put("bornPink", bornPink);
		Stats theAlbum=statsService.findAlbumStats(THE_ALBUM);
		map.put("theAlbum", theAlbum);
		Stats killThisLove=statsService.findAlbumStats(KILL_THIS_LOVE);
		map.put("killThisLove", killThisLove);
		Stats squareUp=statsService.findAlbumStats(SQUARE_UP);
		map.put("squareUp", squareUp);
//		System.out.println("bornPink.albumPrice/Qty="+bornPink.getAlbumTotalPrice()+"/"+bornPink.getAlbumTotalQty());
		
		return map;
	}
}
