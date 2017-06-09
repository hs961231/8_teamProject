package yjc.wdb.scts.service.impl;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import yjc.wdb.scts.bean.BillVO;
import yjc.wdb.scts.dao.BillDAO;
import yjc.wdb.scts.service.BillService;

@Service
public class BillServiceImpl implements BillService {
	
	@Inject
	private BillDAO dao;

	@Override
	public List<BillVO> billList(String user_id, int day) throws Exception {
		
		return dao.billList(user_id, day);
	}

	@Override
	public List<HashMap> billOne(int bill_code) throws Exception {
		
		return dao.billOne(bill_code);
	}

	@Override
	public List<HashMap> settleInfo(String user_id, int bill_code) throws Exception {
		
		return dao.settleInfo(user_id, bill_code);
	}

	@Override
	public List<HashMap> recommandProduct(String user_id) throws Exception {
		
		return dao.recommandProduct(user_id);
	}

	@Override
	public List<HashMap> yearSales(int year) throws Exception {
		// TODO Auto-generated method stub
		return dao.yearSales(year);
	}

	@Override
	public List<HashMap> daySales() throws Exception {
		// TODO Auto-generated method stub
		return dao.daySales();
	}

	@Override
	public List<HashMap> searchYear(int year1, int year2) throws Exception {
		// TODO Auto-generated method stub
		return dao.searchYear(year1, year2);
	}

	@Override
	public List<HashMap> settleSalesInfo(int year1, int year2) throws Exception {
		
		return dao.settleSalesInfo(year1, year2);
	}

	@Override
	public List<HashMap> daySalesSettleInfo() throws Exception {
		
		return dao.daySalesSettleInfo();
	}

	@Override
	public List<HashMap> searchDaySales(Date date1, Date date2) throws Exception {
		// TODO Auto-generated method stub
		return dao.searchDaySales(date1, date2);
	}

	@Override
	public List<HashMap> daySettle(Date date1, Date date2, int setle_mth_code) {
		// TODO Auto-generated method stub
		return dao.daySettle(date1, date2, setle_mth_code);
	}

	@Override
	public List<HashMap> monthSales(String month1, String month2) throws Exception {
		// TODO Auto-generated method stub
		return dao.monthSales(month1, month2);
	}

	@Override
	public List<HashMap> monthSalesSettleInfo(String month1, String month2, int setle_mth_code) throws Exception {
		// TODO Auto-generated method stub
		return dao.monthSalesSettleInfo(month1, month2, setle_mth_code);
	}

	@Override
	public List<HashMap> productRank(String date) throws Exception {
		
		return dao.productRank(date);
	}

	@Override
	public List<HashMap> productRankInfo(String date) throws Exception {
		
		return dao.productRankInfo(date);
	}

	@Override
	public List<HashMap> yearToMonth(int year) throws Exception {
		
		return dao.yearToMonth(year);
	}

}
