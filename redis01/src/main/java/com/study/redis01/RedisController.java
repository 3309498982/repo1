package com.study.redis01;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
public class RedisController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public static final String SYS_NUM = "0123456789";

    @RequestMapping("getVerifyCode")
    public String getVerifyCode(String mobileNumber) {
        if (StringUtils.isEmpty(mobileNumber)) {
            return "手机号码不能为空";
        }

        String res = stringRedisTemplate.opsForValue().get(mobileNumber);
        if (res != null) {
            return "请勿重复点击";
        }

        // 获取长度为 6 的随机数字
        char[] code = new char[6];
        Random random = new Random();
        for (int i = 0; i < code.length; i++) {
            code[i] = SYS_NUM.charAt(random.nextInt(SYS_NUM.length()));
        }

        // 设置到redis，并且设置过期时间为60s
        stringRedisTemplate.opsForValue().set(mobileNumber, new String(code), 60L, TimeUnit.SECONDS);

        return new String(code);

    }

}
