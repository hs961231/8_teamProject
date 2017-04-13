package yjc.wdb.scts.DAO;

import yjc.wdb.scts.bean.PositionVO;

public interface PositionDAO {
	public void insertPosition(PositionVO vo) throws Exception;
	public PositionVO selectPosition() throws Exception;
 }
