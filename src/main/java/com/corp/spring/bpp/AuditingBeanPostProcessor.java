package com.corp.spring.bpp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class AuditingBeanPostProcessor implements BeanPostProcessor {

    private final Map<String, Class<?>> auditBeans = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(Auditing.class)) {
            auditBeans.put(beanName, bean.getClass());
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = auditBeans.get(beanName);
        if (beanClass != null) {
            return Proxy.newProxyInstance(beanClass.getClassLoader(), bean.getClass()
                    .getInterfaces(), (proxy, method, args) -> {
                log.info("Audit method: " + method.getName());
                var startTime = System.nanoTime();
                try {
                    return method.invoke(bean, args);
                } finally {
                    log.info("Time execution: " + (System.nanoTime() - startTime));
                }
            });
        }
        return bean;
    }
}
