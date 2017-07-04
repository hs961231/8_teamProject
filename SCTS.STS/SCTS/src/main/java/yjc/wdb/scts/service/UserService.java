package yjc.wdb.scts.service;

import yjc.wdb.scts.bean.UserVO;

public interface UserService {
	
	public int loginUser(UserVO user) throws Exception;
	public void insertUser(UserVO user) throws Exception;
	public int checkUser(String id) throws Exception;
	public int point(String user_id) throws Exception;
	public String knowUserBranch(String user_id) throws Exception;
}
