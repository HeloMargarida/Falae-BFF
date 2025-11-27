package com.falae.bff.dto;

public class AuthDtos {

    // JSON esperado: { "email": "...", "senha": "..." }
    public static class LoginRequest {
        private String email;
        private String senha;

        public LoginRequest() {}
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getSenha() { return senha; }
        public void setSenha(String senha) { this.senha = senha; }
    }

    // JSON esperado: { "email": "..." }
    public static class RecuperarSenhaRequest {
        private String email;

        public RecuperarSenhaRequest() {}
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
    }
}
