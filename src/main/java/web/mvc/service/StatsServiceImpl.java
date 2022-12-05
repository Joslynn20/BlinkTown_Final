package web.mvc.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import web.mvc.domain.Orderdetails;
import web.mvc.domain.Orders;
import web.mvc.dto.Stats;
import web.mvc.repository.StatsInterface;
import web.mvc.repository.StatsOrdersRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class StatsServiceImpl implements StatsService {
	
	
	private final StatsOrdersRepository statsOrderRep;
	
	/**
	 * 전체 매출 조회 & 월별 매출조회
	 * 결제일-인수, 리턴 - OrderdetailsPrice (null이면 전체조회)
	 */
	@Override
	public int findByGetMonth(String month) throws Exception{
		StatsInterface impl = null;
		  if(month==null) 
			  impl = statsOrderRep.findTotalPrice();
		  else  
			  impl = statsOrderRep.findByGetMonth(month);
		  
			return impl.getTotalprice();
	}
	
	
	/**
	 * 앨범별
	 * productCode -인수
	 * */
	@Override
	public Stats findAlbumStats(String productCode) throws Exception {
		StatsInterface result = statsOrderRep.findAlbumStats(productCode);
		return new Stats(result.getAlbumTotalPrice(), result.getAlbumTotalQty());
	}
	
	


}
