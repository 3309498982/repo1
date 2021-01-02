package com.study.redis01;

import com.study.redis01.entity.SysRole;
import com.study.redis01.service.SysRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = Redis01Application.class)
@RunWith(SpringRunner.class)
public class RoleTests {

    @Autowired
    private SysRoleService sysRoleService;

    @Test
    public void testUpdateRole(){
        SysRole sysRole = new SysRole();
        sysRole.setId("1");
        sysRole.setRoleName("admin");
        sysRoleService.updateById(sysRole);
    }

}
