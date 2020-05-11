package com.springbootplus.mappers.mappers0;

import java.util.Map;

public interface Db0Mapper {
	 /**
     * 根据id获取学生姓名,
     * @param id
     * @return
     */
    String getStudentNameFromDb0(String id);
 
    /**
     * 根据id更新学生的姓名
     * @param parm
     * @return
     */
    int updateStudents0Name(Map<String, Object> parm);
}
