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

    @Query(value = "SELECT en.idend AS idend, " +
            "en.idexame AS idexame, " +
            "e.idpaciente AS idpaciente, " +
            "e.idmedico AS idmedico, " +
            "en.esofago AS esofago, " +
            "en.duodeno AS duodeno, " +
            "en.conclusao AS conclusao, " +
            "en.descricao AS descricao, " +
            "e.dataa AS dataexame, " +
            "um.nome_completo AS nomemedico, " +
            "up.nome_completo AS nomepaciente " +
            "FROM endoscopia en " +
            "JOIN exames e ON en.idexame = e.idex " +
            "JOIN medicos m ON e.idmedico = m.idmed " +
            "JOIN usuarios um ON m.iduser = um.idus " +
            "JOIN pacientes p ON e.idpaciente = p.idpac " +
            "JOIN usuarios up ON p.iduser = up.idus", nativeQuery = true)
    List<TodasEndoscopiasDTO> findAllEndoscopiasDTO();


}
