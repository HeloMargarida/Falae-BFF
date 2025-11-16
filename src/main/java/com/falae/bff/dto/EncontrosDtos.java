package com.falae.bff.dto;

public class EncontrosDtos {

    public static class EncontroInput {
        public String localId;
        public String dataHora; 
        public int minimoPreferenciasIguais;
    }

    public static class MatchingInput {
        public String localId;
        public String dataHora;
        public int minimoPreferenciasIguais;
        public int numeroParticipantes;
    }
}
