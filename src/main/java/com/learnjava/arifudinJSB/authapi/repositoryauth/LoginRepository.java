package com.learnjava.arifudinJSB.authapi.repositoryauth;

import com.learnjava.arifudinJSB.authapi.modelsauth.Login;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LoginRepository extends CrudRepository<Login, UUID> {
    Optional<Login> findByEmail(String email);
}
