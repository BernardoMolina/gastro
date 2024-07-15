package br.csi.clinica_gastro.model.colangioressonancia;

import br.csi.clinica_gastro.model.exame.Exame;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "colangioressonancia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Colangioressonancia  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idcol;

    @NotBlank
    private String diagnostico;

    @NotBlank
    private String tecnica;

    @NotBlank
    private String observacao;

    @OneToOne
    @JoinColumn(name = "idexame")
    private Exame exame;
}
