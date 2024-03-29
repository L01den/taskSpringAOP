package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Slf4j
@Aspect
@Component
public class RecoverExceptionAspect {

    @Pointcut("@annotation(com.example.demo.RecoverException)")
    public void methodsAnnotatedWith() {
    }

    @Around("methodsAnnotatedWith()")
    public Object exceptionInterception(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        RecoverException annotation = signature.getMethod().getAnnotation(RecoverException.class);
        try {
            joinPoint.proceed();
        } catch (Throwable e) {
            for (Class<? extends RuntimeException> exceptionClass : annotation.noRecoverFor()) {
                if(exceptionClass.isAssignableFrom(e.getClass())){
                    throw e;
                }
                log.error("{}:{}", joinPoint.getTarget().getClass(), joinPoint.getSignature().getName());
                return 0;
            }
        }
        return 0;
    }

}
