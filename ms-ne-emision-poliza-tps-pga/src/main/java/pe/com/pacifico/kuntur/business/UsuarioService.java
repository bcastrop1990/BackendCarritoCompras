package pe.com.pacifico.kuntur.business;

import org.springframework.http.ResponseEntity;
import pe.com.pacifico.kuntur.expose.request.UsuarioLoginRequest;
import pe.com.pacifico.kuntur.expose.request.UsuarioSaveRequest;
import pe.com.pacifico.kuntur.expose.request.response.UsuarioResponse;

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
public interface UsuarioService {

  ResponseEntity login(UsuarioLoginRequest request );
  ResponseEntity guardarUsuario(UsuarioSaveRequest request);

  UsuarioResponse buscarUsuarioById(Long idUsuario);
}
