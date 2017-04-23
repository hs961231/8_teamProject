package yjc.wdb.scts.DAO;

import yjc.wdb.scts.bean.UserVO;

public interface UserDAO {

	public int loginUser(UserVO user) throws Exception;
	
}
