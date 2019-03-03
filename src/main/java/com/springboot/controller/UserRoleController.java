package com.springboot.controller;

import com.springboot.dto.Message;
import com.springboot.dto.UserRoleDto;
import com.springboot.service.UserRoleService;
import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/userRole")
public class UserRoleController {

    private static final Logger LOGGER = Logger.getLogger(UserRoleController.class);

    @Autowired
    Mapper mapper;

    @Autowired
    @Qualifier("userRoleServiceImpl")
    UserRoleService userRoleService;

    @ResponseBody
    @GetMapping("userRole")
    public Message findAll() {
        List<UserRoleDto> userRoleDtos = userRoleService.findAll();

        return Message.success("success").add("list",userRoleDtos);
    }

    @ResponseBody
    @PostMapping("/userRole")
    public Message save(@RequestBody UserRoleDto userRoleDto) {
        userRoleService.save(userRoleDto);

        return Message.success("success");
    }

    @ResponseBody
    @GetMapping("/userRole/{id}")
    public Message findById(@PathVariable(value = "id") String id) {
        UserRoleDto userRoleDto = userRoleService.findById(id);

        return Message.success("success").add("userRole",userRoleDto);
    }

    @ResponseBody
    @DeleteMapping("/userRole/{userRoleIds}")
    public Message delete(@PathVariable String userRoleIds) {
        try {
            List<String> userList = Arrays.asList(userRoleIds.split("-"));
            userRoleService.delete(userList);
            return Message.success();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e.getCause());
            throw e;
        }
    }

}
