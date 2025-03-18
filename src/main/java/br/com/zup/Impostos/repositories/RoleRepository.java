package br.com.zup.Impostos.repositories;

import br.com.zup.Impostos.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {
    boolean existsByName(String name);
    Optional<Role> findByName(String name);
}
