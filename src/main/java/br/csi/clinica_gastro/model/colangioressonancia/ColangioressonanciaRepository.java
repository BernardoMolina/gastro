package br.csi.clinica_gastro.model.colangioressonancia;

import br.csi.clinica_gastro.model.colangioressonancia.Colangioressonancia;
import br.csi.clinica_gastro.model.colangioressonancia.ColangioressonanciaDTO;
import br.csi.clinica_gastro.model.colangioressonancia.SalvarColangioressonanciaDTO;
import br.csi.clinica_gastro.model.colangioressonancia.TodasColangioressonanciasDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ColangioressonanciaRepository extends JpaRepository<Colangioressonancia, Integer> {

    @Query(value = "SELECT * \n" +
            "FROM colangioressonancia where colangioressonancia.idcol =:idcol", nativeQuery = true)
    SalvarColangioressonanciaDTO salvarColangioressonancia(@Param("idcol") int idcol);

    @Query(value = "SELECT * FROM colangioressonancia,exames where colangioressonancia.idexame=exames.idex", nativeQuery = true)
    List<ColangioressonanciaDTO> findAllDTO();

    @Query(value = "SELECT c.idcol AS idcol, " +
            "c.idexame AS idexame, " +
            "e.idpaciente AS idpaciente, " +
            "e.idmedico AS idmedico, " +
            "c.diagnostico AS diagnostico, " +
            "c.tecnica AS tecnica, " +
            "c.observacao AS observacao, " +
            "e.dataa AS dataexame, " +
            "um.nome_completo AS nomemedico, " +
            "up.nome_completo AS nomepaciente " +
            "FROM colangioressonancia c " +
            "JOIN exames e ON c.idexame = e.idex " +
            "JOIN medicos m ON e.idmedico = m.idmed " +
            "JOIN usuarios um ON m.iduser = um.idus " +
            "JOIN pacientes p ON e.idpaciente = p.idpac " +
            "JOIN usuarios up ON p.iduser = up.idus", nativeQuery = true)
    List<TodasColangioressonanciasDTO> findAllColangioressonanciasDTO();
}
