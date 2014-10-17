#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package demo.spring.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.support.StaticApplicationContext;

import ${package}.${parentArtifactId}.GreetingServiceBean;

public class HelloWorldImplTest {

    @Test
    public void test() {
        try(StaticApplicationContext applicationContext = new StaticApplicationContext()) {
//            
//            GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
//            beanDefinition.setBeanClass(HelloWorldImpl.class);
//            beanDefinition.setAutowireCandidate(true);
//            beanDefinition.setAutowireMode(org.springframework.beans.factory.support.AbstractBeanDefinition.AUTOWIRE_BY_NAME);
//            applicationContext.registerBeanDefinition("abc1", beanDefinition);
//            //applicationContext.registerSingleton("abc1", HelloWorldImpl.class);
//
//            
//            beanDefinition = new GenericBeanDefinition();
//            beanDefinition.setBeanClass(FunImpl.class);
//            beanDefinition.setAutowireCandidate(true);
//            beanDefinition.setAutowireMode(org.springframework.beans.factory.support.AbstractBeanDefinition.AUTOWIRE_BY_NAME);
//            applicationContext.registerBeanDefinition("abc", beanDefinition);
//            
//            //applicationContext.registerSingleton("abc", FunImpl.class);
//            
//            HelloWorldImpl helloWorld = applicationContext.getBean(HelloWorldImpl.class);
//            helloWorld.sayHi("huh");
//            
//            Assert.assertTrue(helloWorld.getFun() != null);
        }
    }
}
