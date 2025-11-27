package com.falae.bff.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EncontrosDtos {

    // DTO de entrada para criar/atualizar encontro
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class EncontroInput {
        @JsonProperty("localId")
        public String localId;

        @JsonProperty("dataHora")
        public String dataHora;

        @JsonProperty("minimoPreferenciasIguais")
        public Integer minimoPreferenciasIguais;
    }

    // DTO para matching
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class MatchingInput {
        @JsonProperty("localId")
        public String localId;

        @JsonProperty("dataHora")
        public String dataHora;

        @JsonProperty("minimoPreferenciasIguais")
        public Integer minimoPreferenciasIguais;

        @JsonProperty("numeroParticipantes")
        public Integer numeroParticipantes;
    }

    // DTO de saída (o backend envia esse formato)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class EncontroOutput {

        @JsonProperty("id")
        public String id;

        @JsonProperty("localId")
        public String localId;

        @JsonProperty("dataHora")
        public String dataHora;   // pode vir null

        @JsonProperty("minimoPreferenciasIguais")
        public Integer minimoPreferenciasIguais;

        @JsonProperty("status")
        public String status;     // pode vir null

        @JsonProperty("dataCriacao")
        public String dataCriacao; // pode vir null

        // Lista de IDs dos participantes (pode estar vazia)
        @JsonProperty("participantesIds")
        public List<String> participantesIds;
    }
}
