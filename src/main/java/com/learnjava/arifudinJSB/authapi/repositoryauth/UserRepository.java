package com.learnjava.arifudinJSB.authapi.repositoryauth;

import com.learnjava.arifudinJSB.authapi.modelsauth.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
    Optional<User> findByEmail(String email);
}
