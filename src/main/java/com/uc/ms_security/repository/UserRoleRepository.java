package com.uc.ms_security.repository;

import com.uc.ms_security.entity.UserRole;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    boolean existsByUserIdAndRoleId(Long userId, Long roleId);

    @EntityGraph(attributePaths = {"user", "role"}) // Este es el entity graph para cargar las entidades relacionadas, cargar relaciones de usuario y rol al buscar roles por usuario
    List<UserRole> findAllByUserId(Long userId);

    @EntityGraph(attributePaths = {"user", "role"})
    List<UserRole> findAllByRoleId(Long roleId);

    @Override 
    @EntityGraph(attributePaths = {"user", "role"})
    List<UserRole> findAll();

    @EntityGraph(attributePaths = {"user", "role"})
    Optional<UserRole> findByUserIdAndRoleId(Long userId, Long roleId);

    boolean existsByRoleId(Long roleId);
}