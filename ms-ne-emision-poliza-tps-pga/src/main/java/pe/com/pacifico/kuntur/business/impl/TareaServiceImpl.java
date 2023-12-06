package pe.com.pacifico.kuntur.business.impl;


import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.com.pacifico.kuntur.business.TareaService;
import pe.com.pacifico.kuntur.business.util.Constantes;
import pe.com.pacifico.kuntur.expose.request.TareaRequest;
import pe.com.pacifico.kuntur.expose.request.TareaUpdateDeleteRequest;
import pe.com.pacifico.kuntur.expose.request.TareaUpdateRequest;
import pe.com.pacifico.kuntur.model.Tarea;
import pe.com.pacifico.kuntur.repository.TareaJpaRepository;

/**
 * <b>Class</b>: PolizaServiceImpl <br/>
 * <b>Copyright</b>: 2022 Pacífico Seguros - La Chakra <br/>.
 *
 * @author 2022 Pacífico Seguros - La Chakra <br/>
 * <u>Service Provider</u>: Soluciones Digitales <br/>
 * <u>Developed by</u>: Oro Sólido <br/>
 * <u>Changes:</u><br/>
 * <ul>
 *   <li>
 *     September 7, 2022 Creación de Clase.
 *   </li>
 * </ul>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TareaServiceImpl implements TareaService {

  private final TareaJpaRepository tareaJpaRepository;


  /**
   * This method is used to get only one example.
   */
  public String saveTarea(TareaRequest request) {

    Long  idTarea = tareaJpaRepository.obtenerMaxIdTarea();

    Tarea tarea = new Tarea();
    tarea.setIdTarea(String.valueOf(idTarea));
    tarea.setNombreTarea(request.getNombreTarea());
    tarea.setDescripcion(request.getDescripcion());
    tarea.setEstado("1");


    var saveTarea = tareaJpaRepository.save(tarea);

    return String.valueOf(saveTarea.getIdTarea());
  }

  @Override
  public List<Tarea> listarTarea() {
    return tareaJpaRepository.findAll();
  }

  @Override
  public String updateTarea(TareaUpdateRequest request) {

    Tarea tarea = new Tarea();
    Optional.ofNullable(request.getIdTarea()).ifPresent(value -> tarea.setIdTarea(value.trim().toUpperCase()));
    Optional.ofNullable(request.getNombreTarea()).ifPresent(value -> tarea.setNombreTarea(value.trim()));
    Optional.ofNullable(request.getDescripcion()).ifPresent(value -> tarea.setDescripcion(value.trim()));

    tareaJpaRepository.save(tarea);
    return Constantes.MENSAJE_EXITO_UPDATE_TAREA;
  }


  @Override
  public String updateDeleteTarea(TareaUpdateDeleteRequest request) {
    Optional<Tarea> tareaOptional = tareaJpaRepository.obtenerTareaById(request.getIdTarea());
    if (tareaOptional != null) {


      tareaOptional.ifPresent(tarea -> {
      // Verifica si el estado actual es "1" y, de ser así, cambia a "0"
        if ("1".equals(tarea.getEstado())) {
        tarea.setEstado("0");
        tareaJpaRepository.save(tarea); // Guarda la tarea actualizada en la base de datos
        }else{
        tarea.setEstado("1");
        tareaJpaRepository.save(tarea);
        }
    });

      return Constantes.MENSAJE_EXITO_UPDATE_DELETE_TAREA;
    }
    else{
      return Constantes.MENSAJE_FALLO_UPDATE_DELETE_TAREA;
    }




  }
  private String toggleEstado(String estado) {
    return "1".equals(estado) ? "0" : "1";
  }
}
