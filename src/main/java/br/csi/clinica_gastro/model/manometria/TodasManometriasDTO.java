package br.csi.clinica_gastro.model.manometria;

public interface TodasManometriasDTO {

    Integer getIdman();
    Integer getIdexame();
    Integer getIdmedico();
    String getNomepaciente();
    Integer getIdpaciente();
    String getNomemedico();
    String getDataexame();
    String getDataa();
    String getSumario();

    String getConclusao();

    String getResultados();

}
