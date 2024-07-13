package br.csi.clinica_gastro.model.usuario;

public record DadosUsuario(int idus,String email,String permissao) {

    public DadosUsuario(Usuario usuario){
        this(usuario.getIdus(), usuario.getEmail(),
                usuario.getPermissao());
    }
}