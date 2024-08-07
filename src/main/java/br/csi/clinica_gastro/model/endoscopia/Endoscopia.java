package br.csi.clinica_gastro.model.endoscopia;

import br.csi.clinica_gastro.model.exame.Exame;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "endoscopia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Endoscopia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idend;

    private String duodeno;

    private String esofago;

    @NotBlank
    private String conclusao;

    @NotBlank
    private String descricao;
    @OneToOne
    @JoinColumn(name = "idexame")
    private Exame exame;
}
