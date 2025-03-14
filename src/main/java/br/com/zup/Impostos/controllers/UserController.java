package br.com.zup.Impostos.controllers;

import br.com.zup.Impostos.dtos.LoginRequestDTO;
import br.com.zup.Impostos.dtos.LoginResponseDTO;
import br.com.zup.Impostos.dtos.RegisterUserRequestDTO;
import br.com.zup.Impostos.dtos.RegisterUserResponseDTO;
import br.com.zup.Impostos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponseDTO> registerUser(@RequestBody RegisterUserRequestDTO registerUserRequestDTO) {
        RegisterUserResponseDTO registerUserResponseDTO = userService.registerUser(registerUserRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUserResponseDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        LoginResponseDTO loginResponseDTO = userService.login(loginRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(loginResponseDTO);
    }
}
