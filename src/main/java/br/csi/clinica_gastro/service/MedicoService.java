package br.csi.clinica_gastro.service;


import br.csi.clinica_gastro.model.medico.*;
import br.csi.clinica_gastro.model.usuario.Usuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {

    private final MedicoRepository repository;

    public MedicoService(MedicoRepository repository) {
        this.repository = repository;
    }



    public void salvar(Medico medico) {
        
        Usuario u = medico.getUsuario();
        u.setSenha(new BCryptPasswordEncoder().encode(u.getSenha()));
        this.repository.save(medico);
    }

    public MedicoDTO findById(int idmed) {
        return this.repository.findById(idmed);
    }

    public SalvarMedicoDTO salvarMedicoDTO(int id) {
        return this.repository.salvarMedico(id);
    }


    public void atualizar(Medico medico) {
        Medico p = this.repository.getReferenceById(medico.getIdmed());
        p.setRegistro(medico.getRegistro());

        // Atualizar dados do usuário
        if (medico.getUsuario() != null) {
            Usuario u = p.getUsuario();
            u.setNome_completo(medico.getUsuario().getNome_completo());
            u.setEmail(medico.getUsuario().getEmail());
            u.setCpf(medico.getUsuario().getCpf());
            u.setTelefone(medico.getUsuario().getTelefone());
            u.setPermissao(medico.getUsuario().getPermissao());
            u.setStatus(medico.getUsuario().getStatus());

            // Só atualiza a senha se foi informada
            if (medico.getUsuario().getSenha() != null && !medico.getUsuario().getSenha().isEmpty()) {
                u.setSenha(new BCryptPasswordEncoder().encode(medico.getUsuario().getSenha()));
            }
        }
    }

    public void excluir(int idmed) {
        this.repository.deleteById(idmed);
    }

    public List<MedicoDTO> listaMedicosDTO() {
        return this.repository.findAllDTO();
    }

    public List<InfoTodosMedicosDTO> listarTodosMedicosDTO() {
        return this.repository.findAllMedicosDTO();
    }

}
