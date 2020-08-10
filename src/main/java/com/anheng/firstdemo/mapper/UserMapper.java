package com.anheng.firstdemo.mapper;

import com.anheng.firstdemo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author WN
 * @date 2020/8/10 19:22
 */
@Mapper
@Repository
public interface UserMapper {
    /**
     * @param userName     账户
     * @param userPassword 密码
     * @return String 返回JSON 字符串
     */
    User loginUser(@Param("userName") String userName,@Param("userPassword") String userPassword);

    /**
     * 注册
     *
     * @param userPassword 密码
     * @param userName     用户名
     * @return java.lang.String 返回状态 是否注册成功
     */
    Integer registerUser(@Param("userName") String userName,@Param("userPassword") String userPassword);

    /**
     * 修改密码
     * @param userName 用户名
     * @param userOldPassword 旧密码
     * @param userNewPassword 新密码
     * @return String 返回状态
     */
    Integer updateUser(@Param("userName") String userName,@Param("userOldPassword") String userOldPassword,@Param("userNewPassword") String userNewPassword);
}
