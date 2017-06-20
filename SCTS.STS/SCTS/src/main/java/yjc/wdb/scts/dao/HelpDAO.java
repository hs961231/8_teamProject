package yjc.wdb.scts.dao;

import java.util.List;

/*import yjc.wdb.scts.bean.Criteria;*/
import yjc.wdb.scts.bean.HelpVO;
import yjc.wdb.scts.bean.PageVO;

public interface HelpDAO {
	public void createHelp(HelpVO vo)throws Exception;
	public void updateHelp(HelpVO vo)throws Exception;
	public void deleteHelp(Integer bno)throws Exception;
	public HelpVO readHelp(Integer bno)throws Exception;
	public List<HelpVO> listPage(int page)throws Exception;
	public List<HelpVO> listCriteria(PageVO cri)throws Exception;
	public List<HelpVO> listSearch(PageVO cri) throws Exception; /* 검색용 리스트 */
	public int countSearch(PageVO cri) throws Exception; // 검색용 totalCount
	public int countPaging(PageVO cri)throws Exception; // totalCount를 반환하기 위해
	
}
