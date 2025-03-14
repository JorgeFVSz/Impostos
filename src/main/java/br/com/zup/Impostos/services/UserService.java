package br.com.zup.Impostos.services;

import br.com.zup.Impostos.dtos.LoginRequestDTO;
import br.com.zup.Impostos.dtos.LoginResponseDTO;
import br.com.zup.Impostos.dtos.RegisterUserRequestDTO;
import br.com.zup.Impostos.dtos.RegisterUserResponseDTO;

public interface UserService {
    RegisterUserResponseDTO registerUser(RegisterUserRequestDTO registerUserRequestDTO);
    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
}
