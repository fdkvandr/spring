package com.corp.spring.bfpp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class VerifyPropertyBeanFactoryPostprocessor implements BeanFactoryPostProcessor, PriorityOrdered {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        log.info("VerifyPropertyBeanFactoryPostprocessor.postProcessBeanFactory()");
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
