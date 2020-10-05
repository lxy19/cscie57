package edu.spring.xml.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

public class BookThrowsAdvice implements ThrowsAdvice {
    private static Logger logger = LoggerFactory.getLogger(
            BookThrowsAdvice .class);

    public static void main(String... args) throws Exception {
        ErrorBean errorBean = new ErrorBean();

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(errorBean);
        pf.addAdvice(new BookThrowsAdvice());

        ErrorBean proxy = (ErrorBean) pf.getProxy();

        try {
            proxy.throwException();
        } catch (Exception ignored) {

        }
    }

    public void afterThrowing(Method method, Object[] args, Object target,
                              RuntimeException ex) throws Throwable {
        logger.info("***");
        logger.info("RuntimeException Capture");
        logger.info("Caught: " + ex.getClass().getName());
        logger.info("Method: " + method.getName());
        System.out.print("***\n");
    }
}
