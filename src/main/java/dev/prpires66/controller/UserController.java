package dev.prpires66.controller;

import dev.prpires66.domain.model.Funcionario;
import dev.prpires66.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> findById(@PathVariable Long id) {
        var user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<Funcionario> create(@RequestBody Funcionario userToCreate) {
        var userCreated = userService.create(userToCreate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userCreated.getIdFuncionario())
                .toUri();
        return ResponseEntity.created(location).body(userCreated);
    }
}
