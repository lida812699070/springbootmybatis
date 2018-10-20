package com.shiguang.springbootmybatis.handler;

import com.shiguang.springbootmybatis.exception.ResultVO;
import com.shiguang.springbootmybatis.exception.ResultVOUtil;
import com.shiguang.springbootmybatis.exception.MyException;
import com.shiguang.springbootmybatis.exception.MyAuthorizeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class MyExceptionHandler {

    //拦截登录异常
    @ExceptionHandler(value = MyAuthorizeException.class)
    public ModelAndView handlerSellerAuthorizeException() {
        return new ModelAndView("redirect:http://www.baidu.com");
    }

    //拦截登录异常
    @ResponseBody
    @ExceptionHandler(value = MyException.class)
    public ResultVO handlerSellerException(MyException e) {
        return ResultVOUtil.error(e.getCode(), e.getMessage());
    }
}
