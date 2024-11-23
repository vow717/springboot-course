# webflux异步非阻塞
## 创建模块
创建方法类似,maven,java 21
然后依赖选择lombok和Web下面的spring reactive web
然后再把没有用的删了再在pom.xml添加其他依赖
(参照老师的：https://github.com/bwhyman/springboot-course/tree/master/webflux-r2dbc-examples)

## 理解背压
https://www.baeldung.com/spring-webflux-backpressure
## 理解Flux & Mono
https://zhuanlan.zhihu.com/p/378136040
## 知道webflux reactor netty 是什么



# Mono,Flux常用函数
https://blog.csdn.net/lz710117239/article/details/93777692
## 一、Mono.just()

### 含义
`Mono.just()`用于创建一个只包含一个元素的`Mono`。`Mono`是一种表示包含0个或1个元素的异步序列的类型，类似于Java 8中的`Optional`，但用于异步操作。

### 示例
```java
import reactor.core.publisher.Mono;

public class UserService {
    public Mono<User> getUserById(String id) {
        User user = userRepository.findById(id);
        if (user!= null) {
            return Mono.just(user);
        } else {
            return Mono.empty();
        }
    }
}
```
## 二、Mono.switchIfEmpty()

### 含义
`Mono.switchIfEmpty()`用于在`Mono`为空（即没有包含任何元素）时进行切换操作。它接受一个`Mono`作为参数，当原始`Mono`为空时，就会订阅并返回这个作为参数的`Mono`。

### 示例
```java
import reactor.core.publisher.Mono;

public class UserService {
    public Mono<User> getUserById(String id) {
        return Mono.just(userRepository.findById(id))
              .switchIfEmpty(Mono.just(new User("defaultId", "Default User", 0)))
              .map(user -> {
                    // 可以在这里对用户信息进行一些处理
                    return user;
                });
    }
}
```
## 三、Mono.map()

### 含义
`Mono.map()`用于对`Mono`中的元素进行转换操作。它接受一个函数作为参数，这个函数会应用到`Mono`中的元素上。如果`Mono`为空，`map`操作会被跳过。

### 示例
```java
import reactor.core.publisher.Mono;

public class UserService {
    public Mono<String> getUserNameById(String id) {
        return getUserById(id)
              .map(user -> user.getName());
    }
}
```
## 四、Mono.flatMap()

### 含义
`Mono.flatMap()`和`map`类似，也用于对`Mono`中的元素进行转换。但是`flatMap`更加强大，它接受一个返回`Mono（或Publisher类型）`的函数作为参数。当`flatMap`应用这个函数时，它会将返回的`Mono（或Publisher）`“扁平化” 处理，即直接返回内部`Mono（或Publisher）`中的元素，而不是返回一个嵌套的`Mono（或Publisher）`。`
### 示例
```java
import reactor.core.publisher.Mono;

public class UserService {
    public Mono<Order> getOrdersForUserById(String id) {
        return getUserById(id)
                .flatMap(user -> getOrdersForUser(user.getId()));
    }
}
```
## 五、Mono.filter()

### 含义
`Mono.filter()`用于对Mono中的元素进行过滤操作。它接受一个`Predicate`函数作为参数，这个函数会应用到`Mono`中的元素上。如果元素满足`Predicate`函数的条件，`Mono`保持不变；如果不满足，`Mono`会变为空（`Mono.empty()`）。
### 示例
```java
import reactor.core.publisher.Mono;

public class UserService {
    public Mono<User> getAdultUserById(String id) {
        return getUserById(id)
                .filter(user -> user.getAge() > 18);
    }
}
```