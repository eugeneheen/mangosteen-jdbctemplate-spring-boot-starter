package com.eugeneheen.mangosteen.jdbctemplate.repository;

import com.eugeneheen.mangosteen.jdbctemplate.pojo.User;

/**
 * @author ChenZhiHeng
 * @date 2021年08月01日
 */
public interface IUserRepository {
    User selectById(Long id);
}
