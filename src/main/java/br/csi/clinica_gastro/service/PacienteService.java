package br.csi.clinica_gastro.service;


import br.csi.clinica_gastro.model.paciente.*;
import br.csi.clinica_gastro.model.usuario.Usuario;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    private PacienteRepository repository;
    public PacienteService(PacienteRepository repository){
        this.repository = repository;
    }

    public void salvar(Paciente paciente){
        this.repository.save(paciente);
    }

    public Paciente findById(int idpac){
        return  this.repository.findById(idpac).get();
    }
    public SalvarPacienteDTO salvarPacienteDTO(int id){return this.repository.salvarPaciente(id);}



    public void atualizar(Paciente paciente){
        Paciente p = this.repository.getReferenceById(paciente.getIdpac());
        p.setFuncao(paciente.getFuncao());
        p.setSangue(paciente.getSangue());
        p.setPlano_de_saude(paciente.getPlano_de_saude());
        p.setMed_uso_cont(paciente.getMed_uso_cont());
        p.setPlano_de_saude(paciente.getPlano_de_saude());
        p.setCondicao_cronica(paciente.getCondicao_cronica());
        p.setDoenca_anterior(paciente.getDoenca_anterior());
        p.setDoenca_infec(paciente.getDoenca_infec());
        p.setCirurgia(paciente.getCirurgia());
        p.setData_de_nasc(paciente.getData_de_nasc());
        p.setAlergia(paciente.getAlergia());
        p.setHistorico_familiar(paciente.getHistorico_familiar());
        p.setSexo(paciente.getSexo());
        p.setImunizacao(paciente.getImunizacao());
    }

    public void excluir(int idpac){
        this.repository.deleteById(idpac);
    }

    public List<PacienteDTO> listaPacientesDTO(){
        return  this.repository.findAllDTO();
    }

    public List<InfoTodosPacientesDTO> listarTodosPacientesDTO(){
        return  this.repository.findAllPacientesDTO();
    }

}
