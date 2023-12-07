package pe.com.pacifico.kuntur.expose;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.pacifico.kuntur.business.TareaService;
import pe.com.pacifico.kuntur.business.UsuarioService;
import pe.com.pacifico.kuntur.expose.request.UsuarioLoginRequest;
import pe.com.pacifico.kuntur.expose.request.UsuarioSaveRequest;
import pe.com.pacifico.kuntur.expose.request.response.TareaSaveResponse;
import pe.com.pacifico.kuntur.expose.request.response.TareaUpdateResponse;
import pe.com.pacifico.kuntur.expose.request.response.UsuarioLoginResponse;


/**
 * <b>Class</b>: PolizaController <br/>
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

@RestController
@RequestMapping("/usuario")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {


  private final TareaService tareaService;

  private final UsuarioService usuarioService;

  /**
   * This method is used to get only one exampleToBeObfuscated.
   * @author Bruno Castro
   * @param request This is the first parameter to method.
   * @return one example.
   */
  @SuppressWarnings("checkstyle:Indentation")
  @PostMapping
  @ApiOperation(value = "Guardar Usuario", notes = "Endpoint Tarea", response = TareaSaveResponse.class, code = 200)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successful Ok", response = TareaSaveResponse.class),
      @ApiResponse(code = 400, message = "Request object in invalid"),
      @ApiResponse(code = 401, message = "Not Authorized"),
      @ApiResponse(code = 500, message = "Internal Server Error"),
      @ApiResponse(code = 404, message = "Not found"),
      @ApiResponse(code = 415, message = "Content type not supported")})
  public ResponseEntity<UsuarioLoginResponse> registrarUsuario(@RequestBody @Valid UsuarioSaveRequest request) {
    return ResponseEntity.ok(usuarioService.guardarUsuario(request)) ;


  }


  /**
   * This method is used to get only one exampleToBeObfuscated.
   *
   * @param request This is the first parameter to method.
   * @return one example.
   */
  @SuppressWarnings("checkstyle:Indentation")
  @PostMapping("/login")
  @ApiOperation(value = "Login", notes = "Endpoint Login", response = TareaUpdateResponse.class, code = 200)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successful Ok", response = TareaUpdateResponse.class),
      @ApiResponse(code = 400, message = "Request object in invalid"),
      @ApiResponse(code = 401, message = "Not Authorized"),
      @ApiResponse(code = 500, message = "Internal Server Error"),
      @ApiResponse(code = 404, message = "Not found"),
      @ApiResponse(code = 415, message = "Content type not supported")})
  public ResponseEntity<UsuarioLoginResponse> login(@RequestBody @Valid UsuarioLoginRequest request) {

    return ResponseEntity.ok(usuarioService.login(request));
  }
}
