package com.falae.bff.dto;

public class UsuarioDtos {

    // Classe de entrada (vinda do frontend → enviada pro backend)
    public static class UsuarioInput {
        public String nome;
        public String cpf;
        public String dataNascimento; // ← importante: String (backend ASP.NET converte automaticamente)
        public String cidade;
        public String email;
        public String senha;
    }

    // Classe de saída (resposta do backend → enviada pro frontend)
    public static class UsuarioOutput {
        public String id;
        public String nome;
        public String cpf;
        public String dataNascimento;
        public String cidade;
        public String email;
        public boolean ativo;
    }
}
