package yjc.wdb.scts.DAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class PurchaseInfoDAOImpl implements PurchaseInfoDAO {
	
	private static final String NAMESPACE = "yjc.wdb.mapper.PurchaseInfoMapper";
	
	@Inject
	private SqlSession sql;

	@Override
	public List<HashMap> billList(String user_id, int day) throws Exception {

		Map list = new HashMap();
		list.put("user_id", user_id);
		list.put("day", day);
		// TODO Auto-generated method stub
		return sql.selectList(NAMESPACE+".billList", list);
	}

	@Override
	public List<HashMap> billOne(int b_id) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectList(NAMESPACE+".billOne", b_id);
	}

}
