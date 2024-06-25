package com.example.springboot.utils;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.springboot.entity.User;
import com.example.springboot.service.Upload.impl.UploadServiceImpl;
import com.example.springboot.service.User.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 拦截器去获取token并验证token
 **/
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {
  private final Log logger = LogFactory.getLog(UploadServiceImpl.class);

  @Resource
  private UserService userService;

  @Override
  public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
    //通过所有OPTION请求
    if(httpServletRequest.getMethod().toUpperCase().equals("OPTIONS")){
      return true;
    }
    //从http请求头中取出token
    String token = httpServletRequest.getHeader("Authorization");
    //从http请求头中取出token
    String refreshToken = httpServletRequest.getHeader("freshToken");
    Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
    //2.遍历
     while(headerNames.hasMoreElements()){
       String name = headerNames.nextElement();
       //通过请求头的名称获取请求头的值
       String value = httpServletRequest.getHeader(name);
       System.out.println(name+"----"+value);
     }
    //如果不是映射到方法直接通过
    if (!(object instanceof HandlerMethod)) {
      return true;
    }
    HandlerMethod handlerMethod = (HandlerMethod) object;
    Method method = handlerMethod.getMethod();
    //检查是否有passtoken注释，有则跳过认证
    if (method.isAnnotationPresent(PassToken.class)) {
      PassToken passToken = method.getAnnotation(PassToken.class);
      if (passToken.required()) {
        return true;
      }
    }
    //获取token中的用户信息
    if (token == null) {
      throw new RuntimeException("登录信息过期");
    }
    String userValue = null;
    try {
      userValue = JWT.decode(token).getAudience().get(0);
    } catch (JWTDecodeException j) {
      throw new RuntimeException("401");
    }
    Map<String, Object> map = new HashMap<>();
    map.put("level", (userValue).substring(0,1));
    map.put("id", (userValue).substring(1));
    User user = userService.findUser(map);
    if (user == null) {
      throw new RuntimeException("用户不存在，请重新登录");
    }

    Date oldTime = JWT.decode(token).getExpiresAt();
    Date refreshTime = JWT.decode(refreshToken).getExpiresAt();
    //获取毫秒级别的差值
    long oldDiff = oldTime.getTime() - new Date().getTime();
    //这样得到的差值是毫秒级别
    long refreshDiff = refreshTime.getTime() - new Date().getTime();
    if (oldDiff <= 0) {
      if (refreshDiff <= 0) {
        logger.error("=== token 已过期, 请重新登录 ===");
        httpServletResponse.sendError(401);
        return false;
      }
    }
    String newToken = userService.getToken(user, 60* 60 * 1000);
    String newRefToken = userService.getToken(user, 24*60*60*1000);
    //更新token
    httpServletResponse.setHeader("Authorization", newToken);
    httpServletResponse.setHeader("freshToken", newRefToken);
    if (token == null) {
      throw new RuntimeException("=== 无token，请重新登录 ===");
    }
    //利用用户密码，解密验证token
    JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
    try {
      jwtVerifier.verify(token);
    } catch (JWTVerificationException e) {
      logger.error("=== token验证失败 ===");
      httpServletResponse.sendError(401);
      return false;
    }
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         Object o, ModelAndView modelAndView) throws Exception {

  }

  @Override
  public void afterCompletion(HttpServletRequest httpServletRequest,
                              HttpServletResponse httpServletResponse,
                              Object o, Exception e) throws Exception {
  }
}
