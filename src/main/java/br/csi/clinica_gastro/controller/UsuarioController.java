package br.csi.clinica_gastro.controller;


import br.csi.clinica_gastro.model.paciente.Paciente;
import br.csi.clinica_gastro.model.paciente.PacienteDTO;
import br.csi.clinica_gastro.model.paciente.SalvarPacienteDTO;
import br.csi.clinica_gastro.model.usuario.SalvarUsuarioDTO;
import br.csi.clinica_gastro.model.usuario.Usuario;
import br.csi.clinica_gastro.model.usuario.UsuarioDTO;
import br.csi.clinica_gastro.service.UsuarioService;
import jakarta.transaction.Transactional;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService service;
    public UsuarioController(UsuarioService service){
        this.service = service;
    }


    @GetMapping("/{idus}")
    public Usuario usuario(@PathVariable int idus){
        return  this.service.findById(idus);
    }

    @PostMapping
    @Transactional
    public ResponseEntity salvar(@RequestBody @Valid Usuario usuario, UriComponentsBuilder uriComponentsBuilder){

        this.service.salvar(usuario);
        URI uri = uriComponentsBuilder.path("/usuario/{idus}").buildAndExpand(usuario.getIdus()).toUri();
        SalvarUsuarioDTO usuariodto = this.service.salvarUsuarioDTO(usuario.getIdus());
        return ResponseEntity.created(uri).body(usuariodto);
    }

    @GetMapping
    public List<UsuarioDTO> listarUsuarioDTO(){
        return this.service.listaUsuariosDTO();
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody Usuario usuario){
        this.service.atualizar(usuario);
        SalvarUsuarioDTO usuariodto = this.service.salvarUsuarioDTO(usuario.getIdus());
        return ResponseEntity.ok(usuariodto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable int id){
        this.service.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
