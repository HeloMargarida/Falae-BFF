package com.falae.bff.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PreferenciasDtos {
    
    public static class PreferenciasInput {
        public String id; // Adicione se o backend retorna um ID
        public String usuarioId;
        public String horarioFavorito;
        public String tipoComidaFavorito;
        public int nivelEstresse;
        public boolean gostaViajar;
        public String preferenciaLocal;
        public String preferenciaAmbiente;
        public int importanciaEspiritualidade;
        public String posicaoPolitica;
        public String genero;
        public String preferenciaMusical;
        public String moodFilmesSeries;
        public String statusRelacionamento;
        public boolean temFilhos;
        public String preferenciaAnimal;
        public String fraseDefinicao;
        
        @JsonProperty("IdiomaPreferido")
        public String idiomaPreferido;
        
        @JsonProperty("InvestimentoEncontro")
        public String investimentoEncontro;
        
        public String gostosPessoaisJson;
    }
    
    // Classe específica para output (ou reuse a Input)
    public static class PreferenciasOutput {
        public String id; // Adicione se o backend retorna um ID
        public String usuarioId;
        public String horarioFavorito;
        public String tipoComidaFavorito;
        public int nivelEstresse;
        public boolean gostaViajar;
        public String preferenciaLocal;
        public String preferenciaAmbiente;
        public int importanciaEspiritualidade;
        public String posicaoPolitica;
        public String genero;
        public String preferenciaMusical;
        public String moodFilmesSeries;
        public String statusRelacionamento;
        public boolean temFilhos;
        public String preferenciaAnimal;
        public String fraseDefinicao;
        
        @JsonProperty("IdiomaPreferido")
        public String idiomaPreferido;
        
        @JsonProperty("InvestimentoEncontro")
        public String investimentoEncontro;
        
        public String gostosPessoaisJson;
    }
}