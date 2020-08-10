package com.anheng.firstdemo.controller;

import com.alibaba.fastjson.JSON;
import com.anheng.firstdemo.entity.User;
import com.anheng.firstdemo.service.UserDaoImpl;
import com.anheng.firstdemo.utils.IsNAN;
import com.anheng.firstdemo.utils.JwtUtils;
import com.anheng.firstdemo.utils.ResStatus;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * @author WN
 * @date 2020/8/10 19:21
 */
@RestController
@RequestMapping("person")
public class UserController {
    @Autowired
    private UserDaoImpl userDao;

    @RequestMapping("/test")
    public String home(HttpServletRequest request, HttpServletResponse response){

        Cookie cookie = new Cookie("userId","userName");
        response.addCookie(cookie);
        return "哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈";
    }
    @RequestMapping("/home")
    public String home1(){
        return "哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈";
    }
    /**
     * 需要过滤
     * @param userMsg 构造的请求体
     * @return String 返回状态
     */
    @PostMapping("/user")
    public String userLogin(@RequestBody String userMsg,HttpServletResponse response,HttpServletRequest request) {
        // 先找 cookie
        Cookie[] cookies = request.getCookies();

        if (userMsg ==null || userMsg.equals(" ")){
            return JSON.toJSONString(new ResStatus(40001,"账号或者密码格式错误"));
        }
        boolean isNAN = IsNAN.judgeMsg(userMsg);
        User user = JSON.parseObject(userMsg, User.class);

        // 如果 校验通过 再校验 密码
        if (isNAN == true){
            String loginMsg = userDao.loginUser(user.getUserName(), user.getUserPassword());
            return loginMsg;
        }else {
            return JSON.toJSONString(new ResStatus(40001,"账号错误或者密码错误"));
        }
    }

    @PutMapping("/user")
    public String putUser(@RequestBody String userMsg) {
        if (userMsg ==null || userMsg.equals(" ")){
            return JSON.toJSONString(new ResStatus(40001,"账号或者密码格式错误"));
        }
        boolean isNAN = IsNAN.judgeMsg(userMsg);
        User user = JSON.parseObject(userMsg, User.class);
        // 如果 校验通过 再校验 密码
        if (isNAN == true){
            String s = userDao.registerUser(user.getUserName(), user.getUserPassword());
            return s;
        }else {
            return JSON.toJSONString(new ResStatus(40002,"账号或者密码输入不规范"));
        }
    }
    @PatchMapping("/user")
    public String updateUser(@RequestBody String userUpdateMsg) {
        if (userUpdateMsg ==null || userUpdateMsg.equals(" ")){
            return JSON.toJSONString(new ResStatus(40001,"账号或者密码格式错误"));
        }
        boolean isNAN = false;
        HashMap userMap = JSON.parseObject(userUpdateMsg, HashMap.class);

        if (userUpdateMsg!=null && !userUpdateMsg.equals(" ")){
            boolean userName = IsNAN.judgeMsgQueue((String) userMap.get("userName"));
            boolean userNewPassword = IsNAN.judgeMsgQueue((String) userMap.get("userNewPassword"));
            boolean userOldPassword = IsNAN.judgeMsgQueue((String) userMap.get("userOldPassword"));
            if (userName == userNewPassword == userOldPassword == true){
                isNAN = true;
            }
        }else {
            return JSON.toJSONString(new ResStatus(40003,"输入的格式有错误"));
        }
        // 通过检测
        if (isNAN == true){
            String s = userDao.updateUser((String) userMap.get("userName"),
                    (String) userMap.get("userOldPassword"),
                    (String) userMap.get("userNewPassword"));
            return s;
        }else {
            return JSON.toJSONString(new ResStatus(40003,"输入的格式有错误"));
        }
    }

}
