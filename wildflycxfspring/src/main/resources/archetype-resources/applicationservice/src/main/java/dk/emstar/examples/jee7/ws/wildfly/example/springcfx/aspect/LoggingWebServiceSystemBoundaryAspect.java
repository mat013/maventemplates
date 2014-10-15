#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package dk.emstar.examples.jee7.ws.wildfly.example.springcfx.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Aspect
public class LoggingWebServiceSystemBoundaryAspect {
    @Pointcut("execution(public * *(..))")
    public void publicMethod() {
    }

    @Pointcut("within(@javax.jws.WebService *)")
    public void webserviceAnnotatedBeans() {
    }
    
    @Around("publicMethod()  && webserviceAnnotatedBeans()")
    public Object logInputOutput(final ProceedingJoinPoint joinPoint) throws Throwable {
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        logger.info("{} was invoked to logger {}", joinPoint.getSignature(), joinPoint.getTarget().getClass().toString());
        return joinPoint.proceed();
    }
}