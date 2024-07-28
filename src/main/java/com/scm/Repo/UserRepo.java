package com.scm.Repo;


import com.scm.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);

    Optional<User> findByEmailToken(String id);

     boolean existsByEmail(String email);

     boolean existsByEmailAndPassword(String email, String password);

}