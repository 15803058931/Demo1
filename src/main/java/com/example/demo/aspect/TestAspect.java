package com.example.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 通过自定义标注的方式切入
 * @author Administrator
 *
 */
@Aspect
@Component
public class TestAspect {

	@Pointcut("@annotation(com.example.demo.aspect.MyPoint)")
	public void test() {
		System.out.println("new 提交");
	}
	@Before("test()")  
	public void before(JoinPoint joinPoint) {
		System.out.println("TestAspect 的 before");
	}
}
