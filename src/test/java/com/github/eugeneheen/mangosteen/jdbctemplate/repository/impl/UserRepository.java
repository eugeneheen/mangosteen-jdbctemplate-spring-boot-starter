package com.github.eugeneheen.mangosteen.jdbctemplate.repository.impl;

import com.github.eugeneheen.mangosteen.jdbctemplate.pojo.User;
import com.github.eugeneheen.mangosteen.jdbctemplate.repository.IUserRepository;
import org.springframework.stereotype.Service;

/**
 * @author ChenZhiHeng
 * @date 2021年08月01日
 */
@Service
public class UserRepository extends JdbcOperations implements IUserRepository {

    @Override
    public User selectById(Long id) {
        StringBuilder sqlSb = new StringBuilder("SELECT ")
                .append("*")
                .append(" FROM ")
                .append("t_user")
                .append(" WHERE ")
                .append("id = ?");

        return super.commonSelectObject(sqlSb.toString(), User.class, new Object[] {id});
    }
}
