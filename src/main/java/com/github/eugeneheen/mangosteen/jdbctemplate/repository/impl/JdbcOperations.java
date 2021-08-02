package com.github.eugeneheen.mangosteen.jdbctemplate.repository.impl;

import com.github.eugeneheen.mangosteen.jdbctemplate.repository.AbstratcJdbcOperations;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.List;
import java.util.Map;

/**
 * 山竹工具包，基于Spring的JdbcTemplate，封装JDBC基础操作接口实现类
 * @author ChenZhiHeng
 * @date 2021年07月31日
 */
@NoArgsConstructor
public class JdbcOperations extends AbstratcJdbcOperations {

    /**
     * 占位符语法的JDBC模板
     */
    @Getter
    @Autowired
    @Qualifier("mangosteenJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    /**
     * 命名语法的JDBC模板
     */
    @Getter
    @Autowired
    @Qualifier("mangosteenNamedParameterJdbcTemplate")
    private NamedParameterJdbcOperations namedParameterJdbcTemplate;

    @Override
    protected int update(String nameSql, Object bean) throws DataAccessException {
        SqlParameterSource sqlParameterSource = super.convertor(bean);
        return this.getNamedParameterJdbcTemplate().update(nameSql, sqlParameterSource);
    }

    @Override
    protected int update(String nameSql, Map<String, ?> paramMap) throws DataAccessException {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(paramMap);
        return this.getNamedParameterJdbcTemplate().update(nameSql, sqlParameterSource);
    }

    @Override
    protected int commonUpdate(String sql, Object... args) throws DataAccessException {
        return this.getJdbcTemplate().update(sql, args);
    }

    @Override
    protected <T> T selectObject(String nameSql, Map<String, ?> paramMap, Class<T> requiredType) throws DataAccessException {
        SqlParameterSource sqlParameterSource = super.convertor(paramMap);
        RowMapper<T> rowMapper = new BeanPropertyRowMapper<T>(requiredType);
        return this.getNamedParameterJdbcTemplate().queryForObject(nameSql, sqlParameterSource, rowMapper);
    }

    @Override
    protected <T> T selectObject(String nameSql, Object bean, Class<T> requiredType) throws DataAccessException {
        SqlParameterSource sqlParameterSource = super.convertor(bean);
        RowMapper<T> rowMapper = new BeanPropertyRowMapper<T>(requiredType);
        return this.getNamedParameterJdbcTemplate().queryForObject(nameSql, sqlParameterSource, rowMapper);
    }

    @Override
    protected Map<String, Object> selectMap(String nameSql, Map<String, ?> paramMap) throws DataAccessException {
        SqlParameterSource sqlParameterSource = super.convertor(paramMap);
        return this.getNamedParameterJdbcTemplate().queryForMap(nameSql, sqlParameterSource);
    }

    @Override
    protected Map<String, Object> selectMap(String nameSql, Object bean) throws DataAccessException {
        SqlParameterSource sqlParameterSource = super.convertor(bean);
        return this.getNamedParameterJdbcTemplate().queryForMap(nameSql, sqlParameterSource);
    }

    @Override
    protected List<Map<String, Object>> select(String nameSql, Map<String, ?> paramMap) throws DataAccessException {
        return this.getNamedParameterJdbcTemplate().queryForList(nameSql, paramMap);
    }

    @Override
    protected List<Map<String, Object>> select(String nameSql, Object bean) throws DataAccessException {
        SqlParameterSource sqlParameterSource = super.convertor(bean);
        return this.getNamedParameterJdbcTemplate().queryForList(nameSql, sqlParameterSource);
    }

    @Override
    protected <T> List<T> select(String nameSql, Class<T> mappedClass) throws DataAccessException {
        RowMapper<T> rowMapper = new BeanPropertyRowMapper<T>(mappedClass);
        return this.getNamedParameterJdbcTemplate().query(nameSql, rowMapper);
    }

    @Override
    protected <T> T commonSelectObject(String sql, Class<T> requiredType, Object... args) throws DataAccessException {
        RowMapper<T> rowMapper = this.convertor(requiredType);
        return this.getJdbcTemplate().queryForObject(sql, rowMapper, args);
    }

    @Override
    protected Map<String, Object> commonSelectMap(String sql, Object... args) throws DataAccessException {
        return this.getJdbcTemplate().queryForMap(sql, args);
    }

    @Override
    protected List<Map<String, Object>> commonSelect(String sql, Object... args) throws DataAccessException {
        return this.getJdbcTemplate().queryForList(sql, args);
    }

    @Override
    protected <T> List<T> commonSelect(String sql, Class<T> mappedClass) throws DataAccessException {
        RowMapper<T> rowMapper = this.convertor(mappedClass);
        return this.getJdbcTemplate().query(sql, rowMapper);
    }

    @Override
    protected <T> List<T> commonSelect(String sql, RowMapper<T> rowMapper, Object... args) throws DataAccessException {
        return this.getJdbcTemplate().query(sql, rowMapper, args);
    }

    protected <T> T selectObject(String sql, Class<T> requiredType, Object... args) throws DataAccessException {
        return this.getJdbcTemplate().queryForObject(sql, requiredType, args);
    }

    @Override
    protected List<Map<String, Object>> queryForList(String sql, Object[] args, int[] argTypes, int currPageNum, int pageSize) throws DataAccessException {
       if(pageSize>0){
           int startIndex = (currPageNum - 1) * pageSize;            //开始行索引
           int endIndex = (currPageNum) * pageSize + 1;              //截止行索引
           StringBuilder newSql = new StringBuilder("SELECT * FROM");
           newSql.append("(SELECT A.*,ROWNUM rn FROM");
           newSql.append(" (" + sql + ") A");
           newSql.append(" where rownum<" + endIndex + ") B");
           newSql.append(" where B.rn>" + startIndex);
           return this.getJdbcTemplate().queryForList(newSql.toString(), args, argTypes);
       }
        return this.getJdbcTemplate().queryForList(sql, args, argTypes);
    }
}
