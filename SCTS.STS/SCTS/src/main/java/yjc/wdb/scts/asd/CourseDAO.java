package yjc.wdb.scts.asd;

import java.util.HashMap;

public interface CourseDAO {
	public void insertCourse(HashMap<String, String> vo) throws Exception;
	public int selectTodayVisitCnt() throws Exception;
}
