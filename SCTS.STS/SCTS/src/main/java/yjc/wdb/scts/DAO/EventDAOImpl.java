package yjc.wdb.scts.DAO;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import yjc.wdb.scts.bean.EventVO;

@Repository
public class EventDAOImpl implements EventDAO {
	
	private static final String NAMESPACE ="yjc.wdb.mapper.EventMapper";
	
	@Inject
	private SqlSession sql;

	@Override
	public List<EventVO> eventList() throws Exception {
		
		return sql.selectList(NAMESPACE+".eventList");
	}

	@Override
	public EventVO eventOne(String e_id) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne(NAMESPACE+".eventOne", e_id);
	}

}
