package com.example.backendexamples.respository;

import com.example.backendexamples.dox.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ListCrudRepository<User,String> {
    User findByAccount(String account);
}
