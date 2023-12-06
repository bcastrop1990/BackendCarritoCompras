package pe.com.pacifico.kuntur.business.util;

import static pe.com.pacifico.kuntur.business.util.Constantes.LONGITUD_CARNET_EXTRANJERIA;
import static pe.com.pacifico.kuntur.business.util.Constantes.LONGITUD_DNI;
import static pe.com.pacifico.kuntur.business.util.Constantes.LONGITUD_RUC;
import static pe.com.pacifico.kuntur.business.util.Constantes.MENSAJE_EXCEPCION_FECHA_MAYOR_FECHA_ACTUAL;
import static pe.com.pacifico.kuntur.business.util.Constantes.MENSAJE_EXCEPCION_FORMATO_FECHA_INCORRECTO;
import static pe.com.pacifico.kuntur.business.util.Constantes.MENSAJE_EXCEPCION_VALIDACION_LONGITUD_NUMERO_DOCUMENTO;
import static pe.com.pacifico.kuntur.business.util.Constantes.TIPO_DOC_IDEN_CARNETEXTRANJERIA;
import static pe.com.pacifico.kuntur.business.util.Constantes.TIPO_DOC_IDEN_DNI;
import static pe.com.pacifico.kuntur.business.util.Constantes.TIPO_DOC_IDEN_RUC;
import static pe.com.pacifico.kuntur.business.util.Constantes.UTC_PERU;
import static pe.com.pacifico.kuntur.business.util.Constantes.VALOR_0;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.pacifico.kuntur.business.exception.PolizaValidationException;

/**
 * <b>Class</b>: ValidationUtil <br/>
 * <b>Copyright</b>: 2022 Pacifico Seguros - La Chakra <br/>.
 *
 * @author 2022 Pacífico Seguros - La Chakra <br/>
 * <u>Service Provider</u>: Soluciones Digitales <br/>
 * <u>Developed by</u>: Oro Sólido <br/>
 * <u>Changes:</u><br/>
 * <ul>
 *   <li>
 *     Noviembre 4, 2022 Creación de Clase.
 *   </li>
 * </ul>
 */
public class ValidationUtil {

  private ValidationUtil() {
  }

  /**
   * Validates existence in a list of options.
   *
   * @param value        Value to validate
   * @param validOptions List of valid options
   * @param message      Error message
   */
  public static void validateExistsInList(String value, List<String> validOptions, String message) {
    if (!validOptions.contains(value)) {
      throw new PolizaValidationException(message);
    }
  }

  /**
   * Validates existence in database.
   * @param value Value to validate
   * @param repository Repository interface
   * @param message Error message
   * @param <T> Entity type
   */
  public static <T> void validateExistsInDB(String value, JpaRepository<T, String> repository, String message) {
    if (!repository.existsById(value)) {
      throw new PolizaValidationException(message);
    }
  }

  /**
   * Validates existence in database.
   * @param value Value to validate
   * @param repository Repository interface
   * @param message Error message
   * @param <T> Entity type
   */
  public static <T> void validateOptionalExistsInDB(String value, JpaRepository<T, String> repository, String message) {
    if (value != null && !value.trim().isEmpty() && !repository.existsById(value)) {
      throw new PolizaValidationException(message);
    }
  }

  /**
   * Validates date format.
   * @param date Value to validate
   */
  public static void validateDateFormat(String format, String date) {

    SimpleDateFormat formatter = new SimpleDateFormat(format);
    formatter.setLenient(false);
    try {
      formatter.parse(date);
    } catch (ParseException e) {
      throw new PolizaValidationException(MENSAJE_EXCEPCION_FORMATO_FECHA_INCORRECTO + e.getMessage());
    }
  }
  /**
   * Validacion que fecha de venta no sea mayor al dia actual.
   * @param date Value to validate
   */

  public static void validateDateMayorFechaActual(String date) {

    Instant instant = Instant.now();
    if (DateTimeFormatter.ofPattern("yyyyMMdd").withZone(ZoneId.of(UTC_PERU)).format(instant).compareTo(date.substring(0,8)) < VALOR_0) {
      throw new PolizaValidationException(MENSAJE_EXCEPCION_FECHA_MAYOR_FECHA_ACTUAL);
    }

  }

  /**
   * Validacion de longitud del numero de documento.
   * @param numeroDocumento Value to validate
   */

  public static void validateLongitudNumeroDocumento(String tipoDocumento, String numeroDocumento) {

    if (tipoDocumento.equals(TIPO_DOC_IDEN_DNI) && numeroDocumento.length() != LONGITUD_DNI) {
      throw new PolizaValidationException(MENSAJE_EXCEPCION_VALIDACION_LONGITUD_NUMERO_DOCUMENTO);
    }
    if (tipoDocumento.equals(TIPO_DOC_IDEN_RUC) && numeroDocumento.length() != LONGITUD_RUC) {
      throw new PolizaValidationException(MENSAJE_EXCEPCION_VALIDACION_LONGITUD_NUMERO_DOCUMENTO);
    }
    if (tipoDocumento.equals(TIPO_DOC_IDEN_CARNETEXTRANJERIA) && numeroDocumento.length() != LONGITUD_CARNET_EXTRANJERIA) {
      throw new PolizaValidationException(MENSAJE_EXCEPCION_VALIDACION_LONGITUD_NUMERO_DOCUMENTO);

    }
  }
}
