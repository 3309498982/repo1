package com.study.redis01;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = Redis01Application.class)
@RunWith(SpringRunner.class)
class Redis01ApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(123);
    }

}
