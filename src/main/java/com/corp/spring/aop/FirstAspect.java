// package com.corp.spring.aop;
//
// import lombok.extern.slf4j.Slf4j;
// import org.aspectj.lang.JoinPoint;
// import org.aspectj.lang.ProceedingJoinPoint;
// import org.aspectj.lang.annotation.*;
// import org.springframework.stereotype.Component;
// import org.springframework.transaction.annotation.Transactional;
//
// @Aspect
// @Component
// @Slf4j
// public class FirstAspect {
//
//     @Pointcut("this(org.springframework.stereotype.Repository)")
//     public void isRepositoryLayer() {
//     }
//
//     @Pointcut("com.corp.spring.aop.CommonPointcuts.isControllerLayer()ControllerLayer() && @annotation(org.springframework.web.bind.annotation.GetMapping)")
//     public void hasGetMapping() {
//     }
//
//     @Pointcut("com.corp.spring.aop.CommonPointcuts.isControllerLayer()ControllerLayer() && args(org.springframework.ui.Model,..)")
//     public void hasModelParam() {
//     }
//
//     @Pointcut("com.corp.spring.aop.CommonPointcuts.isControllerLayer()ControllerLayer() && @args(com.corp.spring.validation.UserInfo)")
//     public void hasUserInfoParamAnnotation() {
//     }
//
//     @Pointcut("bean(*Service)")
//     public void isServiceLayerBean() {
//     }
//
//     @Pointcut("execution(public * com.corp.spring.service.*Service.findById(*))")
//     public void anyFindByIdServiceMethod() {
//     }
//
//     @Before(value = "anyFindByIdServiceMethod()" +
//             "&& args(id)" + "&& target(service)" +
//             "&& this(serviceProxy)" +
//             "&& @within(transactional)",
//             argNames = "joinPoint,id,service,serviceProxy,transactional")
//     public void addLogging(JoinPoint joinPoint,
//             Object id,
//             Object service,
//             Object serviceProxy,
//             Transactional transactional) {
//         log.info("before - invoked findById method class {}, with id {}", service, id);
//     }
//
//     @AfterReturning(value = "anyFindByIdServiceMethod()" +
//             "&& target(service)", returning = "result")
//     public void addLoggingAfterReturning(Object result, Object service) {
//         log.info("after returning - invoked findById method class {}, result {}", service, result);
//     }
//
//     @AfterThrowing(value = "anyFindByIdServiceMethod()" +
//             "&& target(service)", throwing = "ex")
//     public void addLoggingAfterThrowing(Throwable ex, Object service) {
//         log.info("after throwing - invoked findById method class {}, exception {}: {}", service, ex.getClass(), ex.getMessage());
//     }
//
//     @After(value = "anyFindByIdServiceMethod()" +
//             "&& target(service)")
//     public void addLoggingAfterFinally(Object service) {
//         log.info("after (finally) - invoked findById method class {}", service);
//     }
//
//     @Around("anyFindByIdServiceMethod()" +
//             "&& target(service)" +
//             "&& args(id)")
//     public Object addLoggingAround(ProceedingJoinPoint joinPoint, Object service, Object id) throws Throwable {
//         log.info("AROUND before - invoked findById method class {}, with id {}", service, id);
//         try {
//             Object result = joinPoint.proceed();
//             log.info("AROUND after returning - invoked findById method class {}, result {}", service, result);
//             return result;
//         } catch (Throwable ex) {
//             log.info("AROUND after throwing - invoked findById method class {}, exception {}: {}", service, ex.getClass(), ex.getMessage());
//             throw ex;
//         } finally {
//             log.info("AROUND after (finally) - invoked findById method class {}", service);
//         }
//     }
// }