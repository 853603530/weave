package com.gl.weave.dataSource;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
/**
 * 自定义注解 + AOP的方式实现数据源动态切换。
 * Created by pure on 2018-05-06.
 */
@Aspect
@Order(-1)// 保证该AOP在@Transactional之前执行
@Component
public class DynamicDataSourceAspect {
	private static final Logger log = LoggerFactory.getLogger(DynamicDataSourceAspect.class);
	/*
     * 定义一个切入点
     */
    @Pointcut("execution(* com.gl.weave.dao..*.*(..))")
    public void excudeService(){}
 
    @Before("execution(* com.gl.weave.dao..*.*(..))")
    public void doBefore(JoinPoint joinPoint) {
    	ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

       /* DataSourceKey dataSourceKey = DataSourceContextHolder.get();
        if (dataSourceKey == DataSourceKey.DB_SLAVE2) {
        	log.info(String.format("设置数据源为  %s", DataSourceKey.DB_SLAVE2));
            DataSourceContextHolder.set(DataSourceKey.DB_SLAVE2);
        } else {
        	log.info(String.format("使用默认数据源  %s", DataSourceKey.DB_SLAVE1));
            DataSourceContextHolder.set(DataSourceKey.DB_SLAVE2);
        }*/
    }

    
    @After("excudeService()")
    public void afterSwitchDS(JoinPoint point){
        //DataSourceContextHolder.clear();
    }
    
   /* @Before("excudeService()")
    public void doBeforeWithSlave(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        //获取当前切点方法对象
        Method method = methodSignature.getMethod();
        if (method.getDeclaringClass().isInterface()) {//判断是否为借口方法
            try {
                //获取实际类型的方法对象
                method = joinPoint.getTarget().getClass()
                        .getDeclaredMethod(joinPoint.getSignature().getName(), method.getParameterTypes());
            } catch (NoSuchMethodException e) {
                log.error("方法不存在！", e);
            }
        }
        if (null == method.getAnnotation(DS.class)) {
        	DataSourceContextHolder.setSlave();
        }
    }*/

}
