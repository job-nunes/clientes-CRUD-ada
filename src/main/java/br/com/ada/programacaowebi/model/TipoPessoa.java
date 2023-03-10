package br.com.ada.programacaowebi.model;

import jakarta.persistence.Enumerated;


public enum TipoPessoa {
    PESSOA_FISICA("Pessoa Física"),
    PESSOA_JURIDICA("Pessoa Júridica");

    private final String tipoPessoa;
    TipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getTipoPessoa(){
        return this.tipoPessoa;
    }
}
