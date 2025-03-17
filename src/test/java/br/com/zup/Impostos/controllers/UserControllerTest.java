package br.com.zup.Impostos.controllers;

import br.com.zup.Impostos.dtos.LoginRequestDTO;
import br.com.zup.Impostos.dtos.LoginResponseDTO;
import br.com.zup.Impostos.dtos.RegisterUserRequestDTO;
import br.com.zup.Impostos.dtos.RegisterUserResponseDTO;
import br.com.zup.Impostos.enums.RoleEnum;
import br.com.zup.Impostos.exceptions.UserAlreadyExistsException;
import br.com.zup.Impostos.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    private UserService userService;
    private UserController userController;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        userController = new UserController(userService);
    }

    @Test
    void shouldRegisterUserSuccessfully() {
        String uuid = UUID.randomUUID().toString();

        RegisterUserRequestDTO requestDTO = new RegisterUserRequestDTO("testUser", "password", RoleEnum.ROLE_ADMIN);
        RegisterUserResponseDTO responseDTO = new RegisterUserResponseDTO(uuid, "testUser", RoleEnum.ROLE_ADMIN);
        when(userService.registerUser(requestDTO)).thenReturn(responseDTO);

        ResponseEntity<RegisterUserResponseDTO> response = userController.registerUser(requestDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(uuid, response.getBody().getId());
        assertEquals("testUser", response.getBody().getUserName());
        assertEquals(RoleEnum.ROLE_ADMIN, response.getBody().getRole());
        verify(userService, times(1)).registerUser(requestDTO);
    }

    @Test
    void shouldThrowExceptionWhenUserAlreadyExists() {

        String errorMessage = "Já existe usuário com nome testUser cadastrado";
        RegisterUserRequestDTO requestDTO = new RegisterUserRequestDTO("testUser", "password", RoleEnum.ROLE_ADMIN);

        doThrow(new UserAlreadyExistsException(errorMessage)).when(userService).registerUser(requestDTO);

        UserAlreadyExistsException exception = assertThrows(
                UserAlreadyExistsException.class,
                () -> userController.registerUser(requestDTO)
        );

        assertEquals(errorMessage, exception.getMessage());

        verify(userService, times(1)).registerUser(requestDTO);
    }

    @Test
    void shouldLoginSuccessfully() {

        LoginRequestDTO requestDTO = new LoginRequestDTO("testUser", "password");
        LoginResponseDTO responseDTO = new LoginResponseDTO("mocked-jwt-token");
        when(userService.login(requestDTO)).thenReturn(responseDTO);

        ResponseEntity<LoginResponseDTO> response = userController.login(requestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
        verify(userService, times(1)).login(requestDTO);
    }

    @Test
    void login_InvalidCredentials() {

        LoginRequestDTO requestDTO = new LoginRequestDTO("testUser", "wrongPassword");
        doThrow(new RuntimeException("Credenciais inválidas.")).when(userService).login(requestDTO);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> userController.login(requestDTO));

        assertEquals("Credenciais inválidas.", exception.getMessage());
        verify(userService, times(1)).login(requestDTO);
    }
}