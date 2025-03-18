package br.com.zup.Impostos.services;

import br.com.zup.Impostos.dtos.LoginRequestDTO;
import br.com.zup.Impostos.dtos.LoginResponseDTO;
import br.com.zup.Impostos.dtos.RegisterUserRequestDTO;
import br.com.zup.Impostos.dtos.RegisterUserResponseDTO;
import br.com.zup.Impostos.enums.RoleEnum;
import br.com.zup.Impostos.exceptions.RoleNotFoundException;
import br.com.zup.Impostos.exceptions.UserAlreadyExistsException;
import br.com.zup.Impostos.infra.jwt.JwtTokenProvider;
import br.com.zup.Impostos.models.Role;
import br.com.zup.Impostos.models.User;
import br.com.zup.Impostos.repositories.RoleRepository;
import br.com.zup.Impostos.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        roleRepository = mock(RoleRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        authenticationManager = mock(AuthenticationManager.class);
        jwtTokenProvider = mock(JwtTokenProvider.class);
        userService = new UserServiceImpl(userRepository, roleRepository, passwordEncoder, authenticationManager, jwtTokenProvider);
    }

    @Test
    void shouldRegisterUserSuccessfully() {

        RegisterUserRequestDTO requestDTO = new RegisterUserRequestDTO("testUser", "password", RoleEnum.ROLE_ADMIN);
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        User user = new User();
        user.setUuid(UUID.randomUUID().toString());
        user.setUsername("testUser");
        user.setRole(role);

        when(userRepository.existsByUsername("testUser")).thenReturn(false);
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(roleRepository.findByName("ROLE_ADMIN")).thenReturn(Optional.of(role));
        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        RegisterUserResponseDTO response = userService.registerUser(requestDTO);

        assertEquals(user.getUuid(), response.getId());
        assertEquals("testUser", response.getUserName());
        assertEquals(RoleEnum.ROLE_ADMIN, response.getRole());
        verify(userRepository, times(1)).existsByUsername("testUser");
        verify(passwordEncoder, times(1)).encode("password");
        verify(roleRepository, times(1)).findByName("ROLE_ADMIN");
        verify(userRepository, times(1)).save(Mockito.any(User.class));
    }

    @Test
    void shouldThrowExceptionWhenUserAlreadyExists() {

        RegisterUserRequestDTO requestDTO = new RegisterUserRequestDTO("testUser", "password", RoleEnum.ROLE_ADMIN);
        when(userRepository.existsByUsername("testUser")).thenReturn(true);

        UserAlreadyExistsException exception = assertThrows(UserAlreadyExistsException.class, () -> userService.registerUser(requestDTO));
        assertEquals("Já existe usuário com nome testUser cadastrado", exception.getMessage());
        verify(userRepository, times(1)).existsByUsername("testUser");
        verifyNoInteractions(passwordEncoder, roleRepository);
    }

    @Test
    void shouldThrowExceptionWhenRoleNotFound() {

        RegisterUserRequestDTO requestDTO = new RegisterUserRequestDTO("testUser", "password", RoleEnum.ROLE_ADMIN);
        when(userRepository.existsByUsername("testUser")).thenReturn(false);
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(roleRepository.findByName("ROLE_ADMIN")).thenReturn(Optional.empty());

        RoleNotFoundException exception = assertThrows(RoleNotFoundException.class, () -> userService.registerUser(requestDTO));
        assertEquals("Role não cadastrado na base.", exception.getMessage());
        verify(userRepository, times(1)).existsByUsername("testUser");
        verify(passwordEncoder, times(1)).encode("password");
        verify(roleRepository, times(1)).findByName("ROLE_ADMIN");
    }

    @Test
    void shouldLoginSuccessfully() {

        LoginRequestDTO requestDTO = new LoginRequestDTO("testUser", "password");
        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(jwtTokenProvider.generateToken(authentication)).thenReturn("mocked-jwt-token");

        LoginResponseDTO response = userService.login(requestDTO);

        assertEquals("mocked-jwt-token", response.getToken());
        verify(authenticationManager, times(1)).authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class));
        verify(jwtTokenProvider, times(1)).generateToken(authentication);
    }

    @Test
    void shouldThrowExceptionWhenLoginFails() {

        LoginRequestDTO requestDTO = new LoginRequestDTO("testUser", "wrongPassword");
        when(authenticationManager.authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new RuntimeException("Credenciais inválidas."));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.login(requestDTO));
        assertEquals("Credenciais inválidas.", exception.getMessage());
        verify(authenticationManager, times(1)).authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class));
        verifyNoInteractions(jwtTokenProvider);
    }
}