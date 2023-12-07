package pe.com.pacifico.kuntur.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.com.pacifico.kuntur.model.Producto;
import pe.com.pacifico.kuntur.model.Usuario;

public interface UsuarioJpaRepository extends JpaRepository<Usuario, Long> {


  @Query(value = "select * from ADMIN.USUARIOS u where u.usuario = :usuario ",nativeQuery = true)
  Optional<Usuario> buscarPorNombreUsuario(@Param("usuario") String usuario);

  @Query(value = "select * from ADMIN.USUARIOS u where u.idusuario = :usuario ",nativeQuery = true)
  Optional<Usuario> buscarPorId(@Param("usuario") long idUsuario);

  @Query(value = "SELECT COALESCE(MAX(id_usuario), 0) + 1 FROM ADMIN.USUARIOS", nativeQuery = true)
  int obtenerMaxIdUsuario();


}
