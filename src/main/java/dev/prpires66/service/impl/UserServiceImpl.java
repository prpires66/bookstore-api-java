package dev.prpires66.service.impl;

import dev.prpires66.domain.model.Funcionario;
import dev.prpires66.domain.repository.UserRepository;
import dev.prpires66.service.UserService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Funcionario findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Funcionario create(Funcionario userToCreate) {
        if (userRepository.existsByIdFuncionario(userToCreate.getIdFuncionario())) {
            throw new IllegalArgumentException("This Account number already exists.");
        }
        return userRepository.save(userToCreate);
    }
}
