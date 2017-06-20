package yjc.wdb.scts.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import yjc.wdb.scts.bean.PageVO;
import yjc.wdb.scts.bean.HelpVO;
import yjc.wdb.scts.dao.HelpDAO;
import yjc.wdb.scts.service.HelpService;

@Service
public class HelpServiceImpl implements HelpService {
	
	@Inject
	private HelpDAO dao;
	
	@Override
	public void createHelp(HelpVO vo) throws Exception {
		dao.createHelp(vo);
	}

	@Override
	public void updateHelp(HelpVO vo) throws Exception {
		dao.updateHelp(vo);
	}

	@Override
	public void deleteHelp(Integer bno) throws Exception {
		dao.deleteHelp(bno);
	}

	@Override
	public HelpVO readHelp(Integer bno) throws Exception {
		return dao.readHelp(bno);
	}

	@Override
	public List<HelpVO> listPage(int page) throws Exception {
		return dao.listPage(page);
	}

	@Override
	public List<HelpVO> listCriteria(PageVO cri) throws Exception {
		return dao.listCriteria(cri);
	}

	@Override
	public int countPaging(PageVO cri) throws Exception {
		return dao.countPaging(cri);
	}

	@Override
	public List<HelpVO> listSearch(PageVO cri) throws Exception {
		// TODO Auto-generated method stub
		return dao.listSearch(cri);
	}

	@Override
	public int countSearch(PageVO cri) throws Exception {
		// TODO Auto-generated method stub
		return dao.countSearch(cri);
	}


}
