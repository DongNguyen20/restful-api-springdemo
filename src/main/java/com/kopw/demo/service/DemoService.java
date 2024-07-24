package com.kopw.demo.service;

import com.kopw.demo.dto.UserDto;
import com.kopw.demo.dto.UserInfoDto;

import java.util.List;

public interface DemoService {
    List<UserInfoDto> getUsers();

    UserInfoDto createUser(UserDto userDto);

    UserInfoDto getUserById(Long id);
}
