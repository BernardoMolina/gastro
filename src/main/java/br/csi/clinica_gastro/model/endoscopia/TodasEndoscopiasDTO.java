package br.csi.clinica_gastro.model.endoscopia;

public interface TodasEndoscopiasDTO {


    Integer getIdpaciente();
    Integer getIdmedico();
    Integer getIdend();
    Integer getIdexame();
    String getEsofago();
    String getDuodeno();
    String getConclusao();
    String getDescricao();
    String getDataexame();
    String getDataa();
    String getNomemedico();
    String getNomepaciente();

}
