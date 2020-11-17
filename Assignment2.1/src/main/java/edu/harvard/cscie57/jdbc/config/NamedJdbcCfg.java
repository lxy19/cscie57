package edu.harvard.cscie57.jdbc.config;

import edu.harvard.cscie57.jdbc.dao.BookCategoryDao;
import edu.harvard.cscie57.jdbc.dao.BookCategoryDaoImpl;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:db/jdbc.properties")
@ComponentScan(basePackages = "edu.harvard.cscie57.jdbc")
public class NamedJdbcCfg {
    private static Logger logger = LoggerFactory.getLogger(NamedJdbcCfg.class);

    @Value("${driverClassName}")
    private String driverClassName;
    @Value("${url}")
    private String url;
    @Value("lix8")
    private String username;
    @Value("${password}")
    private String password;

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        try {
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName(driverClassName);
            dataSource.setUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            return dataSource;
        } catch (Exception e) {
            logger.error("DBCP DataSource bean cannot be created!", e);
            return null;
        }
    }

    @Bean(name="bookCategoryDao")
    public BookCategoryDao bookCategoryDao() {
        BookCategoryDaoImpl bookCategoryDao = new BookCategoryDaoImpl();
        bookCategoryDao.setDataSource(dataSource());
        return bookCategoryDao;
    }

}
