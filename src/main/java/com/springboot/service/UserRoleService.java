package com.springboot.service;

import com.springboot.dto.UserRoleDto;

import java.util.List;

public interface UserRoleService {
    UserRoleDto findUserRoleById(String id);

    List<UserRoleDto> findUserRole();
}
