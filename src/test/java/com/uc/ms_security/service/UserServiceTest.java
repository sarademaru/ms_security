package com.uc.ms_security.service;

import com.uc.ms_security.entity.User;
import com.uc.ms_security.exception.ApplicationException;
import com.uc.ms_security.exception.ErrorCase;
import com.uc.ms_security.mapper.UserMapper;
import com.uc.ms_security.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @Test
    void findById_whenUserDoesNotExist_throwsApplicationException() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        ApplicationException ex = assertThrows(
                ApplicationException.class,
                () -> userService.findById(99L)
        );

        assertEquals(ErrorCase.NOT_FOUND, ex.getErrorCase());
        assertEquals("Usuario no encontrado con id: 99", ex.getMessage());
    }
}
