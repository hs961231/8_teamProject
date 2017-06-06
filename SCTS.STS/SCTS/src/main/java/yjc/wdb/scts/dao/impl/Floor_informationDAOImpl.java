package yjc.wdb.scts.dao.impl;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import yjc.wdb.scts.bean.Floor_informationVO;
import yjc.wdb.scts.dao.Floor_informationDAO;

@Repository
public class Floor_informationDAOImpl implements Floor_informationDAO {

	private static final String NAMESPACE = "yjc.wdb.mapper.Floor_informationMapper";
	
	@Inject
	private SqlSession sql;
	
	@Override
	public void insertDrawing(String drw_flpth) throws Exception {
		// TODO Auto-generated method stub
		sql.insert(NAMESPACE + ".insertDrawing", drw_flpth);
	}

	@Override
	public void insertFloor_information(Floor_informationVO vo) throws Exception {
		// TODO Auto-generated method stub
		sql.insert(NAMESPACE + ".insertFloor_information", vo);
	}

	@Override
	public List<HashMap<String, String>> selectDrawingList(int bhf_code) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectList(NAMESPACE + ".selectDrawingList", bhf_code);
	}

}
