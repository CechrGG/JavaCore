package acmr.springframework.annotation.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration  //核心配置类
@MapperScan("acmr.springframework.annotation.dao")  //mybatis mapper扫描
@ComponentScan("acmr.springframework.annotation")   //spring注解扫描
public class AppConfig {

    @Bean
    public DataSource dataSource(JdbcConfig jdbc) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(jdbc.getDriver());
        dataSource.setUrl(jdbc.getUrl());
        dataSource.setUsername(jdbc.getUsername());
        dataSource.setPassword(jdbc.getPassword());
        return dataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) {
        SqlSessionFactoryBean sqlSession = new SqlSessionFactoryBean();
        sqlSession.setDataSource(dataSource);
        sqlSession.setTypeAliasesPackage("acmr.springframework.annotation.entity");
//        sqlSession.setMapperLocations(new PathMatchingResourcePatternResolver().getResource(""));
        return sqlSession;
    }
}
