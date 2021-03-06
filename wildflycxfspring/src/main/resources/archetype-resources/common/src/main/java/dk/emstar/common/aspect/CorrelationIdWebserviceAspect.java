#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package dk.emstar.${artifactId}.aspect;

import java.util.UUID;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.google.${artifactId}.base.Strings;

import dk.emstar.example.header.ContextHeader;

@Aspect 
public class CorrelationIdWebserviceAspect {
    private static final String CORRELATION_ID_KEY = "__correlationId";
    private static final Logger logger = LoggerFactory.getLogger(CorrelationIdWebserviceAspect.class);

    @Pointcut("execution(public * *(..))")
    public void publicMethod() {
    }

    @Pointcut("within(@javax.jws.WebService *)")
    public void webserviceAnnotatedBeans() {
    }

    @Around("publicMethod()  && webserviceAnnotatedBeans()")
    public Object logInputOutput(final ProceedingJoinPoint joinPoint) throws Throwable {
        ContextHeader requestHeader = findRequestHeader(joinPoint);
        boolean hasRequestHeader = requestHeader != null;

        String correlationId = !hasRequestHeader || Strings.isNullOrEmpty(requestHeader.getCorrelationId()) 
                ? UUID.randomUUID().toString() : requestHeader.getCorrelationId();
        try {
            MDC.put(CORRELATION_ID_KEY, correlationId);
            Signature invokedMethodSignature = joinPoint.getSignature();
            Class<?> declaringType = invokedMethodSignature.getDeclaringType();
            if (!hasRequestHeader) {
                logger.info("Generating new correlation id for for invocation {}.{}", declaringType.getSimpleName(), invokedMethodSignature.getName());
            } else {
                logger.info("Found request header correlation id {} for for invocation {}.{}", correlationId, declaringType.getSimpleName(), invokedMethodSignature.getName());
            }

            return joinPoint.proceed();
        } finally {
            MDC.remove(CORRELATION_ID_KEY);
        }
    }

    private ContextHeader findRequestHeader(final ProceedingJoinPoint joinPoint) {
        for(Object object : joinPoint.getArgs()) {
            if(object != null && object instanceof ContextHeader) {
                return (ContextHeader) object; 
            }
        }
        
        return null;
    }

    public static String getCurrentCorrelationId() {
        return MDC.get(CORRELATION_ID_KEY);
    }
}