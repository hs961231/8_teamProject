package yjc.wdb.scts.DAO;

import java.util.HashMap;
import java.util.List;

public interface SalesDAO {
	
	
	public List<HashMap> yearSales(int year) throws Exception; 
	public List<HashMap> daySales() throws Exception;
}
