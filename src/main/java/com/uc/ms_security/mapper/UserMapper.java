package com.uc.ms_security.mapper;

import com.uc.ms_security.dto.CreateUserDTO;
import com.uc.ms_security.dto.UpdateUserDTO;
import com.uc.ms_security.dto.UserResponseDTO;
import com.uc.ms_security.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
//el mapper sirve para armar y desarmar
@Component
public class UserMapper {
//entran los datos desarmados, y lo que hace es crear el usuario para poder manipular la tabla
    public User toEntity(CreateUserDTO dto) {
        User user = new User();

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        return user;
    }

    public void updateEntity(UpdateUserDTO dto, User user) {
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());

        if (dto.getPassword() != null) {
            user.setPassword(dto.getPassword());
        }
    }

    public UserResponseDTO toResponseDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
//cree una nueva lista de usuarios pero que no tenga la contaseña
    public List<UserResponseDTO> toResponseDTOList(List<User> users) {
        return users.stream()
                .map(this::toResponseDTO)
                .toList();
    }
}