package br.csi.clinica_gastro.controller;


import br.csi.clinica_gastro.model.medico.InfoTodosMedicosDTO;
import br.csi.clinica_gastro.model.medico.Medico;
import br.csi.clinica_gastro.model.medico.MedicoDTO;
import br.csi.clinica_gastro.model.medico.SalvarMedicoDTO;
import br.csi.clinica_gastro.service.MedicoService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    private final MedicoService service;

    public MedicoController(MedicoService service){
        this.service=service;
    }

    @GetMapping("/{idmed}")
    public Medico medico(@PathVariable int idmed){
        return  this.service.findById(idmed);
    }

    @PostMapping
    @Transactional
    public ResponseEntity salvar(@RequestBody  Medico medico, UriComponentsBuilder uriComponentsBuilder){

        this.service.salvar(medico);
        URI uri = uriComponentsBuilder.path("/medico/{idmed}").buildAndExpand(medico.getIdmed()).toUri();
        SalvarMedicoDTO medicodto = this.service.salvarMedicoDTO(medico.getIdmed());
        return ResponseEntity.created(uri).body(medicodto);
    }


    @GetMapping
    public List<MedicoDTO> listarMedicoDTO(){
        return this.service.listaMedicosDTO();
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody Medico medico){
        this.service.atualizar(medico);
        SalvarMedicoDTO medicodto = this.service.salvarMedicoDTO(medico.getIdmed());
        return ResponseEntity.ok(medicodto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable int id){
        this.service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/detalhes_medicos")
    public List<InfoTodosMedicosDTO> listarTodosMedicos(){
        return this.service.listarTodosMedicosDTO();
    }


}
