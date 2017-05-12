package yjc.wdb.scts.DAO;


import java.util.HashMap;
import java.util.List;

public interface PurchaseInfoDAO {
	
	public List<HashMap> billList(String user_id, int day) throws Exception;
	public List<HashMap> billOne(int b_id) throws Exception;

}
