package com.example.auth_api.domain.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    private String name;


    public enum Values {
        ADMIN(1L),
        BASIC(2L);

        long roleId;

        Values(long roleId) {this.roleId = roleId;}
    }

}
