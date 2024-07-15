package br.csi.clinica_gastro.model.usuario;

public interface UsuarioDTO {


    String getNome_completo();

    String getEmail();

    String getCpf();

    String getTelefone();

    String getSenha();

    String getPermissao();

    String getStatus();
}
