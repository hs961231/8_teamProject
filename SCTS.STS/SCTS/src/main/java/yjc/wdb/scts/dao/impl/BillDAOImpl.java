package yjc.wdb.scts.dao.impl;

import java.sql.Date;
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
	public List<BillVO> billList(String user_id, int day) throws Exception {
		
		Map map = new HashMap();
		map.put("user_id", user_id);
		map.put("day", day);
		
		return sql.selectList(NAMESPACE + ".billList", map);
	}

	@Override
	public List<HashMap> billOne(int bill_code) throws Exception {
		
		return sql.selectList(NAMESPACE+".billOne", bill_code);
	}

	@Override
	public List<HashMap> settleInfo(String user_id, int bill_code) throws Exception {
		
		Map map = new HashMap();
		map.put("user_id", user_id);
		map.put("bill_code", bill_code);
		
		return sql.selectList(NAMESPACE+".settleInfo", map);
	}

	@Override
	public List<HashMap> recommandProduct(String user_id) throws Exception {
		
		return sql.selectList(NAMESPACE+".recommandProduct", user_id);
	}

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
	public List<HashMap> searchDaySales(Date date1, Date date2) throws Exception {
		
		Map map = new HashMap();
		map.put("date1", date1);
		map.put("date2", date2);
		
		return sql.selectList(NAMESPACE+".searchDaySales", map);
	}

	@Override
	public List<HashMap> daySettle(Date date1, Date date2, int setle_mth_code) {
		
		Map map = new HashMap();
		map.put("date1", date1);
		map.put("date2", date2);
		map.put("setle_mth_code", setle_mth_code);
		
		return sql.selectList(NAMESPACE+".daySettle", map);
	}

}
