package com.ts.tokentest.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JwtUtil {

    private static final String SIGN = "#%%sdfd@$^%";

    /**
     * 生成token
     * @param map
     * @return
     */
    public static String getToken(Map<String ,String > map) {

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE,7);  //默认7天过期

        //生成jwt builder
        JWTCreator.Builder builder = JWT.create();

        //payload载荷，存放用户信息，将map中的值放入claim中
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
                              //设置过期时间
        String token = builder.withExpiresAt(instance.getTime())
                //设置签名算法
                .sign(Algorithm.HMAC256(SIGN));

        return token;
    }


    /**
     * 验证token合法性，并返回解密后的token，可以得到payload中的用户信息
     * @param token
     * @return  DecodedJWT
     */
    public static DecodedJWT verify(String token) {
        //如果token不对，会自动抛出异常
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);

        return  verify;
    }

}
