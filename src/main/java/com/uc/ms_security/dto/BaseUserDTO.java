package com.uc.ms_security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
// no tiene el @entity porque no va a la base de datos
@Getter
@Setter
public abstract class BaseUserDTO {

    @NotBlank( //decorador de jakarta
            message = "El nombre es obligatorio"
    )
    @Size( //garantizar el tamaño para proteger la bd
            min = 2,
            max = 100,
            message = "El nombre debe tener entre 2 y 100 caracteres"
    )
    private String name;

    @NotBlank(
            message = "El email es obligatorio"
    )
    @Email( //decorador para validar emails
            message = "El email no tiene un formato válido"
    )
    private String email;
}
