package pe.com.pacifico.kuntur.expose;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.ResourceUtils;
import org.springframework.web.reactive.function.BodyInserters;
import pe.com.pacifico.kuntur.expose.request.PolizaRequest;
import pe.com.pacifico.kuntur.util.Constants;
import static org.mockito.ArgumentMatchers.any;

/**
 * <b>Class</b>: ModeloComercialControllerTest <br/>
 * <b>Copyright</b>: 2022 Pacifico Seguros - La Chakra <br/>.
 *
 * @author 2022  Pacifico Seguros - La Chakra <br/>
 * <u>Service Provider</u>: Soluciones Digitales <br/>
 * <u>Developed by</u>: La Chakra developer <br/>
 * <u>Changes:</u><br/>
 * <ul>
 *   <li>
 *     June 06, 2022 Creación de Clase.
 *   </li>
 * </ul>
 */
@AutoConfigureWebTestClient(timeout = "50000")
@WebFluxTest(controllers = PolizaController.class)
class PolizaControllerTest {

  private final WebTestClient webTestClient;

  ObjectMapper objectMapper = new ObjectMapper();

  @MockBean
  private PolizaService polizaService;

  @Autowired
  public PolizaControllerTest(WebTestClient webTestClient) {
    this.webTestClient = webTestClient;
  }

  @Test
  void shouldReturnGetResponseOkPolizaController() throws IOException {

    var file = ResourceUtils.getFile("classpath:requests/200_poliza_request.json");
    PolizaRequest request = objectMapper.readValue(file, PolizaRequest.class);

    Mockito.when(polizaService.savePoliza(request,"102030","192.168.1.120","aplicacionTest","usuarioTest", "servicioTest")).thenReturn("1");

    this.webTestClient
        .post()
        .uri("/poliza")
        .header(Constants.TRANSACCION_ID, "102030")
        .header(Constants.APLICACION_ID, "192.168.1.120")
        .header(Constants.NOMBRE_APLICACION, "aplicacionTest")
        .header(Constants.USUARIO_CONSUMIRDOR_ID, "usuarioTest")
        .header(Constants.NOMBRE_SERVICIO_CONSUMIDOR, "servicioTest")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(request))
        .exchange()
        .expectStatus()
        .isOk();
  }

}
