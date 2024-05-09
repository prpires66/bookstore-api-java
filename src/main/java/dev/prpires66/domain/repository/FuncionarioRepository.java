package dev.prpires66.domain.repository;

import dev.prpires66.domain.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    boolean existsByIdFuncionario(Long IdFuncionario);
}
