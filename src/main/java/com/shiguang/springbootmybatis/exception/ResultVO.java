package com.shiguang.springbootmybatis.exception;

import lombok.Data;

import java.io.Serializable;

/**
 * HTTP请求返回的最外层对象
 */
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVO<T> implements Serializable{

    private static final long serialVersionUID = -4408861705368024383L;
    /* 错误码*/
    private Integer code;
    /*信息*/
    private String message;
    /*内容*/
    private T data;

}
