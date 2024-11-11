package com.example.backendexamples.exception;


import lombok.*;

@EqualsAndHashCode(callSuper = true) //意思是生成的equals方法会判断父类的属性
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class XException extends RuntimeException{
    private Code code;
    private int codeN;
    private String message;

}
