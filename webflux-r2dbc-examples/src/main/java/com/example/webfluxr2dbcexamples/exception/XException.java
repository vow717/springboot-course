package com.example.webfluxr2dbcexamples.exception;

import lombok.*;

//正在生成 equals/hashCode 实现，但即使此类未扩展 java.lang.Object，也不调用超类。
//如果这是有意为之，请在您的类型中添加 '(callSuper=false)'。
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class XException extends RuntimeException {
   private Code code;
   private int codeNum;
   private String message;

}
