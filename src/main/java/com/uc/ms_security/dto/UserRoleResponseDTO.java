package com.uc.ms_security.dto;

import lombok.Value;

@Value
public class UserRoleResponseDTO {
    Long id;
    Long userId;
    RoleResponseDTO role;
}
//este es el dto de salida para mostrar la información de un rol asignado a un usuario,
//  contiene el id del rol asignado, el id del usuario y la información del rol