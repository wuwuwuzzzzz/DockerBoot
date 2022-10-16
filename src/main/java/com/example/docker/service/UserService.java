package com.example.docker.service;

import com.example.docker.entities.User;
import com.example.docker.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wxz
 * @date 14:33 2022/10/15
 */
@Service
@Slf4j
public class UserService {

    public static final String CACHE_KEY_USER = "user:";

    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 添加用户
     *
     * @param user 用户
     * @author wxz
     * @date 14:34 2022/10/15
     */
    public void addUser(User user) {

        // 先插入数据
        int i = userMapper.insertSelective(user);

        if (i > 0) {
            user = userMapper.selectByPrimaryKey(user.getId());
            String key = CACHE_KEY_USER + user.getId();
            redisTemplate.opsForValue().set(key, user);
        }
    }

    /**
     * 根据ID查询用户
     *
     * @param id ID
     * @return com.example.docker.entities.User
     * @author wxz
     * @date 14:35 2022/10/15
     */
    public User findUserById(Integer id) {
        User user = null;
        String key = CACHE_KEY_USER + id;
        user = (User) redisTemplate.opsForValue().get(key);
        if (user == null) {
            user = userMapper.selectByPrimaryKey(id);
            if (user == null) {
                return user;
            } else {
                redisTemplate.opsForValue().set(key, user);
            }
        }
        return user;
    }
}
