package br.com.zup.Impostos.services;

import br.com.zup.Impostos.models.Role;
import br.com.zup.Impostos.models.User;
import br.com.zup.Impostos.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomUserDatailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDatailsService customUserDatailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldLoadUserByUsernameSuccessfully() {

        String username = "testUser";
        String password = "testPassword";
        String roleName = "ROLE_USER";

        Role role = new Role();
        role.setName(roleName);

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);

        when(userRepository.findByUsername(username)).thenReturn(user);

        UserDetails userDetails = customUserDatailsService.loadUserByUsername(username);

        assertEquals(username, userDetails.getUsername());
        assertEquals(password, userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals(roleName)));

        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    void shouldThrowUsernameNotFoundExceptionWhenUserNotFound() {

        String username = "nonExistentUser";
        when(userRepository.findByUsername(username)).thenReturn(null);

        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class, () -> {
            customUserDatailsService.loadUserByUsername(username);
        });

        assertEquals("Credenciais inválidas.", exception.getMessage());
        verify(userRepository, times(1)).findByUsername(username);
    }
}