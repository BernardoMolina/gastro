package br.csi.clinica_gastro.model.medico;

import br.csi.clinica_gastro.model.exame.Exame;
import br.csi.clinica_gastro.model.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "medicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmed")
    private int idmed;

    @NotBlank
    @Column(name = "registro", nullable = false)
    private String registro;

    @OneToOne
    @JoinColumn(name = "iduser", referencedColumnName = "idus")
    private Usuario usuario;
}
