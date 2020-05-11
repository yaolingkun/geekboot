package com.springbootplus.core;

import com.springbootplus.core.model.Context;
import com.springbootplus.core.model.Result;
/**
* @Api：放在 请求的类上，与 @Controller 并列，说明类的作用，如用户模块，订单类等。
* tags="说明该类的作用"
* value="该参数没什么意义，所以不需要配置"
* 
* 示例：
* @Api(tags="订单模块")
* @Controller
* public class OrderController {
* 
* }
*/
public interface ActionTemplate {
	/**
	 * @ApiImplicitParams：用在请求的方法上，包含一组参数说明
	 * @ApiImplicitParam：对单个参数的说明	    
	 * 	    name：参数名
	 * 	    value：参数的说明、描述
	 * 	    required：参数是否必须必填
	 * 	    paramType：参数放在哪个地方
	 * 	        · query --> 请求参数的获取：@RequestParam
	 * 	        · header --> 请求参数的获取：@RequestHeader	      
	 * 	        · path（用于restful接口）--> 请求参数的获取：@PathVariable
	 * 	        · body（请求体）-->  @RequestBody User user
	 * 	        · form（普通表单提交）	   
	 * 	    dataType：参数类型，默认String，其它值dataType="Integer"	   
	 * 	    defaultValue：参数的默认值
	 * 
	 * 实例
	 * @ApiOperation(value="用户登录",notes="随边说点啥")
	 * 	@ApiImplicitParams({
	 * 		@ApiImplicitParam(name="mobile",value="手机号",required=true,paramType="form"),
	 * 		@ApiImplicitParam(name="password",value="密码",required=true,paramType="form"),
	 * 		@ApiImplicitParam(name="age",value="年龄",required=true,paramType="form",dataType="Integer")
	 * 	})
	 * 	@PostMapping("/login")
	 * 	public JsonResult login(@RequestParam String mobile, @RequestParam String password,
	 * 	@RequestParam Integer age){
	 * 		//...
	 * 	    return JsonResult.ok(map);
	 * 	}
	 */
	public Result execute(Context context);
}
