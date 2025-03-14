package br.com.zup.Impostos.dtos;

import br.com.zup.Impostos.enums.RoleEnum;

public class RegisterUserRequestDTO {

    private String userName;
    private String password;
    private RoleEnum role;

    public RegisterUserRequestDTO() {
    }

    public RegisterUserRequestDTO(String userName, String password, RoleEnum role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
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

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }
}
