package dev.prpires66.service.impl;

import dev.prpires66.domain.model.Funcionario;
import dev.prpires66.domain.repository.UserRepository;
import dev.prpires66.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<Funcionario> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Funcionario create(Funcionario userToCreate) {
        if (userRepository.existsByIdFuncionario(userToCreate.getIdFuncionario())) {
            throw new IllegalArgumentException("This Account number already exists.");
        }
        return userRepository.save(userToCreate);
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(this.findById(id));
    }

    @Override
    public Funcionario update(Long id, Funcionario userToUpdate) {
        Funcionario dbFuncionario = this.findById(id);
        dbFuncionario.setEmail(userToUpdate.getEmail());
        dbFuncionario.setNome(userToUpdate.getNome());
        dbFuncionario.setSenha(userToUpdate.getSenha());
        
        return this.userRepository.save(dbFuncionario);
    }
}
