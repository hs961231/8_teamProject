package yjc.wdb.scts.service;

import java.util.HashMap;

public interface CourseService {
	public void insertCourse(HashMap<String, String> vo) throws Exception;
	public int selectTodayVisitCnt() throws Exception;
}
