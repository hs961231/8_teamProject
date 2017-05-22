package yjc.wdb.scts.DAO;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class SalesDAOImpl implements SalesDAO{
	
	private static final String NAMESPACE = "yjc.wdb.mapper.SalesMapper";
	
	
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

}
