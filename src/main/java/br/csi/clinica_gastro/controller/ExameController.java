package br.csi.clinica_gastro.controller;

import br.csi.clinica_gastro.model.exame.Exame;
import br.csi.clinica_gastro.model.exame.ExameDTO;
import br.csi.clinica_gastro.model.exame.SalvarExameDTO;
import br.csi.clinica_gastro.service.ExameService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/exame")
public class ExameController {

    private final ExameService service;

    public ExameController(ExameService service){
        this.service=service;
    }

    @GetMapping("/{id}")
    public Exame exame(@PathVariable int id){
        return  this.service.findById(id);
    }

    @PostMapping
    @Transactional
    public ResponseEntity salvar(@RequestBody @Valid Exame exame, UriComponentsBuilder uriComponentsBuilder){

        this.service.salvar(exame);
        URI uri = uriComponentsBuilder.path("/exame/{idex}").buildAndExpand(exame.getIdex()).toUri();
        SalvarExameDTO examedto = this.service.salvarExameDTO(exame.getIdex());
        return ResponseEntity.created(uri).body(examedto);
    }

    @GetMapping
    public List<ExameDTO> listarExameDTO(){
        return this.service.listaExamesDTO();
    }


    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody Exame exame){
        this.service.atualizar(exame);
        SalvarExameDTO examedto = this.service.salvarExameDTO(exame.getIdex());
        return ResponseEntity.ok(examedto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable int id){
        this.service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
