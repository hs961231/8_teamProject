package yjc.wdb.scts.DAO;

import yjc.wdb.scts.bean.UserVO;

public interface UserDAO {

	public int loginUser(UserVO user) throws Exception;
	public void registerUser(UserVO user) throws Exception;
	public int checkUser(String id) throws Exception;
	
}
