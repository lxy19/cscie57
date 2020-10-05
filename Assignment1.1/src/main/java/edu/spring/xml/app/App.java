package edu.spring.xml.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

public class App {
    private static final Logger logger =
            LoggerFactory.getLogger(Category.class);

    public static void main(String... args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/app-context-xml.xml");
        ctx.refresh();
        StringBuilder sb = new StringBuilder();
        //1.1 Create an instance containing a map
        Category categoryMap = (Category) ctx.getBean("categoryMap");
        displayInfo(categoryMap, "Map", sb);
        //1.1 Create an instance containing a set
        Category categorySet = (Category) ctx.getBean("categorySet");
        displayInfo(categorySet, "Set", sb);
        //1.1 CCreate an instance containing a list
        Category categoryList = (Category) ctx.getBean("categoryList");
        displayInfo(categoryList,"List", sb);
        logger.info(sb.toString());
        ctx.close();
    }

    public static void displayInfo(Category category, String str, StringBuilder sb) {
        sb.append("\n==================Book " +str +" Output Start==================\n");
        sb.append("Category -- Book "+str+": [");
        sb.append(category.toString());
        sb.append("]");
        sb.append("\n==================Book " +str +" Output End==================\n");
    }
}
