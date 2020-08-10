package com.anheng.firstdemo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author WN
 * @date 2020/8/10 18:49
 */
@Component
public class JwtUtils {
    private static final long EXPIRE_TIME = 7 * 24 * 60 * 60 * 1000;
    //私钥
    private static final String TOKEN_SECRET = "privateKey";

    public  static String createJWT(String username) {

        //id，issuer，subject，ttlMillis都是放在payload中的，可根据自己的需要修改
        //签名的算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //当前的时间
        //签名算法的秘钥，解析token时的秘钥需要和此时的一样
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("miyao");
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        JwtBuilder jwtBuilder = Jwts.builder().setId(username).signWith(signatureAlgorithm, signingKey);
        //压缩
        return jwtBuilder.compact();
    }
    public  static  String parseVer(String jwt) {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary("miyao")).parseClaimsJws(jwt).getBody();
        } catch (Exception e) {
            System.out.println("解析失败");
            return "error...";
        }
        String id = claims.getId();
        return id;
    }

}
