package com.example.springmvcexamples.exception;


import lombok.*;

@EqualsAndHashCode(callSuper = true) //意思是
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class XException extends RuntimeException{
    private Code code;
    private int codeN;
    private String message;

}
