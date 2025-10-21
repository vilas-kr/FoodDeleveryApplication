package com.vilas.hungerHub.entity;

import com.vilas.hungerHub.entity.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id", columnDefinition = "CHAR(9)")
    @Size(min = 9, max = 9, message = "user id should be 9 characters")
    private String userId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "user_name", length = 30, unique = true, nullable = false)
    private String userName;

    @Column(name = "password", length = 30, nullable = false)
    private String password;

    @Column(name = "email", length = 50, unique = true, nullable = false)
    private String email;

    @Column(name = "phone_number", columnDefinition = "CHAR(10)", nullable = false)
    @Size(min = 10, max = 10, message = "Phone number should be 10 digits")
    private String phoneNumber;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "create_date", nullable = false)
    @CreationTimestamp
    private LocalDate createDate;

    @Column(name = "last_login_date")
    private LocalDate lastLoginDate;

}
