package com.provider5552.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Index {
    @RequestMapping("/")
    public String index(ModelMap map) {
        // 加入一个属性，用来在模板中读取
        map.addAttribute("title", "主页");
        map.addAttribute("info", "纽扣第一颗就扣错了，可你扣到最后一颗才发现；有些事一开始就是错的，可只有到最后才不得不承认。当心倦了累了，记得停下来，给心灵寻一份安暖，给自己找一方晴空。谁是谁生命中的过客，谁是谁生命的转轮，那前世的尘，今世的风，无穷无尽的哀伤的精魂。");

        // return模板文件的名称，对应src/main/resources/templates/index.html
        return "index";
    }

    @RequestMapping("/home")
    public String home(ModelMap map) {
        // 加入一个属性，用来在模板中读取
        map.addAttribute("title", "欢迎页面");
        map.addAttribute("info", "岁月清浅，人生无恙。落红片片，满地残！其中寓意种种，懂的无需释解，不懂的不必解释，人生就是这样，痛并快乐着！坐拥一季烟尘，倚清浅的时光，沏一壶清茶，蘸岁月的墨香，书写诗意的人生。岁月与我，在细品静赏中，暗香盈袖，恬然而静美！");

        // return模板文件的名称，对应src/main/resources/templates/index.html
        return "hello";
    }

    @RequestMapping("/hello")
    public String hello(ModelMap map) {
        // 加入一个属性，用来在模板中读取
        map.addAttribute("title", "欢迎页面");
        map.addAttribute("info", "岁月清浅，人生无恙。落红片片，满地残！其中寓意种种，懂的无需释解，不懂的不必解释，人生就是这样，痛并快乐着！坐拥一季烟尘，倚清浅的时光，沏一壶清茶，蘸岁月的墨香，书写诗意的人生。岁月与我，在细品静赏中，暗香盈袖，恬然而静美！");

        // return模板文件的名称，对应src/main/resources/templates/index.html
        return "hello";
    }

    // 省略之前的内容...

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

}
