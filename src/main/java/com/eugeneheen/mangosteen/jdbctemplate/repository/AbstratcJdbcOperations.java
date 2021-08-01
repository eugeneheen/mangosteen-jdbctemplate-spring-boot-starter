package com.eugeneheen.mangosteen.jdbctemplate.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.List;
import java.util.Map;

/**
 * 山竹工具包，基于Spring的JdbcTemplate，封装JDBC基础操作接口定义
 * @author chenzhiheng
 * @date 2021年07月31日
 */
public abstract class AbstratcJdbcOperations {

    /**
     * RowMapper转换器
     *
     * @param mappedClass 待转换的结果类型
     * @param <T>         任意类型
     * @return BeanPropertyRowMapper
     */
    protected <T> RowMapper<T> convertor(Class<T> mappedClass) {
        return new BeanPropertyRowMapper(mappedClass);
    }

    /**
     * SqlParameterSource转换器
     *
     * @param bean 待转换的参数值类型
     * @return BeanPropertySqlParameterSource
     */
    protected SqlParameterSource convertor(Object bean) {
        return new BeanPropertySqlParameterSource(bean);
    }

    /**
     * SqlParameterSource转换器
     *
     * @param values 待转换的参数值类型
     * @return MapSqlParameterSource
     */
    protected SqlParameterSource convertor(Map<String, ?> values) {
        return new MapSqlParameterSource(values);
    }

    /**
     * 更新数据操作，适用于INSERT、UPDATE、DELETE
     *
     * @param nameSql 命名语法SQL;例如:(UPDATE users SET name = :userName)
     * @param bean    Bean类型的属性名，替换命名语法参数
     * @return 更新数据条数
     */
    protected abstract int update(String nameSql, Object bean) throws DataAccessException;

    /**
     * 更新数据操作，适用于INSERT、UPDATE、DELETE
     *
     * @param nameSql  命名语法SQL;例如:(UPDATE users SET name = :userName)
     * @param paramMap Map类型的key，替换命名语法参数
     * @return 更新数据条数
     */
    protected abstract int update(String nameSql, Map<String, ?> paramMap) throws DataAccessException;

    /**
     * 更新数据操作，适用于INSERT、UPDATE、DELETE
     *
     * @param sql  占位符语法SQL;例如:(UPDATE users SET name = ?)
     * @param args 数组类型，替换占位符参数
     * @return 更新数据条数
     */
    protected abstract int commonUpdate(String sql, Object... args) throws DataAccessException;

    /**
     * 查询匹配条件的唯一一条数据
     *
     * @param nameSql      命名语法SQL;例如:(SELECT * FROM users WHERE name = :userName, age = :age)
     * @param paramMap     Map类型的key，替换命名参数
     * @param requiredType 返回的Bean类型
     * @return 唯一的结果，Bean类型为requiredType
     */
    protected abstract <T> T selectObject(String nameSql, Map<String, ?> paramMap, Class<T> requiredType) throws DataAccessException;

    /**
     * 查询匹配条件的唯一一条数据
     *
     * @param nameSql      命名语法SQL;例如:(SELECT * FROM users WHERE name = :userName, age = :age)
     * @param bean         Bean类型的属性名，替换命名参数
     * @param requiredType 返回的Bean类型
     * @return 唯一的结果，Bean类型为requiredType
     */
    protected abstract <T> T selectObject(String nameSql, Object bean, Class<T> requiredType) throws DataAccessException;

    /**
     * 查询匹配条件的唯一一条数据
     *
     * @param nameSql  命名语法SQL;例如:(SELECT * FROM users WHERE name = :userName, age = :age)
     * @param paramMap Map类型的key，替换命名参数
     * @return 唯一的Map类型结果
     */
    protected abstract Map<String, Object> selectMap(String nameSql, Map<String, ?> paramMap) throws DataAccessException;

    /**
     * 查询匹配条件的唯一一条数据
     *
     * @param nameSql 命名语法SQL;例如:(SELECT * FROM users WHERE name = :userName, age = :age)
     * @param bean    Bean类型的属性名，替换命名参数
     * @return 唯一的Map类型结果
     */
    protected abstract Map<String, Object> selectMap(String nameSql, Object bean) throws DataAccessException;

