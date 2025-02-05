package com.learnjava.arifudinJSB.repositorys;
import com.learnjava.arifudinJSB.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    @Query("FROM User u WHERE u.name LIKE %:name%")
    List<User> findByNameContaining(String name);
}
