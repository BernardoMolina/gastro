package br.csi.clinica_gastro.model.manometria;

import br.csi.clinica_gastro.model.exame.Exame;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "manometria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Manometria  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idman;

    private String sumario;

    private String conclusao;

    private String resultados;

    @OneToOne
    @JoinColumn(name = "idexame")
    private Exame exame;

}
