#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package dk.emstar.common.aspect;

import java.util.UUID;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

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

        // TODO: find the standard header for the request and see if we can get
        // the correlationID
        // For now it will be null and thereby generate a new correlationid
        UUID correlationId = null;
        Signature invokedMethodSignature = joinPoint.getSignature();

        boolean isNewCorrelationId = false;
        if (correlationId == null) {
            correlationId = UUID.randomUUID();
            isNewCorrelationId = true;
        }

        try {
            MDC.put(CORRELATION_ID_KEY, correlationId.toString());
            if (isNewCorrelationId) {
                Class<?> declaringType = invokedMethodSignature.getDeclaringType();
                logger.info("Generating new correlation id for for invocation {}.{}", declaringType.getSimpleName(), invokedMethodSignature.getName());
            }

            return joinPoint.proceed();
        } finally {
            MDC.remove(CORRELATION_ID_KEY);
        }
    }

    public static String getCurrentCorrelationId() {
        return MDC.get(CORRELATION_ID_KEY);
    }

}