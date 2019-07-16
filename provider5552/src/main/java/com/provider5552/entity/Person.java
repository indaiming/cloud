package com.provider5552.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;

@Data
@Entry(base = "ou=people,dc=didispace,dc=com", objectClasses = "inetOrgPerson")
public class Person {

    @Id
    private Integer id;
    @DnAttribute(value = "uid", index = 3)
    private String uid;
    @Attribute(name = "cn")
    private String commonName;
    @Attribute(name = "sn")
    private String suerName;
    private String userPassword;

}