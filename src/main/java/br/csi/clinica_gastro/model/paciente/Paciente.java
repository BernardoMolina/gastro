package br.csi.clinica_gastro.model.paciente;

import br.csi.clinica_gastro.model.consulta.Consulta;
import br.csi.clinica_gastro.model.exame.Exame;
import br.csi.clinica_gastro.model.usuario.Usuario;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;


@Entity
@Table(name = "pacientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idpac;

    private String funcao;

    private String sangue;

    private String plano_de_saude;

    private String med_uso_cont;

    private String condicao_cronica;

    private String doenca_anterior;

    private String doenca_infec;

    private String cirurgia;


    private String data_de_nasc;

    private String alergia;

    private String historico_familiar;


    private String sexo;

    private String imunizacao;

    @OneToMany(mappedBy = "paciente")
    private List<Consulta> consultas;

    @OneToOne
    @JoinColumn(name = "iduser")
    private Usuario usuario;
}
