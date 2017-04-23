package yjc.wdb.scts.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import yjc.wdb.scts.DAO.UserDAO;
import yjc.wdb.scts.bean.UserVO;

@Service
public class UserServiceImpl implements UserService {
	
	@Inject
	private UserDAO dao;

	@Override
	public int loginUser(UserVO user) throws Exception {
		return dao.loginUser(user);
	}

}
