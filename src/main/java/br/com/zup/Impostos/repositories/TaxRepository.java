package br.com.zup.Impostos.repositories;

import br.com.zup.Impostos.models.Tax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxRepository extends JpaRepository<Tax, String> {
    boolean existsByName(String nome);
}
