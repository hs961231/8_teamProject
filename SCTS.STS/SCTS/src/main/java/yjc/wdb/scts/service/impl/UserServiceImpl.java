package yjc.wdb.scts.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import yjc.wdb.scts.bean.UserVO;
import yjc.wdb.scts.dao.UserDAO;
import yjc.wdb.scts.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Inject
	private UserDAO dao;

	@Override
	public int loginUser(UserVO user) throws Exception {
		return dao.loginUser(user);
	}

	@Override
	public void insertUser(UserVO user) throws Exception {
		dao.insertUser(user);
	}

	@Override
	public int checkUser(String id) throws Exception {
		// TODO Auto-generated method stub
		return dao.checkUser(id);
	}

	@Override
	public int point(String user_id) throws Exception {
		// TODO Auto-generated method stub
		return dao.point(user_id);
	}

}