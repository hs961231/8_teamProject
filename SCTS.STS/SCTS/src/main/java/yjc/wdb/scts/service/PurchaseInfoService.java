package yjc.wdb.scts.service;


import java.util.HashMap;
import java.util.List;


public interface PurchaseInfoService {
	public List<HashMap> billList(String user_id, int day) throws Exception;
	public List<HashMap> billOne(int b_id) throws Exception;
}
