package com.mashirro.springaop_learn_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


/**
 * 注解@EnableAspectJAutoProxy开启@AspectJ支持,注意
 * AOP运行时仍然是纯spring aop，并且不依赖于AspectJ编译器或weaver
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class SpringaopLearnDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringaopLearnDemoApplication.class, args);
	}

}
