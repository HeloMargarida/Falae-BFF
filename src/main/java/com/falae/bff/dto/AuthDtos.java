package com.falae.bff.dto;

public class AuthDtos {
    public static class LoginRequest {
        public String email;
        public String senha;
    }
    public static class RecuperarSenhaRequest {
        public String email;
    }
}
