package pe.com.pacifico.kuntur.util;

/**
 * <b>Class</b>: RegistraService <br/>
 * <b>Copyright</b>: 2021 Pacifico Seguros - La Chakra <br/>.
 *
 * @author 2022  Pacifico Seguros - La Chakra <br/>
 *     <u>Service Provider</u>: Inetum <br/>
 *     <u>Developed by</u>: Cristhian Chininin <br/>
 *     <u>Changes:</u><br/>
 *     <ul>
 *       <li>
 *         October 27, 2022 Creación de Clase.
 *       </li>
 *     </ul>
 */
public class Constants {
  public static final int COMILLA_DOBLE = 34;
  public static final String TRANSACCION_ID = "Transaccion-Id";
  public static final String APLICACION_ID = "Aplicacion-Id";
  public static final String NOMBRE_APLICACION = "Nombre-Aplicacion";
  public static final String USUARIO_CONSUMIRDOR_ID = "Usuario-Consumidor-Id";
  public static final String NOMBRE_SERVICIO_CONSUMIDOR = "Nombre-Servicio-Consumidor";


  public static final String MENSAJE_CODE_STATUS_500 = "The server encountered"
            + " something unexpected that prevented it from completing the request.";
  public static final String MENSAJE_CODE_STATUS_501 = "Indicates that the server doesn't"
            + "support the method used in the request, and therefore it can't process the request.";
  public static final String MENSAJE_CODE_STATUS_503 = "Indicates that the server is unable"
            + " to complete the request due to a server overload.";
  public static final String MENSAJE_CODE_STATUS_504 = "The server acts as the gateway";

  public static final String SOLICITUD_MASIVA_OK_CODIGO_RESPUESTA = "0";
  public static final String SOLICITUD_MASIVA_OK_MENSAJE_RESPUESTA = "Exito";

  public static final String SOLICITUD_MASIVA_YAEXISTE_CODIGO_RESPUESTA = "-1";
  public static final String SOLICITUD_MASIVA_YAEXISTE_MENSAJE_RESPUESTA = "El Registro Poliza ya existe.";

  public static final Integer NUMERO_REGISTROS_AGRUPADOR_BUSQUEDA = 100;

  public static final String VALOR_REG = "REG";

  private Constants() {

  }
}
