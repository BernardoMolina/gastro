package br.csi.clinica_gastro.model.colangioressonancia;

import br.csi.clinica_gastro.model.exame.Exame;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
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

    private String diagnostico;

    private String tecnica;

    private String observacao;

    @OneToOne
    @JoinColumn(name = "idexame")
    private Exame exame;
}
