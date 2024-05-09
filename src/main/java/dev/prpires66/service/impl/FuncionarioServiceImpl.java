package dev.prpires66.service.impl;

import dev.prpires66.domain.model.Funcionario;
import dev.prpires66.domain.repository.FuncionarioRepository;
import dev.prpires66.service.FuncionarioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioServiceImpl(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    public Funcionario findById(Long id) {
        return funcionarioRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll();
    }

    @Override
    public Funcionario create(Funcionario funcionarioToCreate) {
        if (funcionarioRepository.existsByIdFuncionario(funcionarioToCreate.getIdFuncionario())) {
            throw new IllegalArgumentException("This Account number already exists.");
        }
        return funcionarioRepository.save(funcionarioToCreate);
    }

    @Override
    public void delete(Long id) {
        funcionarioRepository.delete(this.findById(id));
    }

    @Override
    public Funcionario update(Long id, Funcionario funcionarioToUpdate) {
        Funcionario dbFuncionario = this.findById(id);
        dbFuncionario.setEmail(funcionarioToUpdate.getEmail());
        dbFuncionario.setNome(funcionarioToUpdate.getNome());
        dbFuncionario.setSenha(funcionarioToUpdate.getSenha());
        
        return this.funcionarioRepository.save(dbFuncionario);
    }
}
