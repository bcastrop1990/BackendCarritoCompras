package pe.com.pacifico.kuntur.business;

import java.util.List;
import pe.com.pacifico.kuntur.expose.request.TareaRequest;
import pe.com.pacifico.kuntur.expose.request.TareaUpdateDeleteRequest;
import pe.com.pacifico.kuntur.expose.request.TareaUpdateRequest;
import pe.com.pacifico.kuntur.model.Tarea;

/**
 * <b>Class</b>: PolizaService <br/>
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
public interface TareaService {

  String saveTarea(TareaRequest request);

  List<Tarea> listarTarea();

  String updateTarea(TareaUpdateRequest request);

  String updateDeleteTarea(TareaUpdateDeleteRequest request);
}
