package dev.prpires66.domain.model;

public class LoginResponse {

    private boolean isAuthenticated;
    private String token;
    private String nome;
    private String message;

    public LoginResponse(boolean isAuthenticated, String token, String nome, String message) {
        this.isAuthenticated = isAuthenticated;
        this.token = token;
        this.nome = nome;
        this.message = message;
    }

    // Getters e Setters
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
