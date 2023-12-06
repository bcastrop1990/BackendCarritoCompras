package pe.com.pacifico.kuntur.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.com.pacifico.kuntur.model.Tarea;

/**
 * <b>Class</b>: PolizaJpaRepository <br/>
 * <b>Copyright</b>: 2022 Pacifico Seguros - La Chakra <br/>.
 *
 * @author 2022  Pacifico Seguros - La Chakra <br/>
 * <u>Service Provider</u>: Soluciones Digitales <br/>
 * <u>Developed by</u>: Oro solido <br/>
 * <u>Changes:</u><br/>
 * <ul>
 *   <li>
 *     September 7, 2022 Creación de Clase.
 *   </li>
 * </ul>
 */
public interface TareaJpaRepository extends JpaRepository<Tarea, String>  {
  @Query(value = "SELECT COALESCE(MAX(id_tarea), 0) + 1 FROM ADMIN.TAREAS u", nativeQuery = true)
  Long obtenerMaxIdTarea();

  @Query(value = "select estado from ADMIN.TAREAS u where u.id_tarea = :idTarea ",nativeQuery = true)
  String obtenerEstadoByID(@Param("idTarea") String idTarea);

  @Query(value = "select * from ADMIN.TAREAS u where u.id_tarea = :idTarea ",nativeQuery = true)
  Optional<Tarea> obtenerTareaById(@Param("idTarea") String idTarea);
}
