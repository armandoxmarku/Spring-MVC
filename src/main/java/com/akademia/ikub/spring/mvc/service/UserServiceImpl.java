package com.akademia.ikub.spring.mvc.service;

import com.akademia.ikub.spring.mvc.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private Map<Long,User> userMap;
    @PostConstruct
    public void init(){
        userMap = new HashMap<>();
        userMap.put(1L,new User(1L,"name1","surname1","email1","122333"
                ,"passowrd","Albania","Tirane Albania","1001"));
        userMap.put(2L,new User(2L,"name2","surname2","email1","122333"
                ,"passowrd","Albania","Tirane Albania","1001"));
        userMap.put(3L,new User(3L,"name3","surname3","email1","122333"
                ,"passowrd","Albania","Tirane Albania","1001"));
    }
    @Override
    public List<User> getUsers() {
        return userMap.entrySet().stream()
                .map(entry -> entry.getValue())
                .collect(Collectors.toList());
    }

    private static Long ID_INCREMENT =3l;


    @Override
    public User getUserById(Long id) {
        return userMap.get(id);
    }

    @Override
    public User updateById(Long id,User user) {
        System.err.println(id);
        userMap.remove(id);
        userMap.put(id, user);
        System.err.println(userMap.get(id));
        return userMap.get(id);
    }

    @Override
    public void deleteById(Long id) {
        System.err.println("ID "+id);
        User u = userMap.remove(id);
        System.err.println("USer "+u);
    }

    @Override
    public Long createUser(User user) {
        user.setId(getUserId());
        userMap.put(user.getId(),user);
        return user.getId();
    }

   private  Long getUserId(){
        ID_INCREMENT++;
        return ID_INCREMENT;
    }
}
