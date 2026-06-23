package br.csi.clinica_gastro.model.consulta;

public interface ConsultaDTO {
    int getIdcon();
    int getIdmedico();
    int getIdpaciente();
    String getObservacao();
    String getNomepaciente();
    String getNomemedico();
    String getDataa();
    String getSintoma();
    String getPrescricao();
}