package com.shiguang.springbootmybatis.aspectj;


import com.shiguang.springbootmybatis.exception.MyException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@EnableAspectJAutoProxy
public class CheckLoginAspect {

    @Autowired
    HttpServletRequest httpServletRequest;

    public CheckLoginAspect() {
        System.out.println("CheckLoginAspect 初始化......");
    }

    @Pointcut("@annotation(com.shiguang.springbootmybatis.annotion.CheckLoginAnnotation)")
    public void loginPointCut() {

    }

    @Before("loginPointCut()")
    public void checkLogin(JoinPoint joinPoint) {
//        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = requestAttributes.getRequest();
        //判断是否是我们需要的权限
//        if (StringUtils.isEmpty(request.getParameter("token"))) {
//            throw new MyException(401,"没有权限");
//        }
    }

}
