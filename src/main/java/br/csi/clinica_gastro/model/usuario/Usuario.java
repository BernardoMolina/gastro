package br.csi.clinica_gastro.model.usuario;

import br.csi.clinica_gastro.model.colangioressonancia.Colangioressonancia;
import br.csi.clinica_gastro.model.manometria.Manometria;
import br.csi.clinica_gastro.model.medico.Medico;
import br.csi.clinica_gastro.model.paciente.Paciente;
import jakarta.persistence.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "usuarios")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idus")
    private int idus;


    @NotBlank
    @Column(name = "nome_completo", nullable = false, length = 80)
    private String nome_completo;

    @NotBlank
    @Email
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @NotBlank
    //@CPF
    //@Size(max = 14,min = 14,message = "CPF deve ter 11 números!!")
    @Column(name = "cpf", nullable = false, unique = true, length = 14)
    private String cpf;

    @NotBlank
    //@Size(max = 14,min = 14,message = "Telefone deve ter 11 números!!")
    @Column(name = "telefone", nullable = false, unique = true, length = 14)
    private String telefone;

    @NotBlank
    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(name = "permissao")
    private String permissao;

    public Usuario( String email, String senha, String permissao){
        this.email = email;
        this.senha = senha;
        this.permissao = permissao;
    }


    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @OneToOne(mappedBy = "usuario")
    private Paciente paciente;
    @OneToOne(mappedBy = "usuario")
    private Medico medico;
}
