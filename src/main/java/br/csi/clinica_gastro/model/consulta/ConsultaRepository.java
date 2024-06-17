package br.csi.clinica_gastro.model.consulta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ConsultaRepository extends JpaRepository<Consulta, Integer> {

    public Consulta getById(int idcon);
    public Optional<Consulta> findById(int idcon);
    @Query(value = "SELECT observacao,dataa,sintoma,prescricao,c.idcon, u.nome_completo AS nomemedico, p.nome_completo AS nomepaciente\n" +
            "FROM consultas c\n" +
            "JOIN medicos m ON c.idmedico = m.idmed\n" +
            "JOIN usuarios u ON m.iduser = u.idus\n" +
            "JOIN pacientes pa ON c.idpaciente = pa.idpac\n" +
            "JOIN usuarios p ON pa.iduser = p.idus;", nativeQuery = true)
    List<ConsultaDTO> findAllDTO();

    @Query(value = "SELECT consultas.idcon as idcon,dataa,pacientes.idpac as paciente,medicos.idmed as medico,observacao,prescricao,sintoma \n" +
            "FROM pacientes,consultas,medicos where pacientes.idpac=consultas.idpaciente and\n" +
            "medicos.idmed=consultas.idmedico and\n" +
            "consultas.idcon =:idcon", nativeQuery = true)
    SalvarConsultaDTO salvarConsulta(@Param("idcon") int idcon);
}
