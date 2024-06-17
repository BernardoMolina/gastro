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

    @Query(value = "SELECT * FROM pacientes,usuarios where pacientes.iduser=usuarios.idus", nativeQuery = true)
    List<InfoTodosPacientesDTO> findAllPacientesDTO();
}
