package br.com.zup.Impostos.models;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "users")
public class User {
    @Id
    @UuidGenerator
    private String uuid;
    private String username;
    private String password;
}
