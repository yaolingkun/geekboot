package com.springbootplus.core.model;

public class BaseConstants {
	/**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";
    
    /**
     * 通用失败标识
     */
    public static final String FAIL = "1";

    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "登录成功";

    /**
     * 注销
     */
    public static final String LOGOUT = "退出成功";
    
    /**
     * 分页默认第一页
     */
    public static int PAGE_CURR= 1;

    /**
     * 每页显示记录数
     */
    public static int PAGE_SIZE = 20;
    
    /**
     * 常用图片格式
     */
    public static final String FILE_REG_IMG_FORMAT = "^.+(.JPEG|.jpeg|.JPG|.jpg|.PNG|.png|.GIF|.gif)$";
    
    /**
     * 邮箱格式
     */
    public static final String REG_EMAIL_FORMAT = "^[a-z_0-9.-]{1,64}@([a-z0-9-]{1,200}.){1,5}[a-z]{1,6}$";
    
    
    /**
     * 系统管理相关
     */
    public static final String MODULES_SYSTEM = "/system";
    
    /**
     * 登录认证
     */
    public static final String MODULES_AUTH = "/auth";
    
    /**
     * 日志接口
     */
    public static final String MODULES_LOG = "/log";
}
