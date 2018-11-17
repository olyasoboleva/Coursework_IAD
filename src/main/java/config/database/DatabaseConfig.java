package config.database;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories("repository")
@ComponentScan("repository")
@EnableTransactionManagement
public class DatabaseConfig {

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUsername("postgres");
        dataSource.setPassword("qwerty");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/s243887");
        dataSource.setDriverClassName("org.postgresql.Driver");
        return dataSource;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter(){
        return new HibernateJpaVendorAdapter();
    }

    public Properties getHibernateProperties(){
        Properties properties = new Properties();
        properties.put("db.hibernate.hbm2ddl.auto", "update");
        properties.put("db.hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
        return properties;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter());
        entityManagerFactory.setJpaProperties(getHibernateProperties());
        entityManagerFactory.setPackagesToScan("entity");
        return entityManagerFactory;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        return jpaTransactionManager;
    }

}
