package com.mini.resultvo;

import lombok.Data;

@Data
public class ResultVO<T> {
    private Integer code;
    private String message;
    private T data;
    private Long total;

    public static <T> ResultVO<T> success() {
        ResultVO<T> resultVO = new ResultVO<T>();
        resultVO.setCode(StatusCode.SUCCESS);
        resultVO.setMessage("成功");
        return resultVO;
    }

    public static <T> ResultVO<T> success(String message) {
        ResultVO<T> resultVO = new ResultVO<T>();
        resultVO.setCode(StatusCode.SUCCESS);
        resultVO.setMessage(message);
        return resultVO;
    }

    public static <T> ResultVO<T> success(T data) {
        ResultVO<T> resultVO = new ResultVO<T>();
        resultVO.setCode(StatusCode.SUCCESS);
        resultVO.setMessage("操作成功");
        resultVO.setData(data);
        return resultVO;
    }

    public static <T> ResultVO<T> url(T data) {
        ResultVO<T> resultVO = new ResultVO<T>();
        resultVO.setCode(StatusCode.SUCCESS);
        resultVO.setMessage("操作成功");
        resultVO.setData(data);
        return resultVO;
    }
    public static <T> ResultVO<T> ok(T data) {
        ResultVO<T> resultVO = new ResultVO<T>();
        resultVO.setCode(StatusCode.SUCCESS);
        resultVO.setMessage("操作成功");
        resultVO.setData(data);
        return resultVO;
    }

    public static <T> ResultVO<T> success(T data, Long total) {
        ResultVO<T> resultVO = new ResultVO<T>();
        resultVO.setCode(StatusCode.SUCCESS);
        resultVO.setMessage("操作成功");
        resultVO.setData(data);
        resultVO.setTotal(total);
        return resultVO;
    }

    public static <T> ResultVO<T> fail() {
        ResultVO<T> resultVO = new ResultVO<T>();
        resultVO.setCode(StatusCode.FAIL);
        resultVO.setMessage("操作失败");
        return resultVO;
    }

    public static <T> ResultVO<T> fail(String message) {
        ResultVO<T> resultVO = new ResultVO<T>();
        resultVO.setCode(StatusCode.FAIL);
        resultVO.setMessage(message);
        return resultVO;
    }
}
