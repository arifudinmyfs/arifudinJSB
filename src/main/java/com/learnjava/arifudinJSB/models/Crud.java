package com.learnjava.arifudinJSB.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "cruds")
public class Crud {
    @Id
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @PrePersist
    public void generateId() {
        this.id = UUID.randomUUID();
    }
}
