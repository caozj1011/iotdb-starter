package org.apache.autoconfigure.starter;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.type.filter.AssignableTypeFilter;

public class IoTDBBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        IoTDBClassPathBeanDefinitionScanner scanner = new IoTDBClassPathBeanDefinitionScanner(registry);
        // 限制必须实现IoTDBRepository接口
        scanner.addIncludeFilter(new AssignableTypeFilter(IoTDBRepository.class));
        scanner.scan("com.timecho.example");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}
