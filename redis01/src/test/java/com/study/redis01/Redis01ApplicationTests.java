package com.study.redis01;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = Redis01Application.class)
@RunWith(SpringRunner.class)
class Redis01ApplicationTests {

    @Test
    void contextLoads() {
    }

}
