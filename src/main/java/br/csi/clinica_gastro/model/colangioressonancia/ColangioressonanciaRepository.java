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

    @Query(value = "SELECT diagnostico,tecnica,observacao,e.idcol, u.nome_completo AS nomemedico, p.nome_completo AS nomepaciente, ex.dataa AS dataexame\n" +
            "FROM colangioressonancia e\n" +
            "JOIN exames ex ON e.idexame = ex.idex\n" +
            "JOIN medicos m ON ex.idmedico = m.idmed\n" +
            "JOIN usuarios u ON m.iduser = u.idus\n" +
            "JOIN pacientes pa ON ex.idpaciente = pa.idpac\n" +
            "JOIN usuarios p ON pa.iduser = p.idus;", nativeQuery = true)
    List<TodasColangioressonanciasDTO> findAllColangioressonanciasDTO();
}
