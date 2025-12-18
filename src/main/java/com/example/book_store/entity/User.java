package com.example.book_store.entity;

import edu.umd.cs.findbugs.annotations.Nullable;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "USERS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@SQLDelete(sql = "update users set deleted_at = now() where id = ?")
@SQLRestriction("deleted_at is null")
public class User extends BaseEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;

    @Column(name = "USERNAME", nullable = false, unique = true, length = 16)
    String username;

    @Column(name = "EMAIL", nullable = false, unique = true, length = 25)
    String email;

    @Column(name = "FULL_NAME", nullable = false, length = 20)
    String fullName;

    @Column(name = "DATE_OF_BIRTH", nullable = false)
    LocalDate dateOfBirth;

    @Column(name = "PASSWORD_HASH", nullable = false, length = 16)
    String passwordHash;

    @Column(name = "IS_BANNED")
    Boolean isBanned = false;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID")
    )
    List<Role> roles;


    @Override
    @NonNull
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public @Nullable String getPassword() {
        return passwordHash;
    }
}