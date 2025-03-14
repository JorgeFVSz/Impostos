package br.com.zup.Impostos.dtos;

import br.com.zup.Impostos.models.Role;

public class UserDTO {

    private String uuid;
    private String username;
    private String password;
    private Role role;

    public UserDTO() {
    }

    public UserDTO(String uuid, String userName, String password, Role role) {
        this.uuid = uuid;
        this.username = userName;
        this.password = password;
        this.role = role;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
