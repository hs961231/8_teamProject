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

}
