package br.com.zup.Impostos.dtos;

import br.com.zup.Impostos.enums.RoleEnum;

public class RegisterUserResponseDTO {

    private String id;
    private String userName;
    private RoleEnum role;

    public RegisterUserResponseDTO() {
    }

    public RegisterUserResponseDTO(String id, String userName, RoleEnum role) {
        this.id = id;
        this.userName = userName;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }
}
