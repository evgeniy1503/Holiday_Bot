package ru.prokhorov.povod.aspect.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Аспект для логирования.
 *
 * @author Evgeniy_Prokhorov
 */

@Slf4j
@Aspect
@Component
@ConditionalOnProperty(name = "logging.enabled", havingValue = "true", matchIfMissing = true)
public class LoggingAspect {

    @Pointcut("@annotation(ru.prokhorov.povod.aspect.logging.annotation.Logging)")
    public void loggingMethod() {}

    @Before("loggingMethod()")
    public void beforeMethod(JoinPoint joinPoint) {
        log.info("start: {} {}", joinPoint.getSignature().getName(), joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "loggingMethod()", returning = "result")
    public void afterMethod(JoinPoint joinPoint, Object result) {
        log.info("end: {}, result = {}", joinPoint.getSignature().getName(), Objects.isNull(result) ? "Метод без результат" : result);
    }

    @AfterThrowing(pointcut = "loggingMethod()", throwing = "exception")
    public void afterThrowingMethod(JoinPoint joinPoint, Exception exception) {
        log.error("Возникла ошибка метода {} : {}", joinPoint.getSignature().getName(), exception.getMessage());
    }

}
