package br.com.zup.Impostos.services;

import br.com.zup.Impostos.enums.RoleEnum;
import br.com.zup.Impostos.models.Role;
import br.com.zup.Impostos.repositories.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolesDataBaseInitializer {

    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    public void init() {
        createRoleIfNotFound("ROLE_ADMIN");
        createRoleIfNotFound("ROLE_USER");
    }

    private void createRoleIfNotFound(String roleName) {
        if (!roleRepository.existsByName(roleName)) {

            Role role = new Role();
            role.setName(roleName);

            roleRepository.save(role);
        }
    }
}
