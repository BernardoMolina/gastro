package br.csi.clinica_gastro.service;


import br.csi.clinica_gastro.model.medico.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {

    private final MedicoRepository repository;

    public MedicoService(MedicoRepository repository) {
        this.repository = repository;
    }

    public void salvar(Medico medico) {
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
