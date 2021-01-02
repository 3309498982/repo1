package com.study.redis01;

import com.study.redis01.entity.SysUser;
import com.study.redis01.service.SysUserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = Redis01Application.class)
@RunWith(SpringRunner.class)
class Redis01ApplicationTests {

    @Autowired
    private SysUserService sysUserService;

    @Test
    void contextLoads() {
        sysUserService.findAll().forEach(u -> System.out.println("u:" + u));
        System.out.println("============================================");
        sysUserService.findAll().forEach(u -> System.out.println("u:" + u));
    }

    @Test
    void testSave(){
        SysUser user = new SysUser();
        user.setUsername("test");
        user.setPassword("123");
        sysUserService.save(user);
    }

    @Test
    void testDelete() {
        sysUserService.deleteById("2");
    }

    @Test
    void testUserRole(){
        sysUserService.findUserRole().forEach(u-> System.out.println("u:=====================>" + u));
        System.out.println("============================================");
        sysUserService.findUserRole().forEach(u-> System.out.println("u:=====================>" + u));
    }

}
