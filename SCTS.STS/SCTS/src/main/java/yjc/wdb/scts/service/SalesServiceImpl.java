package yjc.wdb.scts.service;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import yjc.wdb.scts.DAO.SalesDAO;


@Service
public class SalesServiceImpl implements SalesService{
	
	@Inject
	private SalesDAO dao;

	@Override
	public List<HashMap> yearSales(int year) throws Exception {
		// TODO Auto-generated method stub
		return dao.yearSales(year);
	}

	@Override
	public List<HashMap> daySales() throws Exception {
		// TODO Auto-generated method stub
		return dao.daySales();
	}

}
