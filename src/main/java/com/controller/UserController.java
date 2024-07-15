package com.controller;

import com.common.R;
import com.entity.User;
import com.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping
    public R<List<User>>  selectList(){
        List<User> list = userService.list();
        return R.success(list);
    }
    @GetMapping("/page")
    @Cacheable(value = "userCache",key = "#page + '_'+#pageSize + '_' + #username")
    public R<List<User>> selectPage(Integer pageSize,Integer page,String username){
        List<User> users = userService.selectPage(pageSize, page, username);
        return R.success(users);
    }

    @PostMapping
    @CacheEvict(value = "userCache",allEntries = true)//清除缓存中的用户信息
    public R<String> save(@RequestBody User user){
        userService.save(user);
        return R.success("添加成功");
    }
    @DeleteMapping
    @CacheEvict(value = "userCache",allEntries = true)//清除缓存中的用户信息
    public R<String> delete(Long id){
        userService.removeById(id);
        return R.success("删除成功");
    }

    @PutMapping
    @CacheEvict(value = "userCache",allEntries = true)//清除缓存中的用户信息
    public R<String> update(@RequestBody User user){
        //修改
        userService.updateById(user);
        return R.success("保存成功");
    }


}
