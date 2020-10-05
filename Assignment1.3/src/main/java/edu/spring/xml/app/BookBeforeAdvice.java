package edu.spring.xml.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.lang.reflect.Method;

public class BookBeforeAdvice implements MethodBeforeAdvice {
    private static Logger logger = LoggerFactory.getLogger(
            BookBeforeAdvice.class);

    public static void main(String... args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/application-context.xml");
        ctx.refresh();
        Book book = (Book) ctx.getBean("bookDesignPatterns");

        ProxyFactory pf = new ProxyFactory();
        pf.addAdvice(new BookBeforeAdvice());
        pf.setTarget(book);

        Book proxy = (Book) pf.getProxy();

        proxy.reading();
    }
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        logger.info("Before '" + method.getName() + "' buy the book.");
    }
}
