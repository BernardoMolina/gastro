package br.csi.clinica_gastro.model.medico;

import br.csi.clinica_gastro.model.medico.InfoTodosMedicosDTO;
import br.csi.clinica_gastro.model.medico.Medico;
import br.csi.clinica_gastro.model.medico.MedicoDTO;
import br.csi.clinica_gastro.model.medico.SalvarMedicoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicoRepository extends JpaRepository<Medico, Integer> {

    @Query(value = "SELECT * \n" +
            "FROM medicos where medicos.idmed =:idmed", nativeQuery = true)
    SalvarMedicoDTO salvarMedico(@Param("idmed") int idmed);

    @Query(value = "SELECT * FROM medicos,usuarios where medicos.iduser=usuarios.idus", nativeQuery = true)
    List<MedicoDTO> findAllDTO();

    @Query(value = "SELECT * FROM medicos,usuarios where medicos.iduser=usuarios.idus", nativeQuery = true)
    List<InfoTodosMedicosDTO> findAllMedicosDTO();
}
