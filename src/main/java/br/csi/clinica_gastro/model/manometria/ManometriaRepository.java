package br.csi.clinica_gastro.model.manometria;

import br.csi.clinica_gastro.model.manometria.Manometria;
import br.csi.clinica_gastro.model.manometria.ManometriaDTO;
import br.csi.clinica_gastro.model.manometria.SalvarManometriaDTO;
import br.csi.clinica_gastro.model.manometria.TodasManometriasDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ManometriaRepository extends JpaRepository<Manometria, Integer> {

    @Query(value = "SELECT * \n" +
            "FROM manometria where manometria.idman =:idman", nativeQuery = true)
    SalvarManometriaDTO salvarManometria(@Param("idman") int idman);

    @Query(value = "SELECT * FROM manometria,exames where manometria.idexame=exames.idex", nativeQuery = true)
    List<ManometriaDTO> findAllDTO();

    @Query(value = "SELECT ma.idman AS idman, " +
            "ma.idexame AS idexame, " +
            "e.idpaciente AS idpaciente, " +
            "e.idmedico AS idmedico, " +
            "ma.sumario AS sumario, " +
            "ma.conclusao AS conclusao, " +
            "ma.resultados AS resultados, " +
            "e.dataa AS dataexame, " +
            "um.nome_completo AS nomemedico, " +
            "up.nome_completo AS nomepaciente " +
            "FROM manometria ma " +
            "JOIN exames e ON ma.idexame = e.idex " +
            "JOIN medicos m ON e.idmedico = m.idmed " +
            "JOIN usuarios um ON m.iduser = um.idus " +
            "JOIN pacientes p ON e.idpaciente = p.idpac " +
            "JOIN usuarios up ON p.iduser = up.idus", nativeQuery = true)
    List<TodasManometriasDTO> findAllManometriasDTO();
}
