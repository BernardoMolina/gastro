package br.csi.clinica_gastro.model.medico;

import br.csi.clinica_gastro.model.exame.Exame;
import br.csi.clinica_gastro.model.usuario.Usuario;
import jakarta.persistence.*;
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
    private int idmed;

    private String registro;

    @OneToOne
    @JoinColumn(name = "iduser")
    private Usuario usuario;
}
