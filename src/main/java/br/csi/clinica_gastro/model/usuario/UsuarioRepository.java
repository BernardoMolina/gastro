package br.csi.clinica_gastro.model.usuario;

import br.csi.clinica_gastro.model.paciente.PacienteDTO;
import br.csi.clinica_gastro.model.paciente.SalvarPacienteDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

    @Query(value = "SELECT * \n" +
            "FROM usuarios where usuarios.idus =:idus", nativeQuery = true)
    SalvarUsuarioDTO salvarUsuario(@Param("idus") int idus);

    @Query(value = "SELECT * FROM usuarios", nativeQuery = true)
    List<UsuarioDTO> findAllDTO();

    public Usuario findByEmail(String email);
}
