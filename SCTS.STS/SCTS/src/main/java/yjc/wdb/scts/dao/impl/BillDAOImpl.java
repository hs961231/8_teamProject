package yjc.wdb.scts.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import yjc.wdb.scts.bean.BillVO;
import yjc.wdb.scts.dao.BillDAO;

@Repository
public class BillDAOImpl implements BillDAO {
	
	private static final String NAMESPACE = "yjc.wdb.mapper.BillMapper";
	
	@Inject
	private SqlSession sql;


	@Override
	public List<HashMap> yearSales(int year) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectList(NAMESPACE+".yearSales", year);
	}

	@Override
	public List<HashMap> daySales() throws Exception {
		// TODO Auto-generated method stub
		return sql.selectList(NAMESPACE+".daySales");
	}

	@Override
	public List<HashMap> searchYear(int year1, int year2) throws Exception {
		Map map = new HashMap();
		map.put("year1", year1);
		map.put("year2", year2);
		
		return sql.selectList(NAMESPACE+".searchYear", map);
	}

	@Override
	public List<HashMap> settleSalesInfo(int year1, int year2) throws Exception {
		Map map = new HashMap();
		map.put("year1", year1);
		map.put("year2", year2);
		
		return sql.selectList(NAMESPACE+".settleSalesInfo", map);
	}

	@Override
	public List<HashMap> daySalesSettleInfo() throws Exception {
		// TODO Auto-generated method stub
		return sql.selectList(NAMESPACE+".daySalesSettleInfo");
	}

	@Override
	public List<HashMap> searchDaySales(String date1, String date2) throws Exception {
		
		Map map = new HashMap();
		map.put("date1", date1);
		map.put("date2", date2);
		
		return sql.selectList(NAMESPACE+".searchDaySales", map);
	}

	@Override
	public List<HashMap> daySettle(String date1, String date2, int setle_mth_code) {
		
		Map map = new HashMap();
		map.put("date1", date1);
		map.put("date2", date2);
		map.put("setle_mth_code", setle_mth_code);
		
		return sql.selectList(NAMESPACE+".daySettle", map);
	}

	@Override
	public List<HashMap> monthSales(String month1, String month2) throws Exception {
		Map map = new HashMap();
		map.put("month1", month1);
		map.put("month2", month2);
		
		
		return sql.selectList(NAMESPACE+".monthSales", map);
	}

	@Override
	public List<HashMap> monthSalesSettleInfo(String month1, String month2, int setle_mth_code) throws Exception {
		Map map = new HashMap();
		map.put("month1", month1);
		map.put("month2", month2);
		map.put("setle_mth_code", setle_mth_code);
		
		return sql.selectList(NAMESPACE+".monthSalesSettleInfo", map);
	}

	@Override
	public List<HashMap> productRank(String date, int standard) throws Exception {
		
		Map map = new HashMap();
		map.put("date", date);
		map.put("standard", standard);
		
		return sql.selectList(NAMESPACE+".productRank", map);
	}

	@Override
	public List<HashMap> productRankInfo(String date ,int standard) throws Exception {
		Map map = new HashMap();
		map.put("date", date);
		map.put("standard", standard);
		
		return sql.selectList(NAMESPACE+".productRankInfo", map);
	}

	@Override
	public List<HashMap> yearToMonth(int year) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectList(NAMESPACE+".yearToMonth", year);
	}

	@Override
	public List<HashMap> genderSales(String date, String gender) throws Exception {
		Map map = new HashMap();
		map.put("date", date);
		map.put("gender", gender);
		
		return sql.selectList(NAMESPACE+".genderSales", map);
	}

	@Override
	public List<HashMap> ageSales(String date, int age, int standard, String gender) throws Exception {
		Map map = new HashMap();
		map.put("date", date);
		map.put("age", age);
		map.put("standard", standard);
		map.put("gender", gender);
		
		return sql.selectList(NAMESPACE+".ageSales", map);
	}

	@Override
	public List<HashMap> ageSalesInfo(String date, int age, int standard, String gender) throws Exception {
		
		Map map = new HashMap();
		map.put("date", date);
		map.put("age", age);
		map.put("standard", standard);
		map.put("gender", gender);
		
		return sql.selectList(NAMESPACE+".ageSalesInfo", map);
	}

	@Override
	public int todaySales() throws Exception {
		return sql.selectOne(NAMESPACE+".todaySales");
	}

	@Override
	public int monthTotalSales() throws Exception {
		
		return sql.selectOne(NAMESPACE+".monthTotalSales");
	}

}
