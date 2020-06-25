package kr.ac.jejunu.controller;

import kr.ac.jejunu.database.object.User;
import kr.ac.jejunu.database.dao.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class RestController {
    private final UserDao userDao;

    @GetMapping("/{id}")
    public User get(@PathVariable("id") String id){
        return userDao.findById(id).get();
    }


    public User create(@RequestBody User user){
        userDao.save(user);
        return user;
    }
}
