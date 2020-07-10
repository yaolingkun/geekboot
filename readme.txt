接口规范：
	

功能：
多数据源
	手工配置数据源
	注意：
		MySQL：	的日期类型建议使用DataTime类型
		Oracle: date精确到秒,timestamp精确到毫秒
	

日志记录
	使用logback-spring.xml自定义日志输出
	
自定义日志
	/springbootplus/src/main/java/com/springbootplus/core/logger/Logger.java
	
区分渠道:
	http--.do,H5--hl,client--cl,微信--wx



过滤器
	使用FilterRegistrationBean注册过滤器；
	注意：WebFilter注解配合Order注解，排序无效；@Order注解只能用于定义Bean的加载顺序，却真正无法控制Filter排序。

swagger配置
	ActionTemplate接口补充swagger使用示例
	

json格式数据
	使用@RestController或者@ResponseBody注解返回的是json格式对象，而不是一个json字符串。
	根据实际情况使用jackson

获取外部自定义配置文件
	非application开头的Properties配置文件

跨域配置	
	请求头无数据的原因：请求头有不安全字段，浏览器自发options类型请求预校验
	
定时器监控任务
---------------------------------------------待优化-----------------------------------------
增加jwt

全局异常处理

限制请求绑定方法（execute）、参数(Context)、返回值类型(Map)
	优点：一个控制层对应一个请求，便于维护
	           统一返回格式

全链路跟踪
---------------------------------------------规则-------------------------------------------

配置文件使用规则：
	禁止私自增加配置文件
	/config/application.yml       		公用变量配置
	/config/application-dev.yml   		开发环境变量配置
	/config/application-test.yml  		测试环境变量配置
	/config/application-pro.yml  		准生产环境变量配置
	/config/mappers/mappers0			具体数据源下的mapper路径，多个路径用英文逗号隔开

		     			
---------------------------------------------补充-----------------------------------------
小知识：
1、springboot 有读取外部配置文件的方法，如下优先级：
	第一种是在jar包的同一目录下建一个config文件夹，然后把配置文件放到这个文件夹下。（最后被加载）
	第二种是直接把配置文件放到jar包的同级目录。
	第三种在classpath下建一个config文件夹，然后把配置文件放进去。
	第四种是在classpath下直接放配置文件。（首先被加载）
	注意：这里只针对application的配置文件
	 在不指定要被加载文件时，默认的加载顺序：由里向外加载，所以最外层的最后被加载，会覆盖里层的属性
2、注：如果在docker里面运行在jar同目录下放config目录也是读取不到的，Dockerfile里需要加上一句：ADD config/ /config/   
	然后读出来的路径是：//config/application.properties
	
3、Mysql 日期
	1.DATE、DATETIME和TIMESTAMP 表达的时间范围
		Type		Range													Remark
		DATE		'1000-01-01' to '9999-12-31'							只有日期部分，没有时间部分
		DATETIME	'1000-01-01 00:00:00' to '9999-12-31 23:59:59'			时间格式为 YYYY-MM-DD hh:mm:ss，默认精确到秒
		TIMESTAMP	'1970-01-01 00:00:01' UTC to '2038-01-19 03:14:07'UTC	默认精确到秒
	2.DATETIME和TIMESTAMP 最大时间精确度
		5.7 之后的版本（其实应该说5.6.5），在默认的秒精确度上，可以带小数，最多带6位小数，即可以精确到 microseconds (6 digits) precision。
		Type	 	Range 															Remark
		DATETIME	'1000-01-01 00:00:00.000000' to '9999-12-31 23:59:59.999999'	'YYYY-MM-DD hh:mm:ss[.fraction]'
		TIMESTAMP	'1970-01-01 00:00:01.000000' to '2038-01-19 03:14:07.999999'	'YYYY-MM-DD hh:mm:ss[.fraction]'
	3.DATETIME和TIMESTAMP 区别
		（1） 时间范围不一样，TIMESTAMP 要小很多 ，且最大范围为2038-01-19 03:14:07.999999，到期也不远了。
		（2）对于TIMESTAMP，它把客户端插入的时间从当前时区转化为UTC（世界标准时间）进行存储。查询时，将其又转化为客户端当前时区进行返回。而对于DATETIME，不做任何改变，基本上是原样输入和输出。

4、非简单请求即是复杂请求
	常见的复杂请求有：
	请求方法为 PUT 或 DELETE
	Content-Type 字段类型为 application/json
	添加额外的http header 比如access_token
	在跨域的情况下，非简单请求会先发起一次空body的OPTIONS请求，称为"预检"请求，用于向服务器请求权限信息，等预检请求被成功响应后，才发起真正的http请求。

5、OncePerRequestFilter
	大家常识上都认为，一次请求本来就只过一次，为什么还要由此特别限定呢，呵呵实际上我们常识和实际的实现并不真的一样，经过一番查阅后，此方式是为了兼容不同的web container，特意而为之（jsr168），也就是说并不是所有的container都像我们期望的只过滤一次，servlet版本不同，表现也不同。

