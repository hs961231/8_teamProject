package yjc.wdb.scts.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import yjc.wdb.scts.DAO.PurchaseInfoDAO;

@Service
public class PurchaseInfoServeiceImpl implements PurchaseInfoService {

	@Inject
	private PurchaseInfoDAO dao;
	
	@Override
	public List<HashMap> billList(String user_id, int day) throws Exception {
		
		
		return dao.billList(user_id, day);
	}

	@Override
	public List<HashMap> billOne(int b_id) throws Exception {
		
		return dao.billOne(b_id);
	}

}
