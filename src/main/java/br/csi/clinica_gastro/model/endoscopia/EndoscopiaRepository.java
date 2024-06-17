package br.csi.clinica_gastro.model.endoscopia;

import br.csi.clinica_gastro.model.endoscopia.TodasEndoscopiasDTO;
import br.csi.clinica_gastro.model.endoscopia.Endoscopia;
import br.csi.clinica_gastro.model.endoscopia.EndoscopiaDTO;
import br.csi.clinica_gastro.model.endoscopia.SalvarEndoscopiaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EndoscopiaRepository extends JpaRepository<Endoscopia, Integer> {

    @Query(value = "SELECT * \n" +
            "FROM endoscopia where endoscopia.idend =:idend", nativeQuery = true)
    SalvarEndoscopiaDTO salvarEndoscopia(@Param("idend") int idend);

    @Query(value = "SELECT * FROM endoscopia,exames where endoscopia.idexame=exames.idex", nativeQuery = true)
    List<EndoscopiaDTO> findAllDTO();

    @Query(value = "SELECT duodeno,esofago,descricao,conclusao,e.idend, u.nome_completo AS nomemedico, p.nome_completo AS nomepaciente, ex.dataa AS dataexame\n" +
            "FROM endoscopia e\n" +
            "JOIN exames ex ON e.idexame = ex.idex\n" +
            "JOIN medicos m ON ex.idmedico = m.idmed\n" +
            "JOIN usuarios u ON m.iduser = u.idus\n" +
            "JOIN pacientes pa ON ex.idpaciente = pa.idpac\n" +
            "JOIN usuarios p ON pa.iduser = p.idus;", nativeQuery = true)
    List<TodasEndoscopiasDTO> findAllEndoscopiasDTO();
}
