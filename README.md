# bloom 业余项目笔记

1、ResponseBody注解没有生效的问题。
      原因:没有开启mvc注解驱动。
      
	<!-- 开启注解驱动 -->
	<mvc:annotation-driven/>
	
2、DispatcherServlet拦截了静态资源的访问。

      通过添加 <mvc:default-servlet-handler />解决。
      
3、mysql8.0+连接问题。

     驱动：com.mysql.cj.jdbc.Driver
     还需注意SSL、和时区的问题
	URL:jdbc:mysql://localhost:3306/bloom?useUnicode=true&characterEncoding=utf8&rewriteBatchedStatement		s=true&zeroDateTimeBehavior=CONVERT_TO_NULL&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8&autoReconnect=true&failOverReadOnly=false&useSSL=false

4、mybatis数据模型变动重新用generator生成mapper文件不方便问题的解决。

	  示例：
	  生成的mapper文件为： GardenerMapper 、GardenerMapper.xml
	  自己创建扩展类：             GardenerExtDao extends GardenerMapper 、 GardenerExtDao.xml
	  自己后续手写的方法以及sql都放在扩展文件中。项目业务依赖于GardnerExtDao。
	  这样、当数据库模型发生变动需要调整时，通过重新生成GardenerMapper以及xml文件来解决。
 
5、项目角色权限管理。（由注解和前置增强处理）
  （1）@RoleCheck。
  
    @Target(ElementType.METHOD)
	@Retention(RetentionPolicy.RUNTIME)
	public @interface RoleCheck {
		HighGradeRole[] value() default {} ;
	}
（2）@RoleCheckAspect。
	
	@Aspect
	@Component
	public class RoleCheckAspect {
		@Resource
		private HttpServletRequest request;
	
	@Before(value = "@annotation(com.bloom.annotation.RoleCheck)")
	public void check(JoinPoint jp) throws NoSuchMethodException, SecurityException {
		Signature signature = jp.getSignature();
		MethodSignature methodSignature = (MethodSignature)signature;    
		Method targetMethod = methodSignature.getMethod();
			Method realMethod = jp.getTarget().getClass().getDeclaredMethod(signature.getName(), 	targetMethod.getParameterTypes());
			RoleCheck rc = realMethod.getAnnotation(RoleCheck.class);
			Arrays.stream(rc.value())
				.filter(role -> role.value() == LoginCheckUtil.loginRoleId(request))
				.findFirst()
				.orElseThrow(() -> new FlowBreakException("权限不足！"));
		}
	
	}
（3）需要开启Aspect自动代理

	<aop:aspectj-autoproxy/>
	
（4）使用示例

	@RoleCheck(HighGradeRole.Administrator)
	@Transactional
	public Role createRole(Role role) {
		//只有管理员可以执行此操作；
		return null;
	}
	