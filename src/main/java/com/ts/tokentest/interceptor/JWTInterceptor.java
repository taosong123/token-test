package com.ts.tokentest.interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.ts.tokentest.utils.JwtUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class JWTInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //从请求头中取出token
        String token = request.getHeader("token");
        try {
            //验证token是否合法
            DecodedJWT verify = JwtUtil.verify(token);
            //合法就放行
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        //不合法就拦截
        return false;
    }
}
