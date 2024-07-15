package br.csi.clinica_gastro.controller;

import br.csi.clinica_gastro.model.colangioressonancia.Colangioressonancia;
import br.csi.clinica_gastro.model.colangioressonancia.ColangioressonanciaDTO;
import br.csi.clinica_gastro.model.colangioressonancia.SalvarColangioressonanciaDTO;
import br.csi.clinica_gastro.model.colangioressonancia.TodasColangioressonanciasDTO;
import br.csi.clinica_gastro.service.ColangioressonanciaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/colangioressonancia")
public class ColangioressonanciaController {

    private final ColangioressonanciaService service;

    public ColangioressonanciaController(ColangioressonanciaService service){
        this.service=service;
    }

    @GetMapping("/{id}")
    public Colangioressonancia colangioressonancia(@PathVariable int id){
        return  this.service.findById(id);
    }

    @PostMapping
    @Transactional
    public ResponseEntity salvar(@RequestBody @Valid Colangioressonancia colangioressonancia, UriComponentsBuilder uriComponentsBuilder){

        this.service.salvar(colangioressonancia);
        URI uri = uriComponentsBuilder.path("/colangioressonancia/{idcol}").buildAndExpand(colangioressonancia.getIdcol()).toUri();
        SalvarColangioressonanciaDTO colangioressonanciadto = this.service.salvarColangioressonanciaDTO(colangioressonancia.getIdcol());
        return ResponseEntity.created(uri).body(colangioressonanciadto);
    }

    @GetMapping
    public List<ColangioressonanciaDTO> listarColangioressonanciaDTO(){
        return this.service.listaColangioressonanciasDTO();
    }


    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody Colangioressonancia colangioressonancia){
        this.service.atualizar(colangioressonancia);
        SalvarColangioressonanciaDTO colangioressonanciadto = this.service.salvarColangioressonanciaDTO(colangioressonancia.getIdcol());
        return ResponseEntity.ok(colangioressonanciadto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable int id){
        this.service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/detalhes_colangioressonancias")
    public List<TodasColangioressonanciasDTO> listarTodasColangioressonancias(){
        return this.service.listarTodasColangioressonanciasDTO();
    }

}
