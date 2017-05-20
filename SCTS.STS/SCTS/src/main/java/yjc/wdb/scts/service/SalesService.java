package yjc.wdb.scts.service;

import java.util.HashMap;
import java.util.List;

public interface SalesService {
	public List<HashMap> yearSales(int year) throws Exception;
	public List<HashMap> daySales() throws Exception;
}
