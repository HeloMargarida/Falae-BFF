package com.falae.bff.dto;

import java.time.LocalDate;

public class UsuarioDtos {
    public static class UsuarioInput {
        public String nome;
        public String cpf;
        public LocalDate dataNascimento;
        public String cidade;
        public String email;
        public String senha;
    }
}
