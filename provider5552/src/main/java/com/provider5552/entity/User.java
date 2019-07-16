package com.provider5552.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//@Table
@Table(name = "user")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column(name="id",columnDefinition="id")
    private long id;

    @Column(name="username",columnDefinition="用户名")
    private String username;

    @Column(name="password",columnDefinition="密码")
    private String password;

    @Column(name="phone",columnDefinition="手机号")
    private String phone;

    @Column(name="email",columnDefinition="邮箱")
    private String email;

}
