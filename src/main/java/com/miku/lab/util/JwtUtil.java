package com.miku.lab.util;

import java.security.Key;
import java.util.*;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;


import com.miku.lab.entity.UserInfo;
import io.jsonwebtoken.*;

/**
 * token生成类
 * @author：miku
 * @date：2021年6月9日
 * @version:1.0
 */
public class JwtUtil {
    /**
     * 得到subject
     * @return
     */
    public static Claims checkJWT(String token) {
    try {
        final Claims claims = Jwts.parser().setSigningKey(Constant.JWT_SECRET).parseClaimsJws(token).getBody();
        return claims;
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
    }

    /**
     * 传入值生成jwt
     * @return
     */
    public static String geneToken(UserInfo user){
        if(user==null){
            return null;
        }
        Map<String,Object> map = new HashMap<>();
        map.put("user_id",user.getUserId()) ;
        String token = Jwts.builder()
                .setSubject(user.getUserId()) //jwt的所有者
                .setClaims(map)
                .claim("user_id",user.getUserId())
                .claim("role_code",user.getRoleCode())
                .claim("user",user)
                .setIssuedAt(new Date()) //设置jwt的签发时间
                .signWith(SignatureAlgorithm.HS256, Constant.JWT_SECRET) //设置签名使用的签名算法和签名使用的密钥
                .setExpiration(new Date((System.currentTimeMillis()+Constant.JWT_TTL)))
                .compact(); //生成xxxx.xxxx.xxx样式
        return token;
    }
    /**
     * 解析jwt
     * @param jwt
     * @return
     */
    public static Claims parseJWT(String jwt) {
        Claims claims = (Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(Constant.JWT_SECRET))
                .parseClaimsJws(jwt).getBody());
        System.out.println("ID: " + claims.getId());
        System.out.println("Subject: " + claims.getSubject());
        System.out.println("Issuer: " + claims.getIssuer());
        System.out.println("Expiration: " + claims.getExpiration());
        return claims;
    }

    /**
          * 获取用户名
          * @param token
          * @return
          */
    public static String getUsername(String token){
        Claims claims = Jwts.parser().setSigningKey(Constant.JWT_SECRET).parseClaimsJws(token).getBody();
        System.out.println(claims.get("user_id").toString());
            return claims.get("user_id").toString();
    }

    /**
     *
     * @param token
     * @return
     */
    public static String getRole(String token){
        Claims claims = Jwts.parser().setSigningKey(Constant.JWT_SECRET).parseClaimsJws(token).getBody();
        System.out.println("role:"+claims.get("role_code").toString());
        return claims.get("role_code").toString();
    }


    /**
     *
     * @param token
     * @return
     */
    public static String getUserSort(String token){
        Claims claims = Jwts.parser().setSigningKey(Constant.JWT_SECRET).parseClaimsJws(token).getBody();
        System.out.println("sort:"+claims.get("sort").toString());
        return claims.get("sort").toString();
    }

    /**
        * 是否过期
        * @param token
        * @return
        */
     public static boolean isExpiration(String token){
        Claims claims = Jwts.parser().setSigningKey(Constant.JWT_SECRET).parseClaimsJws(token).getBody();
        return claims.getExpiration().before(new Date());
     }


}