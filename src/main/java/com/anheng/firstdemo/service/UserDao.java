package com.anheng.firstdemo.service;

/**
 * @author WN
 * @date 2020/8/10 19:41
 */
public interface UserDao {
    /**
     * @param userName     账户
     * @param userPassword 密码
     * @return String 返回JSON 字符串
     */
    String loginUser(String userName, String userPassword);

    /**
     * 注册
     *
     * @param userPassword 密码
     * @param userName     用户名
     * @return java.lang.String 返回状态 是否注册成功
     */
    String registerUser(String userName, String userPassword);

    /**
     * 修改密码
     * @param userName 用户名
     * @param userOldPassword 旧密码
     * @param userNewPassword 新密码
     * @return String 返回状态
     */
    String updateUser(String userName, String userOldPassword, String userNewPassword);
}
