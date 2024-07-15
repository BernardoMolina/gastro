package br.csi.clinica_gastro.controller;

import br.csi.clinica_gastro.model.endoscopia.Endoscopia;
import br.csi.clinica_gastro.model.endoscopia.EndoscopiaDTO;
import br.csi.clinica_gastro.model.endoscopia.SalvarEndoscopiaDTO;
import br.csi.clinica_gastro.model.endoscopia.TodasEndoscopiasDTO;
import br.csi.clinica_gastro.service.EndoscopiaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/endoscopia")
public class EndoscopiaController {

    private final EndoscopiaService service;

    public EndoscopiaController(EndoscopiaService service){
        this.service=service;
    }

    @GetMapping("/{id}")
    public Endoscopia endoscopia(@PathVariable int id){
        return  this.service.findById(id);
    }

    @PostMapping
    @Transactional
    public ResponseEntity salvar(@RequestBody @Valid Endoscopia endoscopia, UriComponentsBuilder uriComponentsBuilder){

        this.service.salvar(endoscopia);
        URI uri = uriComponentsBuilder.path("/endoscopia/{idend}").buildAndExpand(endoscopia.getIdend()).toUri();
        SalvarEndoscopiaDTO endoscopiadto = this.service.salvarEndoscopiaDTO(endoscopia.getIdend());
        return ResponseEntity.created(uri).body(endoscopiadto);
    }

    @GetMapping
    public List<EndoscopiaDTO> listarEndoscopiaDTO(){
        return this.service.listaEndoscopiasDTO();
    }


    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody Endoscopia endoscopia){
        this.service.atualizar(endoscopia);
        SalvarEndoscopiaDTO endoscopiadto = this.service.salvarEndoscopiaDTO(endoscopia.getIdend());
        return ResponseEntity.ok(endoscopiadto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable int id){
        this.service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/detalhes_endoscopias")
    public List<TodasEndoscopiasDTO> listarTodasEndoscopias(){
        return this.service.listarTodasEndoscopiasDTO();
    }

}
