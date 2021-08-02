package com.github.eugeneheen.mangosteen.jdbctemplate.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author ChenZhiHeng
 * @date 2021年08月01日
 */
@Data
public class User {
    private Long id;
    private String name;
    private String nickName;
    private Integer age;
    private Integer sex;
    private Date createTime;
    private Date updateTime;
}
