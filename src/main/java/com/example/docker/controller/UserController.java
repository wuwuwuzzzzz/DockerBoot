package com.example.docker.controller;

import cn.hutool.core.util.IdUtil;
import com.example.docker.entities.User;
import com.example.docker.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Random;

/**
 * @author wxz
 * @date 14:44 2022/10/15
 */
@Api(value = "用户User接口")
@RestController
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    /**
     * @author wxz
     * @date 14:49 2022/10/15
     */
    @ApiOperation("数据库新增3条记录")
    @RequestMapping(value = "/user/add")
    public void addUser() {
        for (int i = 1; i <= 3; i++) {
            User user = new User();
            user.setUsername("wxz" + i);
            user.setPassword(IdUtil.simpleUUID().substring(0, 6));
            user.setSex(String.valueOf(new Random().nextInt(2)));
            userService.addUser(user);
        }
    }

    /**
     * @param id 用户ID
     * @return com.example.docker.entities.User
     * @author wxz
     * @date 14:51 2022/10/15
     */
    @ApiOperation("查询一条记录")
    @GetMapping("/user/find/{id}")
    public User findUserById(@PathVariable Integer id) {
        return userService.findUserById(id);
    }
}
