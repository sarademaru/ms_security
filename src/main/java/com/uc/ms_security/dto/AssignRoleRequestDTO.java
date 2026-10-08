package com.uc.ms_security.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignRoleRequestDTO {

    @NotNull(message = "El identificador del usuario es obligatorio")
    private Long userId;

    @NotNull(message = "El identificador del rol es obligatorio")
    private Long roleId;
}

//este es el dto de entrada para asignar un rol a un usuario,
//  contiene el id del usuario y el id del rol que se desea asignar.