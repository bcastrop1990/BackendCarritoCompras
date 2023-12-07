package pe.com.pacifico.kuntur.expose;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.pacifico.kuntur.business.UsuarioService;
import pe.com.pacifico.kuntur.business.VentaService;
import pe.com.pacifico.kuntur.expose.request.VentaRequest;
import pe.com.pacifico.kuntur.expose.request.response.VentaResponse;

/**
 * This method is used to get only one exampleToBeObfuscated.
 *
 * @return one example.
 */
@RestController
@RequestMapping("/venta")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class VentaController {

  private final VentaService ventaService;
  private final UsuarioService usuarioService;

  @PostMapping("/registrar")
  public ResponseEntity<VentaResponse> registerSale(@RequestBody VentaRequest request) {

    return ResponseEntity.ok(ventaService.registrarVenta(request));

  }


}
