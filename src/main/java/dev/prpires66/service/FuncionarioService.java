package dev.prpires66.service;

import dev.prpires66.domain.model.Funcionario;

import java.util.List;

public interface FuncionarioService   {
    Funcionario findById(Long id);
    List<Funcionario> findAll();
    Funcionario create(Funcionario funcionarioToCreate);
    void delete(Long id);
    Funcionario update(Long id, Funcionario funcionarioToUpdate);
}
