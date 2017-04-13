package yjc.wdb.scts.service;

import yjc.wdb.scts.bean.PositionVO;

public interface PositionService {
	public void insertPosition(PositionVO vo) throws Exception;
	public PositionVO selectPosition() throws Exception;
}
