package br.csi.clinica_gastro.model.exame;

import br.csi.clinica_gastro.model.exame.Exame;
import br.csi.clinica_gastro.model.exame.ExameDTO;
import br.csi.clinica_gastro.model.exame.SalvarExameDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ExameRepository extends JpaRepository<Exame, Integer> {

    public Exame getById(int idex);
    public Optional<Exame> findById(int idex);
    @Query(value = "SELECT dataa,e.idex, u.nome_completo AS nomemedico, p.nome_completo AS nomepaciente\n" +
            "FROM exames e\n" +
            "JOIN medicos m ON e.idmedico = m.idmed\n" +
            "JOIN usuarios u ON m.iduser = u.idus\n" +
            "JOIN pacientes pa ON e.idpaciente = pa.idpac\n" +
            "JOIN usuarios p ON pa.iduser = p.idus;", nativeQuery = true)
    List<ExameDTO> findAllDTO();

    @Query(value = "SELECT exames.idex as idex,dataa,pacientes.idpac as paciente,medicos.idmed as medico \n" +
            "FROM pacientes,exames,medicos where pacientes.idpac=exames.idpaciente and\n" +
            "medicos.idmed=exames.idmedico and\n" +
            "exames.idex =:idex", nativeQuery = true)
    SalvarExameDTO salvarExame(@Param("idex") int idex);
}
