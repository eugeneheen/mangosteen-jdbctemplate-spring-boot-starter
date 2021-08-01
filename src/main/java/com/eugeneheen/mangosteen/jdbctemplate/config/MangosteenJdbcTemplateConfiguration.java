package com.eugeneheen.mangosteen.jdbctemplate.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

/**
 * 山竹工具箱，SpringJdbcTemplate通用工具箱的配置类
 * @author ChenZhiHeng
 * @date 2021年07月31日
 */
@ConditionalOnProperty(prefix = "mangosteen.jdbctemplate", name = "enable", havingValue = "true")
@Configuration
public class MangosteenJdbcTemplateConfiguration {

    /**
     * 此处必须使用DataSourceProperties来加载配置文件，否则配置数据源的spring.datasource.url必须修改为spring.datasource.jdbc-url
     * @return DataSourceProperties
     */
    @Bean
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean("defaultDatasource")
    public DataSource defaultDataSource() {
        return dataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean("mangosteenJdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("defaultDatasource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean("mangosteenNamedParameterJdbcTemplate")
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(@Qualifier("defaultDatasource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
}
