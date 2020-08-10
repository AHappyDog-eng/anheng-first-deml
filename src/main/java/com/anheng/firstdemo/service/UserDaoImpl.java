package com.anheng.firstdemo.service;

import com.alibaba.fastjson.JSON;
import com.anheng.firstdemo.entity.User;
import com.anheng.firstdemo.mapper.UserMapper;
import com.anheng.firstdemo.utils.ResStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author WN
 * @date 2020/8/10 19:41
 */
@Service
public class UserDaoImpl implements UserDao {
    private Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
    @Autowired
    private UserMapper userMapper;
    @Override
    public String loginUser(String userName, String userPassword) {
        logger.info(userName);
        logger.info(userPassword);
        User user = userMapper.loginUser(userName, userPassword);
        if (user!=null){
            return JSON.toJSONString(new ResStatus(20000,"SUCCESS"));
        }else {
            return JSON.toJSONString(new ResStatus(40001,"用户名或者密码不正确"));
        }

    }

    @Override
    public String registerUser(String userName, String userPassword) {
        Integer isSuccess = userMapper.registerUser(userName, userPassword);
        if (isSuccess>0){
            return JSON.toJSONString(new ResStatus(20000,"SUCCESS"));
        }else {
            return JSON.toJSONString(new ResStatus(40002,"插入失败"));
        }
    }

    @Override
    public String updateUser(String userName, String userOldPassword, String userNewPassword) {
        Integer integer = userMapper.updateUser(userName, userOldPassword, userNewPassword);
        if (integer>0){
            return JSON.toJSONString(new ResStatus(20000,"SUCCESS"));
        }else {
            return JSON.toJSONString(new ResStatus(40004,"插入失败，请检查输入是否正确"));
        }
    }
}
