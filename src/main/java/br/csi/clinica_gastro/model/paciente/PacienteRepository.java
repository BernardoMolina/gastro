package br.csi.clinica_gastro.model.paciente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

    @Query(value = "SELECT * \n" +
            "FROM pacientes where pacientes.idpac =:idpac", nativeQuery = true)
    SalvarPacienteDTO salvarPaciente(@Param("idpac") int idpac);

    @Query(value = "SELECT * FROM pacientes,usuarios where pacientes.iduser=usuarios.idus", nativeQuery = true)
    List<PacienteDTO> findAllDTO();

    @Query(value = "SELECT p.idpac AS idpac, p.iduser AS iduser, " +
            "u.nome_completo, u.email, u.cpf, u.telefone, u.status, " +
            "p.funcao, p.sangue, p.plano_de_saude, p.med_uso_cont, p.condicao_cronica, " +
            "p.doenca_anterior, p.doenca_infec, p.cirurgia, p.data_de_nasc, p.alergia, " +
            "p.historico_familiar, p.sexo, p.imunizacao " +
            "FROM pacientes p, usuarios u WHERE p.iduser = u.idus", nativeQuery = true)
    List<InfoTodosPacientesDTO> findAllPacientesDTO();


}
