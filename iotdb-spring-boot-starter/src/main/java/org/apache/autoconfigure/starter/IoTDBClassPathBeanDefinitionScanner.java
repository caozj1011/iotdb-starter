package org.apache.autoconfigure.starter;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Set;

public class IoTDBClassPathBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {
    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);

        for (BeanDefinitionHolder definitionHolder : beanDefinitionHolders) {

            ScannedGenericBeanDefinition beanDefinition = (ScannedGenericBeanDefinition)definitionHolder.getBeanDefinition();

            String beanClass = beanDefinition.getBeanClassName();

            beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(beanClass);

            beanDefinition.setBeanClass(IoTDBFacotryBean.class);
        }

        return beanDefinitionHolders;
    }


    public IoTDBClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    // 如果它是一个接口就视为有效组件 , 必须实现Repository
    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        AnnotationMetadata metadata = beanDefinition.getMetadata();
        return  metadata.isInterface();
    }



}
