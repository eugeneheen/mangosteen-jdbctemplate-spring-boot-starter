### Mangosteen JDBC Kit

- 基于`SpringBoot 2.x`版本。
- 已继承`Spring Data JDBC 2.2.3`版本，***切勿重复引入依赖***。
- 实现基于`Spring Framework`框架`JdbcTemplate`操作原生`SQL`，用于`占位符语法 - ?`和`命名语法 - :Bean属性名`这2中语法的便捷工具方法封装，以此更简洁快速的操作执行原生`SQL`的业务场景。
- 要使用`Spring Data JDBC`，直接参照[SpringData官方手册](https://docs.spring.io/spring-data/jdbc/docs/2.2.3/reference/html/#preface)

### 如何集成 Mangosteen JDBC

- Setp1. `SpringBoot`工程中增加依赖: 
  - Maven
    ```vim
    <dependency>
      <groupId>com.github.eugeneheen</groupId>
      <artifactId>mangosteen-jdbctemplate-spring-boot-starter</artifactId>
      <version>1.0.0</version>
    </dependency>
    ```
  - Gradle
    ```vim
    implementation 'com.github.eugeneheen:mangosteen-jdbctemplate-spring-boot-starter:1.0.0'
    ```
- Setp2. `SpringBoot`工程的配置文件中开启`Mangosteen JDBC`:
  - `yaml`配置文件
    ```yaml
    mangosteen:
      jdbctemplate:
        enable: true
    ```
  - `properties`配置文件
    ```properties
    mangosteen.jdbctemplate.enable=true
    ```
- Setp3. `SpringBoot`工程的配置文件中配置数据库:
  - `yaml`配置文件
    ```yaml
    spring:
      datasource:
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://127.0.0.1:3306/eugene
        username: root
        password: 12345678
    ```
  - `properties`配置文件
    ```properties
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.url=jdbc:mysql://127.0.0.1:3306/eugene
    spring.datasource.username=root
    spring.datasource.password=12345678
    ```
 - Setp4. 编写一个`数据层`的`类`，继承`JdbcOperations`，通过`super`关键字调用执行原生`SQL`的`API`方法，代码示例:
   ```java
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
   ```
