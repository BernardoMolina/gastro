package br.csi.clinica_gastro.controller;


import br.csi.clinica_gastro.model.paciente.InfoTodosPacientesDTO;
import br.csi.clinica_gastro.model.paciente.Paciente;
import br.csi.clinica_gastro.model.paciente.PacienteDTO;
import br.csi.clinica_gastro.model.paciente.SalvarPacienteDTO;
import br.csi.clinica_gastro.model.usuario.Usuario;
import br.csi.clinica_gastro.service.PacienteService;
import jakarta.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    private final PacienteService service;

    public PacienteController(PacienteService service){
        this.service=service;
    }

    @GetMapping("/{idpac}")
    public Paciente paciente(@PathVariable int idpac){
        return  this.service.findById(idpac);
    }

    @PostMapping
    @Transactional
    public ResponseEntity salvar(@RequestBody  Paciente paciente, UriComponentsBuilder uriComponentsBuilder){

        this.service.salvar(paciente);
        URI uri = uriComponentsBuilder.path("/paciente/{idpac}").buildAndExpand(paciente.getIdpac()).toUri();
        SalvarPacienteDTO pacientedto = this.service.salvarPacienteDTO(paciente.getIdpac());
        return ResponseEntity.created(uri).body(pacientedto);
    }


    @GetMapping
    public List<PacienteDTO> listarPacienteDTO(){
        return this.service.listaPacientesDTO();
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody Paciente paciente){
        this.service.atualizar(paciente);
        SalvarPacienteDTO pacientedto = this.service.salvarPacienteDTO(paciente.getIdpac());
        return ResponseEntity.ok(pacientedto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable int id){
        this.service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/detalhes_pacientes")
    public List<InfoTodosPacientesDTO> listarTodosPacientes(){
        return this.service.listarTodosPacientesDTO();
    }


}
