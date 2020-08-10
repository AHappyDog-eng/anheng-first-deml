package com.anheng.firstdemo.utils;

import com.alibaba.fastjson.JSON;
import com.anheng.firstdemo.entity.User;
import org.springframework.stereotype.Component;

import javax.swing.*;

/**
 * @author WN
 * @date 2020/8/10 19:44
 */
@Component
public class IsNAN {
    public static boolean ResIsNAN = false;
    public static boolean ResIsNANQueue = false;

    public static final boolean judgeMsg(String msg) {

        int length = msg.length();
        if (length > 1000) {
            return ResIsNAN;
        }
        // 获取User 实体类
        User user = JSON.parseObject(msg, User.class);
        if (user.getUserName().equals("") || user.getUserPassword().equals("")){
            return ResIsNAN;
        }else {
            boolean b = judgeMsgQueue(JwtUtils.parseVer(user.getUserName()));
            // 解析 原密码 是否能通过校验
            boolean b1 = judgeMsgQueue(JwtUtils.parseVer(user.getUserPassword()));
            if (b==true && b1 == true){
                ResIsNAN = true;
            }
        }

        return ResIsNAN;

    }
    public static final boolean judgeMsgQueue(String msg) {
        if (msg == null){
            return false;
        }
        char[] charMsg = msg.toCharArray();
        char count = 0;
        // 不能为空 只能是数字 数字字符串
        if (!msg.equals("") && !(msg == "")) {
            for (int i = 0; i < charMsg.length; i++) {
                if ((charMsg[i] >= '0' && charMsg[i] <= '9')
                        || (charMsg[i] >= 'a' && charMsg[i] <= 'z')
                        || (charMsg[i] >= 'A' && charMsg[i] <= 'Z')) {
                    count++;
                }
            }
            if (count == charMsg.length){
                ResIsNANQueue = true;
            }else {
                ResIsNANQueue =false;
            }
        }
        return ResIsNANQueue;
    }

}
