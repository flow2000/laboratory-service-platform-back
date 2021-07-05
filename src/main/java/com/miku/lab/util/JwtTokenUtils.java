package com.miku.lab.util;/*
 *@author miku
 *@data 2021/6/1 15:34
 *@version:1.1
 */

import com.miku.lab.entity.UserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtTokenUtils {
    /**
     * 创建token
     * @param userInfo
     * @param isRememberMe
     * @return
     */
    public static String createToken(UserInfo userInfo, int isRememberMe){
        //用户为空
        boolean isSeven = false;
        if(isRememberMe==1){
             isSeven = true;
        }else{
            isSeven = false;
        }

        if(userInfo==null){
            return null;
        }
        long expiration = isSeven ? Constant.TOKEN_EXPIRATION_REMEMBER : Constant.TOKEN_EXPIRATION;
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,Constant.TOKEN_SECRET)
                .setIssuer(Constant.TOKEN_ISS)
                .setSubject(userInfo.getUserId())
                .claim("userId", userInfo.getUserId())
                .claim("roleCode",userInfo.getRole_code())
                .claim("phone",userInfo.getPhone())
                .claim("sex",userInfo.getSex())
                .claim("email",userInfo.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date((System.currentTimeMillis()+expiration*1000)))
                .compact();//xxx.xxx.xxx
    }

    /**
     * 通过token获得用户id
     * @param token
     * @return
     */
    public static String getUserId(String token){
        return getTokenBody(token).getSubject();
    }

    /**
     * token是否过期
     * @param token
     * @return
     */
    public static boolean isExpiration(String token){
        return getTokenBody(token).getExpiration().before(new Date());
    }

    /**
     * 获得tokenBody信息
     * @param token
     * @return
     */
    public static Claims getTokenBody(String token){
        return Jwts.parser()
                .setSigningKey(Constant.TOKEN_SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

}
