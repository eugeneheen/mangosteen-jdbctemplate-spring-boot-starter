package com.github.eugeneheen.mangosteen.jdbctemplate;

import com.github.eugeneheen.mangosteen.jdbctemplate.pojo.User;
import com.github.eugeneheen.mangosteen.jdbctemplate.repository.impl.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MangosteenJdbctemplateSpringBootStarterApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoads() {
        User user = this.userRepository.selectById(2L);
        Assertions.assertNotNull(user);
    }

}
