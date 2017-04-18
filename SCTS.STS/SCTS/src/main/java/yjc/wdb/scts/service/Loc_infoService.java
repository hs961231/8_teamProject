package yjc.wdb.scts.service;

import java.util.List;

import yjc.wdb.scts.bean.Loc_info;

public interface Loc_infoService {
	public void insertLoc_info(Loc_info loc_info) throws Exception;
	public List<Loc_info> Loc_info_List() throws Exception;
}
