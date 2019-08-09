package com.provider5552.curator;/**
 * @Author daim
 * @Description //TODO end
 * @Date date
 **/

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname DBConfig
 * @Description TODO
 * @Date 2019/7/31 
 * @Created by daim
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DBConfig {
    public  String url;
    public  String driver;
    public  String username;
    public  String password;

}
