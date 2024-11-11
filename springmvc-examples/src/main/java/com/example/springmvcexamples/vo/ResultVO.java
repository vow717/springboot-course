package com.example.springmvcexamples.vo;

import com.example.springmvcexamples.exception.Code;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultVO {
    private int code;
    private String message;
    private Object data;

    private static final ResultVO EMPTY = ResultVO.builder().code(200).build();
    public static ResultVO ok() {
        return EMPTY;
    }
    public static ResultVO success(Object data) {
        return ResultVO.builder().code(200).data(data).build();
    }
    // 通用错误码
    public static ResultVO error(Code code) {
        return ResultVO.builder().code(code.getNumber()).message(code.getMessage()).build();
    }
    // 自定义错误码和错误信息
    public static ResultVO error(int code, String message) {
        return ResultVO.builder().code(code).message(message).build();
    }
}
