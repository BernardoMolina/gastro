package br.csi.clinica_gastro.service;

import br.csi.clinica_gastro.model.manometria.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManometriaService {

    private ManometriaRepository repository;
    public ManometriaService(ManometriaRepository repository){
        this.repository = repository;
    }

    public void salvar(Manometria manometria){
        this.repository.save(manometria);
    }



    public List<ManometriaDTO> listaManometriasDTO(){
        return  this.repository.findAllDTO();
    }

    public SalvarManometriaDTO salvarManometriaDTO(int Idman){return this.repository.salvarManometria(Idman);}

    public Manometria findById(int Idman){
        return  this.repository.findById(Idman).get();
    }

    public void atualizar(Manometria manometria){
        Manometria m = this.repository.getReferenceById(manometria.getIdman());
        m.setSumario(manometria.getSumario());
        m.setResultados(manometria.getResultados());
        m.setConclusao(manometria.getConclusao());
        m.setExame(manometria.getExame());
    }

    public void excluir(int id){
        this.repository.deleteById(id);
    }

    public List<TodasManometriasDTO> listarTodasManometriasDTO(){
        return  this.repository.findAllManometriasDTO();
    }
}
