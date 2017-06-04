package yjc.wdb.scts.dao;

import java.util.HashMap;
import java.util.List;

import yjc.wdb.scts.bean.BillVO;

public interface BillDAO {
	
	public List<BillVO> billList(String user_id, int day) throws Exception;
	public List<HashMap> billOne(int bill_code) throws Exception;
	public List<HashMap> settleInfo(String user_id) throws Exception;
	public List<HashMap> recommandProduct(String user_id) throws Exception;

}
