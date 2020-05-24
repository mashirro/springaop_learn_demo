package com.mashirro.springaop_learn_demo.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;


/**
 * 启用@AspectJ支持后，Spring会自动检测应用程序上下文中定义的具有@Aspect注释的类的任何bean，
 * 并将其用于配置Spring AOP。请注意，@Aspect注释不足以在类路径中进行自动检测。为此，您需要添加一个单独的@Component注释。
 */
@Component
@Aspect
public class MyAspect {

    //声明一个切入点
    @Pointcut("execution(* com.mashirro.springaop_learn_demo.service.UserService.*(..))")  //切入点表达式
    private void pt1() {
    }   //切入点签名


    /**
     * 前置通知
     * 任何advice方法都可以将org.aspectj.lang.JoinPoint类型的参数声明为它的第一个参数
     */
    @Before("pt1()")
    public void beforeAdvice(JoinPoint joinPoint) {
        //getTarget()：返回目标对象(被代理对象)
        Object target = joinPoint.getTarget();
        System.out.println("target:" + target + "---targetClassName:" + target.getClass().getName());
        //getThis()：返回代理对象
        Object aThis = joinPoint.getThis();
        System.out.println("this:" + aThis + "---thisClassName:" + aThis.getClass().getName());

        //target和aThis不是同一个对象,上面内存地址之所以相同,是因为代理对象调用了目标对象的toString方法
        System.out.println(target.equals(aThis));

        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("methodName:" + methodName + "----args:" + Arrays.asList(args));
        System.out.println("beforeAdvice...");
    }

    /**
     * 返回通知
     * 有时，您需要在advice body中访问返回的实际值。您可以使用@AfterReturning的形式来绑定返回值以获取该访问。
     * returning 属性中使用的名称必须与通知方法中的参数名称相对应。当方法执行返回时，返回值作为相应的参数值传递给advice方法。
     * returning子句还将匹配限制为仅返回指定类型的值（在本例中为与任何返回值匹配的Object）的方法执行。
     */
    @AfterReturning(pointcut = "pt1()", returning = "retVal")
    public void afterReturningAdvice(JoinPoint joinPoint, Object retVal) {
        //String methodName = joinPoint.getSignature().getName();
        //Object[] args = joinPoint.getArgs();
        //System.out.println("methodName:" + methodName + "----args:" + Arrays.asList(args));
        System.out.println(retVal);
        System.out.println("afterReturningAdvice...");
    }

    /**
     * 异常通知
     */
    @AfterThrowing(pointcut = "pt1()", throwing = "ex")
    public void afterThrowingAdvice(Exception ex) {
        System.out.println(ex);
        System.out.println("afterThrowingAdvice...");
    }

    /**
     * 后置通知(比如进行释放资源操作)
     */
    @After("execution(* com.mashirro.springaop_learn_demo.service.impl.*.*(..))")
    public void finallyAdvice(JoinPoint joinPoint) {
        System.out.println("finallyAdvice...");
    }


    /**
     * 环绕通知:
     * 通知方法的第一个参数必须是ProceedingJoinPoint类型。
     * 在通知正文中，对ProceedingJoinPoint调用proceed()将导致底层方法执行。around通知返回的值是方法调用方看到的返回值。
     */
    @Around("pt1()")
    public Object AroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
        //do something...
        Object retVal = pjp.proceed();
        //do something...
        return retVal;
    }
}
