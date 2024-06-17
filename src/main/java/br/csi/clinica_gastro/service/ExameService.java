package br.csi.clinica_gastro.service;

import br.csi.clinica_gastro.model.exame.Exame;
import br.csi.clinica_gastro.model.exame.ExameDTO;
import br.csi.clinica_gastro.model.exame.ExameRepository;
import br.csi.clinica_gastro.model.exame.SalvarExameDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExameService {

    private ExameRepository repository;
    public ExameService(ExameRepository repository){
        this.repository = repository;
    }

    public void salvar(Exame exame){
        this.repository.save(exame);
    }



    public List<ExameDTO> listaExamesDTO(){
        return  this.repository.findAllDTO();
    }

    public SalvarExameDTO salvarExameDTO(int Idex){return this.repository.salvarExame(Idex);}

    public Exame findById(int Idex){
        return  this.repository.findById(Idex).get();
    }

    public void atualizar(Exame exame){
        Exame c = this.repository.getReferenceById(exame.getIdex());
        c.setMedico(exame.getMedico());
        c.setPaciente(exame.getPaciente());
        c.setDataa(exame.getDataa());
    }

    public void excluir(int id){
        this.repository.deleteById(id);
    }
}
