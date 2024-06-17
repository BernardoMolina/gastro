package br.csi.clinica_gastro.service;

import br.csi.clinica_gastro.model.consulta.Consulta;
import br.csi.clinica_gastro.model.consulta.ConsultaDTO;
import br.csi.clinica_gastro.model.consulta.ConsultaRepository;
import br.csi.clinica_gastro.model.consulta.SalvarConsultaDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {

    private ConsultaRepository repository;
    public ConsultaService(ConsultaRepository repository){
        this.repository = repository;
    }

    public void salvar(Consulta consulta){
        this.repository.save(consulta);
    }



    public List<ConsultaDTO> listaConsultasDTO(){
        return  this.repository.findAllDTO();
    }

    public SalvarConsultaDTO salvarConsultaDTO(int idcon){return this.repository.salvarConsulta(idcon);}

    public Consulta findById(int idcon){
        return  this.repository.findById(idcon).get();
    }

    public void atualizar(Consulta consulta){
        Consulta c = this.repository.getReferenceById(consulta.getIdcon());
        c.setMedico(consulta.getMedico());
        c.setPaciente(consulta.getPaciente());
        c.setObservacao(consulta.getObservacao());
        c.setDataa(consulta.getDataa());
        c.setPrescricao(consulta.getPrescricao());
        c.setSintoma(consulta.getSintoma());
    }

    public void excluir(int id){
        this.repository.deleteById(id);
    }
}
