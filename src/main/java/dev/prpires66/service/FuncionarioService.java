package dev.prpires66.service;

import dev.prpires66.domain.model.Funcionario;
import dev.prpires66.domain.model.LoginResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FuncionarioService   {
    Funcionario findById(Long id);
    Funcionario findByEmail(String email);
    List<Funcionario> findAll();
    Funcionario create(Funcionario funcionarioToCreate);
    void delete(Long id);
    Funcionario update(Long id, Funcionario funcionarioToUpdate);
    ResponseEntity<LoginResponse> login(Funcionario FuncionarioToLogin);
}
