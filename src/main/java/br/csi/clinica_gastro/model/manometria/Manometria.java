package br.csi.clinica_gastro.model.manometria;

import br.csi.clinica_gastro.model.exame.Exame;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    private String sumario;
    @NotBlank
    private String conclusao;
    @NotBlank
    private String resultados;

    @OneToOne
    @JoinColumn(name = "idexame")
    private Exame exame;

}
