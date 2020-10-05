package edu.spring.xml.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

public class App {
    private static final Logger logger =
            LoggerFactory.getLogger(Category.class);

    public static void main(String... args) {

        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/application-context.xml");
        ctx.refresh();
        Category category = (Category) ctx.getBean(Category.class);
        displayInfo(category, "Autowiring byType");
        ctx.close();
    }

    public static void displayInfo(Category category, String str) {
        logger.info("\n==================Book " + str + " Output Start==================\n" +
                str + ": "+category.toString() +"\n" +
                "==================Book " + str + " Output End==================\n");
    }
}
