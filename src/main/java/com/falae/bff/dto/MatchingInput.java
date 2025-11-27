package com.falae.bff.dto.EncontrosDtos;

import java.time.LocalDateTime;

public class MatchingInput {

    private String localId;
    private LocalDateTime dataHora;
    private int minimoPreferenciasIguais;
    private int numeroParticipantes;

    public String getLocalId() {
        return localId;
    }

    public void setLocalId(String localId) {
        this.localId = localId;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public int getMinimoPreferenciasIguais() {
        return minimoPreferenciasIguais;
    }

    public void setMinimoPreferenciasIguais(int minimoPreferenciasIguais) {
        this.minimoPreferenciasIguais = minimoPreferenciasIguais;
    }

    public int getNumeroParticipantes() {
        return numeroParticipantes;
    }

    public void setNumeroParticipantes(int numeroParticipantes) {
        this.numeroParticipantes = numeroParticipantes;
    }
}
