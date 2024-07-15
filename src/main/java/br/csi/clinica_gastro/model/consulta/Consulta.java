package br.csi.clinica_gastro.model.consulta;


import br.csi.clinica_gastro.model.medico.Medico;
import br.csi.clinica_gastro.model.paciente.Paciente;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "consultas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idcon;

    @ManyToOne
    @JoinColumn(name = "idmedico")
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "idpaciente")
    private Paciente paciente;


    @DateTimeFormat
    private String dataa;

    @NotBlank
    private String observacao;

    private String prescricao;

    private String sintoma;


}
