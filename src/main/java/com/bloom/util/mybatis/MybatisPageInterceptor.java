package com.bloom.util.mybatis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

/**
 * @author xuliangliang
 * 用于mybatis分页,该插件会自动查找select语句对应的mapper签名中是否含有page参数，如果有，则进行分页
 *
 */
@Intercepts({@Signature(type =StatementHandler.class, method = "prepare", args ={Connection.class,Integer.class}),@Signature(type =StatementHandler.class, method = "query", args ={Statement.class,ResultHandler.class})}) 
public class MybatisPageInterceptor implements Interceptor{
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		// 
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();  
		MetaObject metaStatementHandler=getNoProxyObject(statementHandler);
		 BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");  
	     Page<?> page=null;
	     if((page=findPageObject(boundSql.getParameterObject()))!=null){//分页参数存在
	    	 if(invocation.getMethod().getName().equals("prepare")){//prepare阶段设置查询总数的sql和分页的sql
	    	      String sql = boundSql.getSql();  
	              String pageSql = buildPageSql(sql, page);  
	              metaStatementHandler.setValue("delegate.boundSql.sql", pageSql);  
	              // 采用物理分页后，就不需要mybatis的内存分页了，所以重置下面的两个参数  
	              metaStatementHandler.setValue("delegate.rowBounds.offset",   
	              RowBounds.NO_ROW_OFFSET);  
	              metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);  
	              // 重设分页参数里的总页数等  
	              if(page.needTotalCount){//需要获取总数
	             	 page.setTotalCountSql("select count(*) from ("+sql.replaceAll("(?i)order +by[^)]+$", "").replace(";", "")+") t");//构造count的sql
	 	    	 }
	    	 }else if(invocation.getMethod().getName().equals("query") ){
	    		 Statement statement = (Statement) invocation.getArgs()[0]; 
	    		 MappedStatement mappedStatement = (MappedStatement)   
	    			     metaStatementHandler.getValue("delegate.mappedStatement"); 
				if (page.needTotalCount) {// 需要获取总数
					setPageParameter(statement.getConnection(), mappedStatement, page, boundSql);
					if (page.getTotalCount() > 0) {// 如果数据为空，则直接返回一个空list
						Object obj=invocation.proceed();
						if(obj instanceof List){
							page.setResults((List)obj);
						}
						return (List)obj;
					} else {
						page.setResults(Collections.EMPTY_LIST);
						return Collections.EMPTY_LIST;
					}
				}
	    	 }
	     }
	     // 将执行权交给下一个拦截器  
	     return invocation.proceed();  
	}


	/**
	 * 在进行代理的时候就判断需不需要分页，这样避免无用代理，但是会造成两次反射调用（可忽略不计）
	 */
	@Override
	public Object plugin(Object target) {
		if(target instanceof StatementHandler){
			StatementHandler statementHandler = (StatementHandler) target;  
			MetaObject metaStatementHandler=getNoProxyObject(statementHandler);
			 BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");   
			if(boundSql.getSql().toLowerCase().contains("select")&&findPageObject(boundSql.getParameterObject())!=null){//分页参数存在
				return Plugin.wrap(target, this);  
			}
		}
		return target;
	}

	
	@Override
	public void setProperties(Properties properties) {
		// 
		
	}
	/**
	 * 获取为代理过的StatementHandler
	 * @param statementHandler
	 * @return
	 */
	protected MetaObject getNoProxyObject(StatementHandler statementHandler){
		MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);  
	     // 分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过下面的两次循环  
	     // 可以分离出最原始的的目标类)  
	     while (metaStatementHandler.hasGetter("h")) {  
	         Object object = metaStatementHandler.getValue("h");  
	         metaStatementHandler = SystemMetaObject.forObject(object);  
	     }  
	     // 分离最后一个代理对象的目标类  
	     while (metaStatementHandler.hasGetter("target")) {  
	         Object object = metaStatementHandler.getValue("target");  
	         metaStatementHandler = SystemMetaObject.forObject(object);  
	     } 
	     return metaStatementHandler;
	}
	/**
	 * 查找参数中是否存在分页参数
	 * @param parameterObj
	 * @return
	 */
	protected Page<?> findPageObject(Object parameterObj) {
		if(parameterObj==null){
			return null;
		}
		if (parameterObj instanceof Page<?>) {
			return (Page<?>) parameterObj;
		} else if (parameterObj instanceof Map) {
			for (Object val : ((Map<?, ?>) parameterObj).values()) {
				if (val instanceof Page<?>) {
					return (Page<?>) val;
				}
			}
		}
		return null;
	}
	
	/** 
	 * 从数据库里查询总的记录数并计算总页数，回写进分页参数<code>PageParameter</code>,这样调用  
	 * 者就可用通过 分页参数<code>PageParameter</code>获得相关信息。 
	 *  
	 * @param sql 
	 * @param connection 
	 * @param mappedStatement 
	 * @param boundSql 
	 * @param page 
	 */  
	private void setPageParameter(Connection connection, MappedStatement mappedStatement, Page<?> page,BoundSql boundSql) {  
	    // 记录总记录数  
	    ResultSet rs = null;  
	    try(PreparedStatement countStmt = connection.prepareStatement(page.getTotalCountSql())) {  
	        setParameters(countStmt, mappedStatement, boundSql, boundSql.getParameterObject());  
	        rs = countStmt.executeQuery();  
	        int totalCount = 0;  
	        if (rs.next()) {  
	            totalCount = rs.getInt(1);  
	        }  
	        page.setTotalCount(totalCount);
	    } catch (SQLException e) {  
	    	e.printStackTrace();
	    } 
	}  

	public String buildPageSql(String sql, Page<?> page) {  
	    StringBuilder pageSql = new StringBuilder();  
	    String beginrow = String.valueOf((page.getPageNo() - 1) * page.getPageSize());  
	    pageSql.append(sql.replace(";", ""));  
	    pageSql.append(" limit " + beginrow + "," + page.getPageSize());  
	    return pageSql.toString();  
	}
	
	/** 
	 * 对SQL参数(?)设值 
	 *  
	 * @param ps 
	 * @param mappedStatement 
	 * @param boundSql 
	 * @param parameterObject 
	 * @throws SQLException 
	 */  
	private void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql,  
	        Object parameterObject) throws SQLException {  
	    ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);  
	    parameterHandler.setParameters(ps);  
	}
}