    /**
     * 查询匹配条件的所有数据
     *
     * @param nameSql  命名语法SQL;例如:(SELECT * FROM users WHERE name = :userName, age = :age)
     * @param paramMap Map类型的key，替换命名参数
     * @return 包含Map的List结果列表
     */
    protected abstract List<Map<String, Object>> select(String nameSql, Map<String, ?> paramMap) throws DataAccessException;

    /**
     * 查询匹配条件的所有数据
     *
     * @param nameSql 命名语法SQL;例如:(SELECT * FROM users WHERE name = :userName, age = :age)
     * @param bean    Bean类型的属性名，替换命名参数
     * @return 包含Map的List结果列表
     */
    protected abstract List<Map<String, Object>> select(String nameSql, Object bean) throws DataAccessException;

    /**
     * 查询所有数据
     *
     * @param nameSql     命名语法SQL;例如:(SELECT * FROM users)
     * @param mappedClass 返回的class类型
     * @return mappedClass类型的List结果列表
     */
    protected abstract <T> List<T> select(String nameSql, Class<T> mappedClass) throws DataAccessException;

    /**
     * 查询匹配条件的唯一一条数据
     *
     * @param sql          占位符语法SQL;例如:(SELECT * FROM users WHERE name = ?, age = ?)
     * @param requiredType 返回的Bean类型
     * @param args         数组类型，替换占位符参数
     * @return 唯一的结果，Bean类型为requiredType
     */
    protected abstract <T> T commonSelectObject(String sql, Class<T> requiredType, Object... args) throws DataAccessException;

    /**
     * 查询匹配条件的唯一一条数据
     *
     * @param sql  占位符语法SQL;例如:(SELECT * FROM users WHERE name = ?, age = ?)
     * @param args 数组类型，替换占位符参数
     * @return 唯一的Map类型结果
     */
    protected abstract Map<String, Object> commonSelectMap(String sql, Object... args) throws DataAccessException;

    /**
     * 查询匹配条件的所有数据
     *
     * @param sql  占位符语法SQL;例如:(SELECT * FROM users WHERE name = ?, age = ?)
     * @param args 数组类型，替换占位符参数
     * @return 包含Map的List结果列表
     */
    protected abstract List<Map<String, Object>> commonSelect(String sql, Object... args) throws DataAccessException;

    /**
     * 查询所有数据
     *
     * @param sql         占位符语法SQL;例如:(SELECT * FROM users)
     * @param mappedClass 返回的class类型
     * @return mappedClass类型的List结果列表
     */
    protected abstract <T> List<T> commonSelect(String sql, Class<T> mappedClass) throws DataAccessException;

    /**
     *
     * @param sql 占位符语法SQL；例如：(SELECT * FROM users WHERE username = ?)
     * @param rowMapper 数据库字段和BEAN字段不一致时的映射
     * @param args 条件参数
     * @param <T>
     * @return <T> List<T> 返回list结果集
     * @throws org.springframework.dao.DataAccessException
     */
    protected abstract <T> List<T> commonSelect(String sql, RowMapper<T> rowMapper, Object... args) throws DataAccessException;

    /**
     *
     * @param sql 占位符语法SQL；例如：（SELECT COUNT(*) FROM users  WHERE = ?）
     * @param requiredType 返回值类型
     * @param args 条件参数
     * @param <T>
     * @return <T>T 返回值
     * @throws org.springframework.dao.DataAccessException
     */
    protected abstract <T> T selectObject(String sql, Class<T> requiredType, Object... args) throws DataAccessException;

    /**
     *
     * @param sql 占位sql语句 目前只支持oracle分页
     * @param args 参数
     * @param argTypes 参数类型
     * @param currPageNum   当前页数
     * @param pageSize  页记录大小 <=0时 表示不分页
     * @return
     * @throws DataAccessException
     */
    protected abstract List<Map<String, Object>> queryForList(String sql, Object[] args, int[] argTypes, int currPageNum, int pageSize) throws DataAccessException;
}
