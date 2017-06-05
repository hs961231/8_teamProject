package yjc.wdb.scts.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import yjc.wdb.scts.bean.TileVO;
import yjc.wdb.scts.dao.TileDAO;
import yjc.wdb.scts.service.TileService;

@Service
public class TileServiceImpl implements TileService {

	@Inject
	private TileDAO dao;
	
	@Override
	public List<HashMap<String, String>> selectTileList() throws Exception {
		// TODO Auto-generated method stub
		return dao.selectTileList();
	}

	@Override
	public void insertTile(TileVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.insertTile(vo);
	}

}
