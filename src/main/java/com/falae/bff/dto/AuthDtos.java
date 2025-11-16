package com.falae.bff.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthDtos {

    public static class LoginRequest {

        @JsonProperty("Email")
        private String Email;

        @JsonProperty("Senha")
        private String Senha;

        public String getEmail() {
            return Email;
        }

        public void setEmail(String email) {
            this.Email = email;
        }

        public String getSenha() {
            return Senha;
        }

        public void setSenha(String senha) {
            this.Senha = senha;
        }
    }

    public static class RecuperarSenhaRequest {

        @JsonProperty("Email")
        private String Email;

        public String getEmail() {
            return Email;
        }

        public void setEmail(String email) {
            this.Email = email;
        }
    }
}
