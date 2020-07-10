package com.springbootplus.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springbootplus.core.BaseController;
import com.springbootplus.core.config.dbconfig.Db0Config;
import com.springbootplus.core.logger.Logger;
import com.springbootplus.core.logger.LoggerFactory;
import com.springbootplus.core.model.BaseConstants;
import com.springbootplus.core.model.Context;
import com.springbootplus.core.model.Result;
import com.springbootplus.core.util.PropertiesUtils;
import com.springbootplus.mappers.mappers0.Db0Mapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(value = BaseConstants.MODULES_AUTH, tags = "系统管理:认证管理",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
public class DemoController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger();
//	@Autowired
//    private Db0Mapper db0Mapper;
	
	
	/**
     * 从db0获取学生姓名
     * @param id
     */
	@PostMapping("/getStudent0.do")
	@ApiOperation(value = "获取学生姓名")
    public Result execute(@RequestBody Context context,HttpServletRequest request) {
//		logger.info("context = {}",context.getStringData("id"));
		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("name", db0Mapper.getStudentNameFromDb0("1"));
		
			logger.info(PropertiesUtils.getExtPropertiesValue("id"),request);
			logger.info(PropertiesUtils.getExtPropertiesValue("name"),request);
			logger.info(PropertiesUtils.getExtPropertiesValue("age"),request);
			logger.info(PropertiesUtils.getExtPropertiesValue("sex"),request);
		
		
        return (new Result(200,map));
    }
    /**
     * 更新db0的学生姓名
     * @param id
     * @param name
     * @return
     * @throws Exception
     */
	@PostMapping("/updateStudent0.do")
    @Transactional(value = Db0Config.TX_MANAGER, rollbackFor = Exception.class)
    public String updateStudent0Name(String id, String name) throws Exception {
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("id", id);
        paraMap.put("name", name);
//        db0Mapper.updateStudents0Name(paraMap);
        return "db0更新成功！";
    }

 

}
