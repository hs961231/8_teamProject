package yjc.wdb.scts.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import yjc.wdb.scts.DAO.Loc_InfoDAO;
import yjc.wdb.scts.bean.Loc_info;

@Service
public class Loc_infoServiceImpl implements Loc_infoService {

	@Inject
	private Loc_InfoDAO dao;
	
	@Override
	public void insertLoc_info(Loc_info loc_info) throws Exception {
		dao.insertLog_info(loc_info);
	}

	@Override
	public List<Loc_info> Loc_info_List() throws Exception {

		return dao.Loc_info_List();
	}

}
