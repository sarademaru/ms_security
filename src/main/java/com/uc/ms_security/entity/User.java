package com.uc.ms_security.entity;

import jakarta.persistence.*; //t odo lo que tiene que ver con validaciones
import lombok.Getter;
import lombok.NoArgsConstructor; //uno no crea los constructores automaticamente lo crea
import lombok.Setter; // no tenemos que hacer setters ni getters
import java.util.ArrayList;
import java.util.List;

@Entity //eso va a estar en una tabla en la bse de datos
@Table(name = "users")  //como se va a crear la tabla de esta entidad en base de datos
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(  //los objetos se termiann convirtiendo en un campo en la base de datos
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            nullable = false,
            length = 100
    )
    private String name;

    @Column(
            nullable = false,
            unique = true,
            length = 150
    )
    private String email;

    @Column(
            nullable = false
    )
    private String password;

    @OneToOne(
            mappedBy = "user",  //la referencia de la tabla profile es user, y el mappedBy es para decirle que la referencia de la tabla profile es user
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private Profile profile;

        @OneToMany(
                        mappedBy = "user",
                        cascade = CascadeType.ALL,
                        orphanRemoval = true,
                        fetch = FetchType.LAZY
        )
        private List<UserRole> userRoles = new ArrayList<>();
}
