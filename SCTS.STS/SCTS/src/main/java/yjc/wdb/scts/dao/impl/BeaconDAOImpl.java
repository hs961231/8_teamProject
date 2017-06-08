package yjc.wdb.scts.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import yjc.wdb.scts.bean.BeaconVO;
import yjc.wdb.scts.dao.BeaconDAO;

@Repository
public class BeaconDAOImpl implements BeaconDAO {
	
	private static final String NAMESPACE = "yjc.wdb.mapper.BeaconMapper";
	
	@Inject
	private SqlSession sql;

	@Override
	public List<BeaconVO> selectBeaconList(int bhf_code) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectList(NAMESPACE + ".selectBeaconList", bhf_code);
	}

}
