package com.uc.ms_security.service;

import com.uc.ms_security.dto.CreateUserDTO;
import com.uc.ms_security.dto.UpdateUserDTO;
import com.uc.ms_security.dto.UserResponseDTO;
import com.uc.ms_security.entity.User;
import com.uc.ms_security.exception.ApplicationException;
import com.uc.ms_security.exception.ErrorCase;
import com.uc.ms_security.mapper.UserMapper;
import com.uc.ms_security.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor //para poder utilizar esta clase se necesita un constructor que lombok me da
public class UserService {
// esta capa de servicio debe acceder al repositorio que estamos trabajando. final es  una constante que se encarga de solo usar un objeto que va a ayudar
    //se encarga de crear solo 1 (ejemplo de clones)
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserResponseDTO create(CreateUserDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new ApplicationException(
                    ErrorCase.ALREADY_EXISTS,
                    "Ya existe un usuario con este email"
            );
        }
        User user = userMapper.toEntity(dto); // este no tiene id
        User savedUser = userRepository.save(user); //este si tiene id
        return userMapper.toResponseDTO(savedUser); //convierta el muñeco en fichas quitando contraseña
    }
    public List<UserResponseDTO> findAll() {
        List<User> users =userRepository.findAll(); //findAll salio del jpa (herencia)
        return userMapper.toResponseDTOList(users); //convierta la lista de usuarios en la lista de responsedto (sin contra)
    }
    private User findUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(
                        ErrorCase.NOT_FOUND,
                        "Usuario no encontrado con id: " + id
                ));
    }

    public UserResponseDTO findById(Long id) {
        User user = findUser(id);
        return userMapper.toResponseDTO(user);
    }

    public UserResponseDTO update(Long id, UpdateUserDTO dto) {
        User user = findUser(id); //ususario como esta actualmente en la bd
        if (userRepository.existsByEmailAndIdNot(dto.getEmail(), id)) { //validacion
            throw new ApplicationException(
                    ErrorCase.ALREADY_EXISTS,
                    "El email pertenece a otro usuario"
            );
        }
        userMapper.updateEntity(dto, user);// dto es lo que esta llegando (la actualizacion)
        User updatedUser = userRepository.save(user); //se confirma el cambio en bd pasa por referencia ESTUDIAR
        return userMapper.toResponseDTO(updatedUser);
    }
    public void delete(Long id) { //id de lo que quiere eliminar
        User user = findUser(id); //busca
        userRepository.delete(user); //elimina
    }
}