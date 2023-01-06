package com.corp.spring.bfpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.core.Ordered;

import java.util.List;

public class LogBeanFactoryPostprocessor implements BeanFactoryPostProcessor, Ordered {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanDefinitionName);
            List<ConstructorArgumentValues.ValueHolder> genericArgumentValues = beanDefinition.getConstructorArgumentValues()
                    .getGenericArgumentValues();
            for (ConstructorArgumentValues.ValueHolder genericArgumentValue: genericArgumentValues){
                // TODO 31.12.2022 set get. Здесь можно подкручитьвать сами Bean Definitions, т.к. есть доступ к BeanFactory
            }
        }
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
