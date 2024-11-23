package com.example.webfluxr2dbcexamples.repository;

import com.example.webfluxr2dbcexamples.dox.UserReactive;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<UserReactive,String> {
    @Query("""
            select * from user_reactive u where u.account=:account;
            """)
    Mono<UserReactive> findByAccount(String account);

    @Query("""
            select * from user_reactive u where u.role=:role;
            """)
    Flux<UserReactive> findByRole(String role);

    @Modifying
    @Query("""
                update user_reactive u set u.password=:password where u.id=:id;
            """)
    Mono<Void> changePasswordById(String id,String password);
    @Modifying
    @Query("""
                update user_reactive u set u.password=:password where u.account=:account;
            """)
    Mono<Void> changePasswordByAccount(String account,String password);

}
