package pe.com.pacifico.kuntur.expose.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <b>Class</b>: DetailErrorEnum <br/>
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
@Getter
@AllArgsConstructor
public enum DetailErrorEnum {
  GENERIC_ERROR("000", "Error Generico: %s"),
  FIELD_ERROR("001", "Error de campo(s): %s");

  private String code;
  private String description;
}
