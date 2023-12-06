package pe.com.pacifico.kuntur.expose;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.pacifico.kuntur.business.TareaService;
import pe.com.pacifico.kuntur.expose.request.TareaRequest;
import pe.com.pacifico.kuntur.expose.request.TareaUpdateDeleteRequest;
import pe.com.pacifico.kuntur.expose.request.TareaUpdateRequest;
import pe.com.pacifico.kuntur.expose.request.response.TareaListarResponse;
import pe.com.pacifico.kuntur.expose.request.response.TareaSaveResponse;
import pe.com.pacifico.kuntur.expose.request.response.TareaUpdateResponse;
import pe.com.pacifico.kuntur.model.Tarea;


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
@RequestMapping("/tarea")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class TareaController {


  private final TareaService tareaService;

  /**
   * This method is used to get only one exampleToBeObfuscated.
   * @author Bruno Castro
   * @param request This is the first parameter to method.
   * @return one example.
   */
  @SuppressWarnings("checkstyle:Indentation")
  @PostMapping
  @ApiOperation(value = "Guardar Tarea", notes = "Endpoint Tarea", response = TareaSaveResponse.class, code = 200)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successful Ok", response = TareaSaveResponse.class),
      @ApiResponse(code = 400, message = "Request object in invalid"),
      @ApiResponse(code = 401, message = "Not Authorized"),
      @ApiResponse(code = 500, message = "Internal Server Error"),
      @ApiResponse(code = 404, message = "Not found"),
      @ApiResponse(code = 415, message = "Content type not supported")})
  public TareaSaveResponse saveTarea(
      @RequestBody @Valid  TareaRequest request) {
    log.info("Recibiendo request {}", request);
    TareaSaveResponse response = new TareaSaveResponse();
    response.setIdTarea(tareaService.saveTarea(request));
    log.info("Id solicitud de poliza generado: {}", response.getIdTarea());
    return response;

  }


  /**
   * This method is used to get only one exampleToBeObfuscated.
   *
   * @param request This is the first parameter to method.
   * @return one example.
   */
  @SuppressWarnings("checkstyle:Indentation")
  @PostMapping("/update")
  @ApiOperation(value = "Actualizar Tarea", notes = "Endpoint Tarea", response = TareaUpdateResponse.class, code = 200)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successful Ok", response = TareaUpdateResponse.class),
      @ApiResponse(code = 400, message = "Request object in invalid"),
      @ApiResponse(code = 401, message = "Not Authorized"),
      @ApiResponse(code = 500, message = "Internal Server Error"),
      @ApiResponse(code = 404, message = "Not found"),
      @ApiResponse(code = 415, message = "Content type not supported")})
  public TareaUpdateResponse updateTarea(
      @RequestBody @Valid TareaUpdateRequest request) {
    log.info("Recibiendo request {}", request);
    TareaUpdateResponse response = new TareaUpdateResponse();
    response.setMensaje(tareaService.updateTarea(request));
    log.info("Mensaje generado generado: {}", response.getMensaje());
    return response;

  }


  /**
   * This method is used to get only one exampleToBeObfuscated.
   *
   * @param request This is the first parameter to method.
   * @return one example.
   */
  @SuppressWarnings("checkstyle:Indentation")
  @PostMapping("/delete")
  @ApiOperation(value = "Eliminar Tarea", notes = "Endpoint Tarea", response = TareaUpdateResponse.class, code = 200)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successful Ok", response = TareaUpdateResponse.class),
      @ApiResponse(code = 400, message = "Request object in invalid"),
      @ApiResponse(code = 401, message = "Not Authorized"),
      @ApiResponse(code = 500, message = "Internal Server Error"),
      @ApiResponse(code = 404, message = "Not found"),
      @ApiResponse(code = 415, message = "Content type not supported")})
  public TareaUpdateResponse deleteTarea(
      @RequestBody @Valid TareaUpdateDeleteRequest request) {
    log.info("Recibiendo request {}", request);
    TareaUpdateResponse response = new TareaUpdateResponse();
    response.setMensaje(tareaService.updateDeleteTarea(request));
    log.info("Id solicitud de poliza generado: {}", response.getMensaje());
    return response;

  }
  /**
   * This method is used to get all planResponse.
   *
   * @return all plan.
   */

  @GetMapping
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Successful Ok", response = TareaListarResponse.class),
    @ApiResponse(code = 400, message = "Request object in invalid"),
    @ApiResponse(code = 401, message = "Not Authorized"),
    @ApiResponse(code = 500, message = "Internal Server Error"),
    @ApiResponse(code = 404, message = "Not found"),
    @ApiResponse(code = 415, message = "Content type not supported")})
  @ApiOperation(value = "Consulta Plan", notes = "Endpoint Plan", response = TareaListarResponse.class)
    public List<TareaListarResponse> getTareas() {
    System.out.println("getTareas:::");
    return tareaService.listarTarea().stream().map(this::buildResponse).collect(Collectors.toList());
  }

  private TareaListarResponse buildResponse(Tarea t) {
    TareaListarResponse a = new TareaListarResponse();
    a.setIdTarea(String.valueOf(t.getIdTarea()));
    a.setDescripcion(t.getDescripcion());
    a.setNombreTarea(t.getNombreTarea());
    a.setEstado((t.getEstado().equals("1")) ? "Activo" : "Inactivo");

    return a;
  }



}
