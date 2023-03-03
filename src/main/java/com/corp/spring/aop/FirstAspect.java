package com.corp.spring.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Aspect
@Component
@Slf4j
public class FirstAspect {

    @Pointcut("@within(org.springframework.stereotype.Controller)")
    public void isControllerLayer() {
    }

    @Pointcut("within(com.corp.spring.service.*)")
    public void isServiceLayer() {
    }

    @Pointcut("this(org.springframework.stereotype.Repository)")
    public void isRepositoryLayer() {
    }

    @Pointcut("isControllerLayer() && @annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void hasGetMapping() {
    }

    @Pointcut("isControllerLayer() && args(org.springframework.ui.Model,..)")
    public void hasModelParam() {
    }

    @Pointcut("isControllerLayer() && @args(com.corp.spring.validation.UserInfo)")
    public void hasUserInfoParamAnnotation() {
    }

    @Pointcut("bean(*Service)")
    public void isServiceLayerBean() {
    }

    @Pointcut("execution(public * com.corp.spring.service.*Service.findById(*))")
    public void anyFindByIdServiceMethod() {
    }

    @Before(value = "anyFindByIdServiceMethod()" +
            "&& args(id)" + "&& target(service)" +
            "&& this(serviceProxy)" +
            "&& @within(transactional)",
            argNames = "joinPoint,id,service,serviceProxy,transactional")
    public void addLogging(JoinPoint joinPoint,
            Object id,
            Object service,
            Object serviceProxy,
            Transactional transactional) {
        log.info("invoked findById method class {}, with id {}", service, id);
    }
}