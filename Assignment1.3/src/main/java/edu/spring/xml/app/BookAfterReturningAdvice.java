package edu.spring.xml.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.lang.reflect.Method;

public class BookAfterReturningAdvice implements AfterReturningAdvice {
    private static Logger logger = LoggerFactory.getLogger(
            BookAfterReturningAdvice.class);

    public static void main(String... args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/application-context.xml");
        ctx.refresh();
        Book book = (Book) ctx.getBean("bookDesignPatterns");

        ProxyFactory pf = new ProxyFactory();

        pf.addAdvice(new BookAfterReturningAdvice());
        pf.setTarget(book);

        Book proxy = (Book) pf.getProxy();
        proxy.reading();
    }
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        logger.info("After '" + method.getName()+ "' write the book review.");
    }
}
