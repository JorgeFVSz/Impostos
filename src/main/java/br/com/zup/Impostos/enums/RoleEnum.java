package br.com.zup.Impostos.enums;

public enum RoleEnum {
    ROLE_ADMIN,
    ROLE_USER;

    public static RoleEnum fromString(String string) {
        for (RoleEnum role : RoleEnum.values()) {
            if (role.name().equals(string)) {
                return role;
            }
        }
        return null;
    }
}
