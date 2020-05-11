package com.springbootplus.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootplus.core.BaseController;
import com.springbootplus.core.config.dbconfig.Db0Config;
import com.springbootplus.core.model.Context;
import com.springbootplus.core.model.Result;
import com.springbootplus.core.util.PropertiesUtils;
import com.springbootplus.mappers.mappers0.Db0Mapper;

@RestController
public class DemoController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(DemoController.class);
	@Autowired
    private Db0Mapper db0Mapper;
	
	
	/**
     * 从db0获取学生姓名
     * @param id
     */
	@RequestMapping("/getStudent0.do")
    public Result execute(Context context) {
		logger.info("context = {}",context.getStringDate("id"));
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name", db0Mapper.getStudentNameFromDb0("1"));
		try {
			logger.info(PropertiesUtils.getExtPropertiesValue("id"));
			logger.info(PropertiesUtils.getExtPropertiesValue("name"));
			logger.info(PropertiesUtils.getExtPropertiesValue("age"));
			logger.info(PropertiesUtils.getExtPropertiesValue("sex"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return (new Result(200,map));
    }
    /**
     * 更新db0的学生姓名
     * @param id
     * @param name
     * @return
     * @throws Exception
     */
    @RequestMapping("/updateStudent0.do")
    @Transactional(value = Db0Config.TX_MANAGER, rollbackFor = Exception.class)
    public String updateStudent0Name(String id, String name) throws Exception {
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("id", id);
        paraMap.put("name", name);
        db0Mapper.updateStudents0Name(paraMap);
        return "db0更新成功！";
    }

 

}
