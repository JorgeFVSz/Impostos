package br.com.zup.Impostos.dtos;

import jakarta.validation.constraints.NotBlank;

public class LoginRequestDTO {

    @NotBlank(message = "Por favor, preencha o nome de usuário")
    private String userName;
    @NotBlank(message = "Por favor, preencha a senha")
    private String password;

    public LoginRequestDTO() {
    }

    public LoginRequestDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
