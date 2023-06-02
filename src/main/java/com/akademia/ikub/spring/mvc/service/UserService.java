package com.akademia.ikub.spring.mvc.service;

import com.akademia.ikub.spring.mvc.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    Long createUser(User user);
    User getUserById(Long id);
    User updateById(Long id,User user);
    void deleteById(Long id);
}
