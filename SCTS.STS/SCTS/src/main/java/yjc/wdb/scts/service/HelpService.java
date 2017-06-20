package yjc.wdb.scts.service;

import java.util.List;

import yjc.wdb.scts.bean.PageVO;
import yjc.wdb.scts.bean.HelpVO;

public interface HelpService {
	public void createHelp(HelpVO vo)throws Exception;
	public void updateHelp(HelpVO vo)throws Exception;
	public void deleteHelp(Integer bno)throws Exception;
	public HelpVO readHelp(Integer bno)throws Exception;
	public List<HelpVO> listPage(int page)throws Exception;
	public List<HelpVO> listCriteria(PageVO cri)throws Exception;
	public List<HelpVO> listSearch(PageVO cri) throws Exception;
	public int countSearch(PageVO cri) throws Exception;
	public int countPaging(PageVO cri)throws Exception; 
}
