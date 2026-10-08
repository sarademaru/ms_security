package com.uc.ms_security.repository;

import com.uc.ms_security.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//para que jpa pueda funcionar
@Repository
public interface UserRepository extends JpaRepository<User, Long> { //se encarga de jugar con la bd mediande jpa

    boolean existsByEmail(String email); //podemos usar jpa el cual de forma automatica va a ser por debajo traducciones a consultas sql

    boolean existsByEmailAndIdNot(String email, Long id);

    @EntityGraph(attributePaths = {"profile"}) // va a hacer un join con la tabla profile y va a traer la información del perfil del usuario
    Optional<User> findWithProfileById(Long id);

    @EntityGraph(attributePaths = {"userRoles", "userRoles.role"})//esto es un doble join para traer la información de los roles asignados a un usuario
    Optional<User> findWithRolesById(Long id);
}
