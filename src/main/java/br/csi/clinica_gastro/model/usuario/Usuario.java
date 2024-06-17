package br.csi.clinica_gastro.model.usuario;

import br.csi.clinica_gastro.model.colangioressonancia.Colangioressonancia;
import br.csi.clinica_gastro.model.manometria.Manometria;
import br.csi.clinica_gastro.model.medico.Medico;
import br.csi.clinica_gastro.model.paciente.Paciente;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "usuarios")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idus;


    private String nome_completo;


    private String email;


    private String cpf;


    private String telefone;


    private String senha;


    private String permissao;


    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne(mappedBy = "usuario")
    private Paciente paciente;
    @OneToOne(mappedBy = "usuario")
    private Medico medico;
}
