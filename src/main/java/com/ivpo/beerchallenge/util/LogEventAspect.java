package com.ivpo.beerchallenge.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogEventAspect {
    private static final Logger LOG = LoggerFactory.getLogger(LogEventAspect.class);

    @Before("@annotation(com.ivpo.beerchallenge.util.LogEvent)")
    public void logEventBeforeMethodCall(JoinPoint joinPoint) {
        if (LOG.isInfoEnabled()) {
            LOG.info(String.format("Method %s is called.", joinPoint.getSignature().getName()));
            LOG.info(String.format("Method description is: %s ", joinPoint.getSignature().toString()));
        }
    }

    @After("@annotation(com.ivpo.beerchallenge.util.LogEvent)")
    public void logEventAfterMethodCall(JoinPoint joinPoint) {
        if (LOG.isInfoEnabled()) {
            LOG.info(String.format("Method %s is finished.", joinPoint.getSignature().getName()));
        }
    }
}
