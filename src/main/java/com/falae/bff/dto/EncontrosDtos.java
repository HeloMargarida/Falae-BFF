package com.falae.bff.dto;

import java.util.List;

public class EncontrosDtos {

    // DTO de entrada para criar/atualizar encontro
    public static class EncontroInput {
        public String localId;
        public String dataHora;
        public int minimoPreferenciasIguais;
    }

    // DTO para matching
    public static class MatchingInput {
        public String localId;
        public String dataHora;
        public int minimoPreferenciasIguais;
        public int numeroParticipantes;
    }

    // DTO de saída (o backend envia esse formato)
    public static class EncontroOutput {
        public String id;
        public String localId;
        public String dataHora;
        public int minimoPreferenciasIguais;
        public String status;
        public String dataCriacao;

        // Lista de IDs dos participantes
        public List<String> participantesIds;
    }
}
