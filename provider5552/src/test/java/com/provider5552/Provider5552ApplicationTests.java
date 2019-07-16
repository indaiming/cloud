package com.provider5552;

import com.provider5552.service.UserService;
import com.provider5552.utils.RedisUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Provider5552ApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	@Qualifier("primaryJdbcTemplate")
	protected JdbcTemplate jdbcTemplate1;

	//@Autowired 配合Qualifier 使用
	@Autowired
	@Qualifier("secondaryJdbcTemplate")
	protected JdbcTemplate jdbcTemplate2;

	@Test
	public void setUp() {
		List<Map<String, Object>> maps = jdbcTemplate1.queryForList("SELECT * FROM `product` LIMIT 10");
		List<Map<String, Object>> maps1 = jdbcTemplate2.queryForList("SELECT * FROM `product` LIMIT 10");
		System.out.println("++++++多数据源测试！++++++");
	}


	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private UserService userService;

	//使用spring-redis-data自带的StringRedisTemplate 测试
	@Test
	public void opsForValue() throws Exception {
		// 保存字符串
		stringRedisTemplate.opsForValue().set("daim", "daimingmingming");
		Assert.assertEquals("daimingmingming", stringRedisTemplate.opsForValue().get("daim"));

	}

	@Test
	public void test1() throws Exception {
		Long a = 10L;
		String u1 = userService.findOneUser("daiming",11);
//		User3306 u1 = userService.findOne(a);
		System.out.println("第一次查询：" + u1.toString());

		String u2 = userService.findOneUser("daiming",11);
		System.out.println("第二次查询：" + u2.toString());
	}



	@Test
	public void test() throws Exception {
		// 保存字符串
		String dam = RedisUtil.set("today","2019", 0);
		String dam1 = RedisUtil.get("daim", 0);
		Assert.assertEquals("2019", RedisUtil.get("today", 0));

	}

}
