package br.csi.clinica_gastro.service;

import br.csi.clinica_gastro.model.endoscopia.*;
import br.csi.clinica_gastro.model.paciente.InfoTodosPacientesDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EndoscopiaService {

    private EndoscopiaRepository repository;
    public EndoscopiaService(EndoscopiaRepository repository){
        this.repository = repository;
    }

    public void salvar(Endoscopia endoscopia){
        this.repository.save(endoscopia);
    }



    public List<EndoscopiaDTO> listaEndoscopiasDTO(){
        return  this.repository.findAllDTO();
    }

    public SalvarEndoscopiaDTO salvarEndoscopiaDTO(int Idend){return this.repository.salvarEndoscopia(Idend);}

    public Endoscopia findById(int Idend){
        return  this.repository.findById(Idend).get();
    }

    public void atualizar(Endoscopia endoscopia){
        Endoscopia e = this.repository.getReferenceById(endoscopia.getIdend());
        e.setDuodeno(endoscopia.getDuodeno());
        e.setEsofago(endoscopia.getEsofago());
        e.setConclusao(endoscopia.getConclusao());
        e.setDescricao(endoscopia.getDescricao());
        e.setExame(endoscopia.getExame());
    }

    public void excluir(int id){
        this.repository.deleteById(id);
    }

    public List<TodasEndoscopiasDTO> listarTodasEndoscopiasDTO(){
        return  this.repository.findAllEndoscopiasDTO();
    }
}
