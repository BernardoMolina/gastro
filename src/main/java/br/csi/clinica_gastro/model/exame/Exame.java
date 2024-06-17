package br.csi.clinica_gastro.model.exame;


import br.csi.clinica_gastro.model.colangioressonancia.Colangioressonancia;
import br.csi.clinica_gastro.model.endoscopia.Endoscopia;
import br.csi.clinica_gastro.model.manometria.Manometria;
import br.csi.clinica_gastro.model.medico.Medico;
import br.csi.clinica_gastro.model.paciente.Paciente;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "exames")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Exame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idex")
    private int idex;

    @ManyToOne
    @JoinColumn(name = "idmedico")
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "idpaciente")
    private Paciente paciente;


    @DateTimeFormat
    private String dataa;

    @OneToOne(mappedBy = "exame")
    private Colangioressonancia colangioressonancia;
    @OneToOne(mappedBy = "exame")
    private Manometria manometria;
    @OneToOne(mappedBy = "exame")
    private Endoscopia endoscopia;

}
