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

    @Query(value = "SELECT sumario,resultados,conclusao,e.idman, u.nome_completo AS nomemedico, p.nome_completo AS nomepaciente, ex.dataa AS dataexame\n" +
            "FROM manometria e\n" +
            "JOIN exames ex ON e.idexame = ex.idex\n" +
            "JOIN medicos m ON ex.idmedico = m.idmed\n" +
            "JOIN usuarios u ON m.iduser = u.idus\n" +
            "JOIN pacientes pa ON ex.idpaciente = pa.idpac\n" +
            "JOIN usuarios p ON pa.iduser = p.idus;", nativeQuery = true)
    List<TodasManometriasDTO> findAllManometriasDTO();
}
