package com.kopw.demo.service.impl;

import com.kopw.demo.dto.UserDto;
import com.kopw.demo.dto.UserInfoDto;
import com.kopw.demo.entity.UserEntity;
import com.kopw.demo.exception.NotFoundException;
import com.kopw.demo.repository.UserRepository;
import com.kopw.demo.service.DemoService;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.DataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DemoServiceImpl implements DemoService {
    Logger log = LoggerFactory.getLogger(getClass().getName());
    private final UserRepository userRepository;

    @Override
    public List<UserInfoDto> getUsers() {
        List<UserEntity> users = userRepository.findAll();
        log.info("get user: ");
        return users.stream().map(s -> UserInfoDto.builder().username(s.getUserName()).build())
                .sorted(Comparator.comparing(UserInfoDto::getUsername))
                .toList();
    }

    @Override
    public UserInfoDto createUser(UserDto userDto) {
        UserEntity savedUser = userRepository.save(UserEntity.builder().userName(userDto.getUsername())
                .passWord(userDto.getPassword()).build());
        log.info("save {} successfully!", savedUser.getUserName());
        return UserInfoDto.builder().username(savedUser.getUserName()).build();
    }

    @Override
    public UserInfoDto getUserById(Long id) {
        UserEntity existedUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));
        log.info("User with id {} found: {}", id, existedUser.getUserName());
        return UserInfoDto.builder()
                .username(existedUser.getUserName())
                .build();
    }
}

