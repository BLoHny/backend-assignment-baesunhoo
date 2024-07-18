package kr.co.polycube.backendtest.global.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Aspect
@Component
public class ClientLoggingAspect {

    @Before("execution(* kr.co.polycube.backendtest.domain.user.controller.UserController.*(..))")
    public void logClientAgent() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        log.info("User-agent : {}", request.getHeader("user-agent"));
    }
}
