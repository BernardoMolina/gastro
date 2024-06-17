package br.csi.clinica_gastro.controller;

import br.csi.clinica_gastro.model.consulta.Consulta;
import br.csi.clinica_gastro.model.consulta.ConsultaDTO;
import br.csi.clinica_gastro.model.consulta.SalvarConsultaDTO;
import br.csi.clinica_gastro.service.ConsultaService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    private final ConsultaService service;

    public ConsultaController(ConsultaService service){
        this.service=service;
    }

    @GetMapping("/{id}")
    public Consulta consulta(@PathVariable int id){
        return  this.service.findById(id);
    }

    @PostMapping
    @Transactional
    public ResponseEntity salvar(@RequestBody Consulta consulta, UriComponentsBuilder uriComponentsBuilder){

        this.service.salvar(consulta);
        URI uri = uriComponentsBuilder.path("/consulta/{idcon}").buildAndExpand(consulta.getIdcon()).toUri();
        SalvarConsultaDTO consultadto = this.service.salvarConsultaDTO(consulta.getIdcon());
        return ResponseEntity.created(uri).body(consultadto);
    }

    @GetMapping
    public List<ConsultaDTO> listarConsultaDTO(){
        return this.service.listaConsultasDTO();
    }


    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody Consulta consulta){
        this.service.atualizar(consulta);
        SalvarConsultaDTO consultadto = this.service.salvarConsultaDTO(consulta.getIdcon());
        return ResponseEntity.ok(consultadto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable int id){
        this.service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
