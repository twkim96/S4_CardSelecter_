package twk.cardselecter.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Date;
import java.util.Objects;

@Configuration
@PropertySource("classpath:/application.properties")
public class DatabaseConfig {
    @Bean //싱글톤 접근
    @ConfigurationProperties("spring.datasource.hikari") // 스프링에서 런타임시 properties값 가져오기
    public HikariConfig hikariConfig(){
        System.out.println("hikariConfig: " + new Date());
        return new HikariConfig();
    }

    @Bean //여기까지 접속 관련 설정
    public DataSource dataSource(){
        DataSource dataSource = new HikariDataSource(hikariConfig());
        System.out.println("dataSource: " + dataSource + " " + new Date());
        return dataSource;
    }
    /*아래로는 mybatis 연동 위한 코드*/
    @Bean //DataSource객체를 받아서 만들어진 setDataSource.
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        Resource[] resources = new PathMatchingResourcePatternResolver()
                .getResources("classpath:sqls/*.xml");
        sqlSessionFactoryBean.setMapperLocations(resources);
        Objects.requireNonNull(sqlSessionFactoryBean.getObject()).getConfiguration().setMapUnderscoreToCamelCase(true);

        return (SqlSessionFactory) sqlSessionFactoryBean.getObject();
    }
    @Bean //MyBatis에서 SqlSession을 이용해 DataSource(DB연결정보)로 실제 DB에 접근하는 빈.
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /*코드 설명 출처: https://hororolol.tistory.com/507*/
}
