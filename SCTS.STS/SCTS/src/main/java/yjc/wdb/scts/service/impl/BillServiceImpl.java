package yjc.wdb.scts.service.impl;


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
	public List<HashMap> searchDaySales(String date1, String date2) throws Exception {
		// TODO Auto-generated method stub
		return dao.searchDaySales(date1, date2);
	}

	@Override
	public List<HashMap> daySettle(String date1, String date2, int setle_mth_code) {
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
	public List<HashMap> productRank(String date, int standard) throws Exception {
		
		return dao.productRank(date, standard);
	}

	@Override
	public List<HashMap> productRankInfo(String date, int standard) throws Exception {
		
		return dao.productRankInfo(date, standard);
	}

	@Override
	public List<HashMap> yearToMonth(int year) throws Exception {
		
		return dao.yearToMonth(year);
	}

	@Override
	public List<HashMap> genderSales(String date, String gender) throws Exception {
		
		return dao.genderSales(date, gender);
	}

	@Override
	public List<HashMap> ageSales(String date, int age, int standard, String gender) throws Exception {
	
		return dao.ageSales(date, age, standard, gender);
	}

	@Override
	public List<HashMap> ageSalesInfo(String date, int age, int standard, String gender) throws Exception {
		// TODO Auto-generated method stub
		return dao.ageSalesInfo(date, age, standard, gender);
	}

	@Override
	public int todaySales() throws Exception {
		// TODO Auto-generated method stub
		return dao.todaySales();
	}

	@Override
	public int monthTotalSales() throws Exception {
		
		return dao.monthTotalSales();
	}

}