6、HTTP协议状态(sc-status)码的含义	
	100 Continue 初始的请求已经接受，客户应当继续发送请求的其余部分
	101 Switching Protocols 服务器将遵从客户的请求转换到另外一种协议
	200 OK 一切正常，对GET和POST请求的应答文档跟在后面。
	201 Created 服务器已经创建了文档，Location头给出了它的URL。
	202 Accepted 已经接受请求，但处理尚未完成。
	203 Non-Authoritative Information 文档已经正常地返回，但一些应答头可能不正确，因为使用的是文档的拷贝
	204 No Content 没有新文档，浏览器应该继续显示原来的文档。如果用户定期地刷新页面，而Servlet可以确定用户文档足够新，这个状态代码是很有用的
	205 Reset Content 没有新的内容，但浏览器应该重置它所显示的内容。用来强制浏览器清除表单输入内容
	206 Partial Content 客户发送了一个带有Range头的GET请求，服务器完成了它
	300 Multiple Choices 客户请求的文档可以在多个位置找到，这些位置已经在返回的文档内列出。如果服务器要提出优先选择，则应该在Location应答头指明。
	301 Moved Permanently 客户请求的文档在其他地方，新的URL在Location头中给出，浏览器应该自动地访问新的URL。
	302 Found 类似于301，但新的URL应该被视为临时性的替代，而不是永久性的。
	303 See Other 类似于301/302，不同之处在于，如果原来的请求是POST，Location头指定的重定向目标文档应该通过GET提取
	304 Not Modified 客户端有缓冲的文档并发出了一个条件性的请求（一般是提供If-Modified-Since头表示客户只想比指定日期更新的文档）。服务器告诉客户，原来缓冲的文档还可
	以继续使用。
	305 Use Proxy 客户请求的文档应该通过Location头所指明的代理服务器提取
	307 Temporary Redirect 和302（Found）相同。许多浏览器会错误地响应302应答进行重定向，即使原来的请求是POST，即使它实际上只能在POST请求的应答是303时才能重定向。由于这个原因，HTTP 1.1新增了307，以便更加清除地区分几个状态代码：当出现303应答时，浏览器可以跟随重定向的GET和POST请求；如果是307应答，则浏览器只能跟随对GET请求的重定向。
	400 Bad Request 请求出现语法错误。
	401 Unauthorized 客户试图未经授权访问受密码保护的页面。应答中会包含一个WWW-Authenticate头，浏览器据此显示用户名字/密码对话框，然后在填写合适的Authorization头后再次发出请求。
	403 Forbidden 资源不可用。
	404 Not Found 无法找到指定位置的资源
	405 Method Not Allowed 请求方法（GET、POST、HEAD、DELETE、PUT、TRACE等）对指定的资源不适用。
	406 Not Acceptable 指定的资源已经找到，但它的MIME类型和客户在Accpet头中所指定的不兼容
	407 Proxy Authentication Required 类似于401，表示客户必须先经过代理服务器的授权。
	408 Request Timeout 在服务器许可的等待时间内，客户一直没有发出任何请求。客户可以在以后重复同一请求。
	409 Conflict 通常和PUT请求有关。由于请求和资源的当前状态相冲突，因此请求不能成功。
	410 Gone 所请求的文档已经不再可用，而且服务器不知道应该重定向到哪一个地址。它和404的不同在于，返回407 表示文档永久地离开了指定的位置，而404表示由于未知的原因文档不可用。
	411 Length Required 服务器不能处理请求，除非客户发送一个Content-Length头。
	412 Precondition Failed 请求头中指定的一些前提条件失败
	413 Request Entity Too Large 目标文档的大小超过服务器当前愿意处理的大小。如果服务器认为自己能够稍后再处理该请求，则应该提供一个Retry-After头
	414 Request URI Too Long URI太长
	416 Requested Range Not Satisfiable 服务器不能满足客户在请求中指定的Range头
	500 Internal Server Error 服务器遇到了意料不到的情况，不能完成客户的请求
	501 Not Implemented 服务器不支持实现请求所需要的功能。例如，客户发出了一个服务器不支持的PUT请求
	502 Bad Gateway 服务器作为网关或者代理时，为了完成请求访问下一个服务器，但该服务器返回了非法的应答
	503 Service Unavailable 服务器由于维护或者负载过重未能应答。例如，Servlet可能在数据库连接池已满的情况下返回503。服务器返回503时可以提供一个Retry-After头
	504 Gateway Timeout 由作为代理或网关的服务器使用，表示不能及时地从远程服务器获得应答
	505 HTTP Version Not Supported 服务器不支持请求中所指明的HTTP版本
	
---------------------------------------------建议-----------------------------------------
自问自答：
	1. 为什么需要手工为每个数据源配置事务管理器？
		没有使用@Primary注解！
		若是使用@Primary的话，各个数据源交叉使用的时候，就给人一种数据源“地位不等”的感觉
		使用@Transactional注解时很容易让人麻痹大意而出错。
	2. 为什么需要为每个数据源配置SqlSessionFactory？
		每一次访问数据库皆由SqlSession代劳，而SqlSessionFactory正是生产SqlSession的工厂。既然和访问数据库有关，那肯定是要区分DataSource的。
	3. 需要专门配置SqlSessionTemplate吗？
		不必。因为mapper接口的实现类MapperProxy在初始化的时候已经初始化了SqlSessionTemplate，参见如下所示MapperProxy的构造函数。

	
