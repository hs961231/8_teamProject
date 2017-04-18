package yjc.wdb.scts.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import yjc.wdb.scts.DAO.CustomerDAO;
import yjc.wdb.scts.bean.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Inject
	private CustomerDAO dao;

	@Override
	public int checkCustomer(Customer customer) throws Exception {
		return dao.checkCustomer(customer);
	}

}
