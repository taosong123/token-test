package com.ts.tokentest.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.ts.tokentest.entity.User;
import com.ts.tokentest.service.UserService;
import com.ts.tokentest.utils.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Api(tags="控制器")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    @ApiOperation(value = "登录")
    public String  login(User user) {

        //获取用户信息
        User result = userService.login(user);

        Map<String , String > map = new HashMap<>();
        map.put("userId",result.getId());
        map.put("userName",result.getName());
        //将用户id，用户名存入token的payload中，生成token
        String token = JwtUtil.getToken(map);

        return token;
    }

    @GetMapping("/verify")
    @ApiOperation(value = "验证")
    //有拦截器后，去掉参数String token
    public Map verify() {
//        //验证token是否合法，并返回解码后的token
//        DecodedJWT verify = JwtUtil.verify(token);
//        //取出解码后存入token的payload中的信息
//        String userId = verify.getClaim("userId").asString();
//        String userName = verify.getClaim("userName").asString();
//
        Map<Object, Object> map = new HashMap<>();
//        map.put("userId",userId);
//        map.put("userName",userName);
        map.put("state","ok");
        return map;
    }


    //以后直接从request中取出token，不用再直接传入token参数
    @GetMapping("/test")
    public Map test(HttpServletRequest request) {
        //从请求头中取出token
        String token = request.getHeader("token");
        //验证token是否合法
        DecodedJWT verify = JwtUtil.verify(token);

        String userId = verify.getClaim("userId").asString();
        String userName = verify.getClaim("userName").asString();

        Map<Object, Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("userName",userName);
        return map;
    }
}
