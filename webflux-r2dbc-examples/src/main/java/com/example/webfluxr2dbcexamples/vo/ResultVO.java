package com.example.webfluxr2dbcexamples.vo;

import com.example.webfluxr2dbcexamples.exception.Code;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultVO {
    private int codeNum;
    private String message;
    private Object data;

    private static final ResultVO EMPTY = ResultVO.builder().codeNum(200).build();
    public static ResultVO ok() {
        return EMPTY;
    }
    public static ResultVO success(Object data) {
        return ResultVO.builder().codeNum(200).data(data).build();
    }

    public static ResultVO error(int codeNum, String message) {
        return ResultVO.builder().codeNum(codeNum).message(message).build();
    }
    public static ResultVO error(Code code) {
        return ResultVO.builder().codeNum(code.getCode()).message(code.getMessage()).build();
    }
}
