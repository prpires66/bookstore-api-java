package dev.prpires66.service;

import dev.prpires66.domain.model.Funcionario;

public interface UserService   {
    Funcionario findById(Long id);

    Funcionario create(Funcionario userToCreate);
}
