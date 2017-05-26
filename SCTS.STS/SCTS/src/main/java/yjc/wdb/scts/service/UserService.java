package yjc.wdb.scts.service;

import yjc.wdb.scts.bean.UserVO;

public interface UserService {
	
	public int loginUser(UserVO user) throws Exception;
	public void registerUser(UserVO user) throws Exception;
	public int checkUser(String id) throws Exception;
	public int point(String user_id) throws Exception;
}
