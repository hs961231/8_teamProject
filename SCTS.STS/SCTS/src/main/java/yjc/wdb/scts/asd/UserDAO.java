package yjc.wdb.scts.asd;

import yjc.wdb.scts.bean.UserVO;

public interface UserDAO {
	public int loginUser(UserVO user) throws Exception;
	public void insertUser(UserVO user) throws Exception;
	public int checkUser(String id) throws Exception;
	public int point(String user_id) throws Exception;
}
