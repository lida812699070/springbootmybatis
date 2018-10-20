package com.shiguang.springbootmybatis.exception;

public class ResultVOUtil {

    public static ResultVO success(Object o) {
        ResultVO resultVO = new ResultVO();
        resultVO.setMessage("成功");
        resultVO.setCode(0);
        resultVO.setData(o);
        return resultVO;
    }

    public static ResultVO success() {
        return success(null);
    }

    public static ResultVO error(Integer code ,String message) {
        ResultVO resultVO = new ResultVO();
        resultVO.setMessage(message);
        resultVO.setCode(code);
        return resultVO;
    }
}
