package br.csi.clinica_gastro.model.colangioressonancia;

public interface TodasColangioressonanciasDTO {

    Integer getIdcol();
    Integer getIdexame();
    Integer getIdpaciente();
    String getNomepaciente();
    Integer getIdmedico();
    String getNomemedico();
    String getDataexame();
    String getDataa();
    String getDiagnostico();

    String getTecnica();

    String getObservacao();
}
