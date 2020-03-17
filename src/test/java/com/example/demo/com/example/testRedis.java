package com.example.demo.com.example;

import java.util.HashMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class testRedis {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testString() throws Exception {
//        stringRedisTemplate.opsForValue().set("aaa", "111");
        stringRedisTemplate.opsForValue().append("aaa", " 222");
        System.out.println(stringRedisTemplate.opsForValue().get("aaa"));
    }

    @Test
    public void testList() throws Exception {
        redisTemplate.opsForList().rightPushAll("list", "item" + redisTemplate.opsForList().size("list"));
        System.out.println(redisTemplate.opsForList().range("list", 0, redisTemplate.opsForList().size("list") - 1));
    }

    @Test
    public void testHash() throws Exception {
        Map<String, String> dict = new HashMap<String, String>();
        dict.put("hello", "world");
        dict.put("redis", "json");
        dict.forEach((key, value) -> {
            System.out.println(key + ":" + "value");
        });
        redisTemplate.opsForHash().putAll("testMap", dict);
        System.out.println(redisTemplate.opsForHash().entries("testMap"));
    }

    @Test
    public void testSet() {
        redisTemplate.opsForSet().add("testSet", "1", "2", "3");
        redisTemplate.opsForSet().members("testSet").forEach((item) -> {
            System.out.println(item);
        });
    }

    @Test
    public void testJSONObj() throws Exception {
        User user = new User("redis", "redisPass");
        String user_str = JSON.toJSONString(user);
        System.out.println(user_str);
        redisTemplate.opsForValue().set("user_json", user_str);
        String json_user = (String) redisTemplate.opsForValue().get("user_json");
        System.out.println(JSON.parseObject(json_user, User.class));
    }

    @Test
    public void testSerObj() throws Exception {
        User user = new User("ready", "perfect");
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        operations.set("user", user);
        System.out.println(operations.get("user"));
    }
}