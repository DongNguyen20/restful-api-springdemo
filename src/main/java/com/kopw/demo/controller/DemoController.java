package com.kopw.demo.controller;

import com.kopw.demo.dto.UserDto;
import com.kopw.demo.dto.UserInfoDto;
import com.kopw.demo.service.DemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/demo/users")
@RequiredArgsConstructor
public class DemoController {
    private final DemoService demoService;

    @GetMapping
    public List<UserInfoDto> getUsers() {
        return demoService.getUsers();
    }

    @PostMapping
    public UserInfoDto createUser(@RequestBody UserDto userDto) {
        return demoService.createUser(userDto);
    }

    @GetMapping("/{id}")
    public UserInfoDto createUser(@PathVariable Long id) {
        return demoService.getUserById(id);
    }
}
