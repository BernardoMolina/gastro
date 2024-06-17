package br.csi.clinica_gastro.service;

import br.csi.clinica_gastro.model.colangioressonancia.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColangioressonanciaService {

    private ColangioressonanciaRepository repository;
    public ColangioressonanciaService(ColangioressonanciaRepository repository){
        this.repository = repository;
    }

    public void salvar(Colangioressonancia colangioressonancia){
        this.repository.save(colangioressonancia);
    }



    public List<ColangioressonanciaDTO> listaColangioressonanciasDTO(){
        return  this.repository.findAllDTO();
    }

    public SalvarColangioressonanciaDTO salvarColangioressonanciaDTO(int Idcol){return this.repository.salvarColangioressonancia(Idcol);}

    public Colangioressonancia findById(int Idcol){
        return  this.repository.findById(Idcol).get();
    }

    public void atualizar(Colangioressonancia colangioressonancia){
        Colangioressonancia c = this.repository.getReferenceById(colangioressonancia.getIdcol());
        c.setDiagnostico(colangioressonancia.getDiagnostico());
        c.setTecnica(colangioressonancia.getTecnica());
        c.setObservacao(colangioressonancia.getObservacao());
        c.setExame(colangioressonancia.getExame());
    }

    public void excluir(int id){
        this.repository.deleteById(id);
    }

    public List<TodasColangioressonanciasDTO> listarTodasColangioressonanciasDTO(){
        return  this.repository.findAllColangioressonanciasDTO();
    }
}
