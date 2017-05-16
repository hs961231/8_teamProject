package yjc.wdb.scts.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yjc.wdb.scts.bean.PositionVO;

public interface PositionDAO {
	public void insertPosition(PositionVO vo) throws Exception;
	public PositionVO selectPosition() throws Exception;
	public List<HashMap<String, String>> visit_count() throws Exception;
	public List<HashMap<String, String>> probability() throws Exception;
	public List<HashMap<String, String>> avgStay() throws Exception;
	public int todayCount() throws Exception;
}
