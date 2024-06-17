package br.csi.clinica_gastro.service;


import br.csi.clinica_gastro.model.paciente.Paciente;
import br.csi.clinica_gastro.model.paciente.PacienteDTO;
import br.csi.clinica_gastro.model.paciente.SalvarPacienteDTO;
import br.csi.clinica_gastro.model.usuario.SalvarUsuarioDTO;
import br.csi.clinica_gastro.model.usuario.Usuario;
import br.csi.clinica_gastro.model.usuario.UsuarioDTO;
import br.csi.clinica_gastro.model.usuario.UsuarioRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    public UsuarioService (UsuarioRepository repository){
        this.repository = repository;
    }
    public void salvar(Usuario usuario){
        this.repository.save(usuario);
    }

    public Usuario findById(int idus){
        return  this.repository.findById(idus).get();
    }
    public SalvarUsuarioDTO salvarUsuarioDTO(int idus){return this.repository.salvarUsuario(idus);}


    public void atualizar(Usuario usuario){
        Usuario u = this.repository.getReferenceById(usuario.getIdus());
        u.setNome_completo(usuario.getNome_completo());
        u.setCpf(usuario.getCpf());
        u.setEmail(usuario.getEmail());
        u.setPermissao(usuario.getPermissao());
        u.setStatus(usuario.getStatus());
        u.setSenha(usuario.getSenha());
        u.setTelefone(usuario.getTelefone());
    }

    public void excluir(int idus){
        this.repository.deleteById(idus);
    }

    public List<UsuarioDTO> listaUsuariosDTO(){
        return  this.repository.findAllDTO();
    }

}
