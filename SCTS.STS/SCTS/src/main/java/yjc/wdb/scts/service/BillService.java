package yjc.wdb.scts.service;


import java.util.HashMap;
import java.util.List;

import yjc.wdb.scts.bean.BillVO;

public interface BillService {
	
	public List<HashMap> yearSales(int year) throws Exception;
	public List<HashMap> searchYear(int year1, int year2) throws Exception;
	public List<HashMap> settleSalesInfo(int year1, int year2) throws Exception;
	public List<HashMap> daySales() throws Exception;
	public List<HashMap> daySalesSettleInfo() throws Exception;
	public List<HashMap> searchDaySales(String date1, String date2) throws Exception;
	public List<HashMap> daySettle(String date1, String date2, int setle_mth_code);
	public List<HashMap> monthSales(String month1, String month2) throws Exception;
	public List<HashMap> monthSalesSettleInfo(String month1, String month2, int setle_mth_code) throws Exception;
	public List<HashMap> productRank(String date, int standard) throws Exception;
	public List<HashMap> productRankInfo(String date, int standard) throws Exception;
	public List<HashMap> yearToMonth(int year) throws Exception;
	public List<HashMap> genderSales(String date, String gender) throws Exception;
	public List<HashMap> ageSales(String date, int age, int standard, String gender) throws Exception;
	public List<HashMap> ageSalesInfo(String date, int age, int standard, String gender) throws Exception;
	public int todaySales() throws Exception;
	public int monthTotalSales() throws Exception;
	
	   // ���� ���� �� ���� ������ ���� ����
	   public void payment(String user_id, int stprc, String setle_mth_nm, List<HashMap<String, String>> goodsList) throws Exception;
	
	
}
