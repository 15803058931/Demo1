package com.example.demo.aspect;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 通过execution定义包路径，来切入方法
 * 在切面中 ，可以获得切入的方法的参数和返回值 
 * @author Administrator
 *
 */
@Aspect
@Component
public class LogAspect {
	@Pointcut("execution(public * com.example.demo.controller.*.*(..))")
	public void webLog() {
		
	}
	
	@Before("webLog()")  
    public void deBefore(JoinPoint joinPoint) throws Throwable {  
        // 接收到请求，记录请求内容  
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();  
        HttpServletRequest request = attributes.getRequest();  
        
        // 记录下请求内容  
        System.out.println("方法deBefore start " );  
         
        System.out.println("URL : " + request.getRequestURL().toString());  
        System.out.println("HTTP_METHOD : " + request.getMethod());  
        System.out.println("IP : " + request.getRemoteAddr());  
        System.out.println("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());  
        System.out.println("ARGS : " + Arrays.toString(joinPoint.getArgs()));  
  
    }  
  
    @AfterReturning(returning = "ret", pointcut = "webLog()")  
    public void doAfterReturning(Object ret) throws Throwable {  
        // 处理完请求，返回内容  
        System.out.println("方法的返回值 : " + ret);  
    }  
  
    //后置异常通知  
    @AfterThrowing("webLog()")  
    public void throwss(JoinPoint jp){  
        System.out.println("方法异常时执行.....");  
    }  
  
    //后置最终通知,final增强，不管是抛出异常或者正常退出都会执行  
    @After("webLog()")  
    public void after(JoinPoint jp){ 
    	jp.getSignature().getName();
    	Object target =   jp.getTarget();
    	Object[]  as=jp.getArgs();
    	System.out.println(as);
	 
        System.out.println("方法最后执行....."+jp.getSignature().getName());  
    }  
  
    //环绕通知,环绕增强，相当于MethodInterceptor  
    /**
     * 返回被切入方法自身得到的值，如果直接返回null，则被切入方法返回的值也是null
     * @param pjp
     * @return
     */
    @Around("webLog()")  
    public Object arround(ProceedingJoinPoint pjp) {  
        System.out.println("方法环绕start.....");  
        try {  
            Object o =  pjp.proceed();  //使用proceed()方法来执行目标方法
            System.out.println("方法环绕proceed，结果是 :" + o);  
            return o;  
        } catch (Throwable e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
}
