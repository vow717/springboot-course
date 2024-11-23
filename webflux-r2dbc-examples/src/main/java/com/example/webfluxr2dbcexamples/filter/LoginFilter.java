package com.example.webfluxr2dbcexamples.filter;

import com.example.webfluxr2dbcexamples.component.JWTComponent;
import com.example.webfluxr2dbcexamples.exception.Code;
import com.example.webfluxr2dbcexamples.exception.XException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.result.condition.RequestCondition;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import org.springframework.web.util.pattern.PathPattern;
import org.springframework.web.util.pattern.PathPatternParser;
import reactor.core.publisher.Mono;

import java.util.List;

//Order注解是用来指定过滤器的执行顺序的，数字越小，优先级越高。
//Filter过滤器，Interceptor拦截器，Aspect切面,都通过Order注解来指定执行顺序。
@Order(1)
@Component
@RequiredArgsConstructor
@Slf4j
public class LoginFilter implements WebFilter {
    //PathPatternParser().parse是 Spring 框架（特别是在 WebFlux 以及 Spring MVC 相关环境中）中用于处理 URL 路径匹配的一种机制。
    // 它的主要作用是将一个字符串形式的路径表达式解析成一个可用于后续路径匹配操作的对象，以便准确判断传入的请求路径是否符合特定的规则或模式。
    private final PathPattern path = new PathPatternParser().parse("/api/**");
    private final List<PathPattern> excludes=List.of(new PathPatternParser().parse("/api/login"));
    private final JWTComponent jwtComponent;

    //这个responseHelper是用来处理响应的，它的作用是将一个错误信息对象转换为 JSON 格式的字符串，然后将这个 JSON 字符串转换为字节数组，以便后续作为响应体发送出去。
    private final ResponseHelper responseHelper;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request=exchange.getRequest();
        /*
        概述
PathPattern类型的matches方法是 Spring 框架（特别是在 WebFlux 和 Spring MVC 路由匹配场景下）用于检查一个给定的实际请求路径是否与该PathPattern对象所代表的路径模式相匹配的方法。它返回一个布尔值，true表示匹配成功，false表示匹配失败。
参数和返回值
参数：该方法通常接受一个PathContainer类型的参数，PathContainer是 Spring 中用于表示路径信息的一种抽象。在实际使用场景中，这个参数往往是通过ServerWebExchange对象获取的请求路径，例如exchange.getRequest().getPath()，其中exchange是ServerWebExchange实例。
返回值：如前面所述，返回一个boolean值，用于指示是否匹配成功。
.pathWithinApplication()方法是用于获取请求路径中相对于应用根路径的部分，即去掉了应用根路径之后的部分。例如，如果请求路径是 /api/user/1001，而应用根路径是 /api，那么调用pathWithinApplication()方法后，返回的就是 /user/1001。
         */
        for(PathPattern p:excludes){
            if(p.matches(request.getPath().pathWithinApplication())){
                return chain.filter(exchange);
            }
        }
        // 非过滤路径，按异常处理
        if (!path.matches(request.getPath().pathWithinApplication())) {
            return responseHelper.response(Code.BAD_REQUEST, exchange);
        }

        // 获取请求头中的token
        String token = request.getHeaders().getFirst("token");

        // 如果token为空，返回未授权
        if (token == null) {
            return responseHelper.response(Code.UNAUTHORIZED, exchange);
        }

        return jwtComponent.decode(token)
                .flatMap(d->{
                    exchange.getAttributes()
                            .put("uid",d.getClaim("uid").asString());
                    exchange.getAttributes()
                            .put("role",d.getClaim("role").asString());
                    return chain.filter(exchange);
                })
                .onErrorResume(e -> responseHelper.response(((XException) e).getCode(), exchange));

    }
}
