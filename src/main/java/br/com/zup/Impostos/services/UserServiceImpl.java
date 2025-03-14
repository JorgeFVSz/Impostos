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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public RegisterUserResponseDTO registerUser(RegisterUserRequestDTO registerUserRequestDTO) {
        if (userRepository.existsByUsername(registerUserRequestDTO.getUserName())) {
            throw new UserAlreadyExistsException("Já existe usuário com nome " + registerUserRequestDTO.getUserName() + " cadastrado");
        }

        String cryptPassword = bCryptPasswordEncoder.encode(registerUserRequestDTO.getPassword());
        registerUserRequestDTO.setPassword(cryptPassword);

        User user = new User();
        user.setUsername(registerUserRequestDTO.getUserName());
        user.setPassword(registerUserRequestDTO.getPassword());
        Role role = roleRepository.findByName(registerUserRequestDTO.getRole().name())
                .orElseThrow(() -> new RoleNotFoundException("Role não cadastrado na base."));
        user.setRole(role);

        User userSaved = userRepository.save(user);

        RegisterUserResponseDTO registerUserResponseDTO = new RegisterUserResponseDTO();
        registerUserResponseDTO.setId(userSaved.getUuid());
        registerUserResponseDTO.setUserName(userSaved.getUsername());
        registerUserResponseDTO.setRole(RoleEnum.fromString(userSaved.getRole().getName()));

        return registerUserResponseDTO;
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequestDTO.getUserName(),
                loginRequestDTO.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setToken(token);
        return loginResponseDTO;
    }
}
