package br.com.ada.programacaowebi.model;

public enum TipoVeiculo {
    SUV("SUV"),
    SEDAN("Sedan"),
    HATCH("Hatch"),
    ESPORTIVO("Esportivo");

    private final String tipoVeiculo;
    TipoVeiculo(String tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public String getTipoVeiculo(){
        return this.tipoVeiculo;
    }
}
