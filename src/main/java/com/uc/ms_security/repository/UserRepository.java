package com.uc.ms_security.repository;

import com.uc.ms_security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//para que jpa pueda funcionar
@Repository
public interface UserRepository extends JpaRepository<User, Long> { //se encarga de jugar con la bd mediande jpa

    boolean existsByEmail(String email); //podemos usar jpa el cual de forma automatica va a ser por debajo traducciones a consultas sql

    boolean existsByEmailAndIdNot(String email, Long id);
}
