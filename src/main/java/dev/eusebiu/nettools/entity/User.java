package dev.eusebiu.nettools.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "\"user\"")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "email")
    private String email;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "email_verified")
    private Boolean emailVerified;

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name  = "user_role",
            joinColumns = @JoinColumn(name = "user_id_fk"),
            inverseJoinColumns = @JoinColumn(name = "role_id_fk")
    )
    private Set<Role> roles = new HashSet<>();

    public void markEmailVerified() {
        this.emailVerified = true;
    }

}
