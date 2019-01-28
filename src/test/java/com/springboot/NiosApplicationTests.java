package com.springboot;

import com.springboot.dto.Message;
import com.springboot.entity.Permission;
import com.springboot.service.PermissionService;
import com.springboot.service.UserRoleService;
import com.springboot.service.UserService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class NiosApplicationTests {

    private static final Logger LOGGER = Logger.getLogger(NiosApplicationTests.class);

    @Autowired
    @Qualifier("userServiceImpl")
    UserService userService;

    @Autowired
    @Qualifier("userRoleServiceImpl")
    UserRoleService userRoleService;

    @Autowired
    @Qualifier("permissionServiceImpl")
    PermissionService permissionService;

    @Test
    public void permissionLoads() {
        Message message = permissionService.findAll();
        List<Permission> permissions = (List<Permission>) message.getExtend().get("permissions");
        System.out.println(permissions.get(0).getId());
    }


    @Test
    public void contextLoads() {
        Message users = userService.findAllWithPage(0,1);
    }

    @Test
    public void testUserRole() {
        Message message = userRoleService.findAll();

        LOGGER.info(message);
    }

}
