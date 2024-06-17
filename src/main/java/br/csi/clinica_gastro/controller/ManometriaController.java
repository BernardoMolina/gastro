package br.csi.clinica_gastro.controller;

import br.csi.clinica_gastro.model.manometria.Manometria;
import br.csi.clinica_gastro.model.manometria.ManometriaDTO;
import br.csi.clinica_gastro.model.manometria.SalvarManometriaDTO;
import br.csi.clinica_gastro.model.manometria.TodasManometriasDTO;
import br.csi.clinica_gastro.service.ManometriaService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/manometria")
public class ManometriaController {

    private final ManometriaService service;

    public ManometriaController(ManometriaService service){
        this.service=service;
    }

    @GetMapping("/{id}")
    public Manometria manometria(@PathVariable int id){
        return  this.service.findById(id);
    }

    @PostMapping
    @Transactional
    public ResponseEntity salvar(@RequestBody Manometria manometria, UriComponentsBuilder uriComponentsBuilder){

        this.service.salvar(manometria);
        URI uri = uriComponentsBuilder.path("/manometria/{idman}").buildAndExpand(manometria.getIdman()).toUri();
        SalvarManometriaDTO manometriadto = this.service.salvarManometriaDTO(manometria.getIdman());
        return ResponseEntity.created(uri).body(manometriadto);
    }

    @GetMapping
    public List<ManometriaDTO> listarManometriaDTO(){
        return this.service.listaManometriasDTO();
    }


    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody Manometria manometria){
        this.service.atualizar(manometria);
        SalvarManometriaDTO manometriadto = this.service.salvarManometriaDTO(manometria.getIdman());
        return ResponseEntity.ok(manometriadto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable int id){
        this.service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/detalhes_manometrias")
    public List<TodasManometriasDTO> listarTodasManometrias(){
        return this.service.listarTodasManometriasDTO();
    }

}
