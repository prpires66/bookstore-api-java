package dev.prpires66.service.impl;

import dev.prpires66.domain.model.Funcionario;
import dev.prpires66.domain.model.LoginResponse;
import dev.prpires66.domain.repository.FuncionarioRepository;
import dev.prpires66.service.FuncionarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

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
    public Funcionario findByEmail(String email) {
        return funcionarioRepository.findByEmail(email);
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

    @Override
    public ResponseEntity<LoginResponse> login(Funcionario funcionarioToLogin) {

        Optional<Funcionario> dbFuncionarioOptional = Optional.ofNullable(this.findByEmail(funcionarioToLogin.getEmail()));

        if (dbFuncionarioOptional.isPresent()) {
            Funcionario dbFuncionario = dbFuncionarioOptional.get();
            if (Objects.equals(dbFuncionario.getSenha(), funcionarioToLogin.getSenha())) {
                String generatedToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjAsIm5hbWUiOiJBZG1pbmlzdHJhZG9yIiwiZW1haWwiOiJhZG1pbkBlbWFpbC5jb20iLCJyb2xlcyI6WyJhZG1pbiIsInVzZXIiXSwiaWF0IjoxNzI0NjE5MzAyLCJleHAiOjE3MjQ2MjExMDJ9.CaS_bYV7Pl5LXvH8IK6h7HbwwaM4cAYIaF7N2Tl5k_Q";
                boolean isAuthenticated = true;
                String nome = dbFuncionario.getNome();
                String message = "Acesso autorizado!";
                LoginResponse response = new LoginResponse(isAuthenticated, generatedToken, nome, message);
                return ResponseEntity.ok(response);
            }
        }
        boolean isAuthenticated = false;
        String generatedToken = null;
        String nome = null;
        String message = "Credenciais inválidas!";
        LoginResponse response = new LoginResponse(isAuthenticated, generatedToken, nome, message);
        return ResponseEntity.ok(response);
    }
}
