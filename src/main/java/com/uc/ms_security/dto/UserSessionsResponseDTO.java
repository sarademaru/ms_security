package com.uc.ms_security.dto;

import lombok.Value;

import java.util.List;

@Value
public class UserSessionsResponseDTO {

    Long id;

    String name;

    String email;

    List<SessionResponseDTO> sessions; //el dto de salida es para mostrar la información del usuario y sus sesiones.
}

//el dto de entrada es para validar y el de salida es para mostrar la información que queremos mostrar, 
// en este caso el dto de salida es para mostrar la información del usuario y sus sesiones.