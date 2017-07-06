package yjc.wdb.scts.dao;


import java.util.HashMap;
import java.util.List;

import yjc.wdb.scts.bean.BillVO;

public interface BillDAO {

	public List<HashMap> yearSales(int year, int bhf_code) throws Exception;
	public List<HashMap> searchYear(int year1, int year2,  int bhf_code) throws Exception;
	public List<HashMap> settleSalesInfo(int year1, int year2,  int bhf_code) throws Exception;
	public List<HashMap> daySales(int bhf_code) throws Exception;
	public List<HashMap> daySalesSettleInfo( int bhf_code) throws Exception;
	public List<HashMap> searchDaySales(String date1, String date2,  int bhf_code) throws Exception;
	public List<HashMap> daySettle(String date1, String date2, int setle_mth_code,  int bhf_code);
	public List<HashMap> monthSales(String month1, String month2,  int bhf_code) throws Exception;
	public List<HashMap> monthSalesSettleInfo(String month1, String month2, int setle_mth_code, int bhf_code) throws Exception;
	public List<HashMap> productRank(String date, int standard, int bhf_code) throws Exception;
	public List<HashMap> productRankInfo(String date, int standard, int bhf_code) throws Exception;
	public List<HashMap> yearToMonth(int year, int bhf_code) throws Exception;
	public List<HashMap> genderSales(String date, String gender, int bhf_code) throws Exception;
	public List<HashMap> ageSales(String date, int age, int standard, String gender, int bhf_code) throws Exception;
	public List<HashMap> ageSalesInfo(String date, int age, int standard, String gender, int bhf_code) throws Exception;
	public int todaySales(int bhf_code) throws Exception;
	public int monthTotalSales(int bhf_code) throws Exception;

	public void insertBill(String user_id) throws Exception;
	public void updateTotamt() throws Exception;
	
}

