package com.falae.bff.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthDtos {

    public static class LoginRequest {

        @JsonProperty("email")
        private String email;

        @JsonProperty("senha")
        private String senha;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getSenha() {
            return senha;
        }

        public void setSenha(String senha) {
            this.senha = senha;
        }
    }

    public static class RecuperarSenhaRequest {

        @JsonProperty("email")
        private String email;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
