package pe.com.pacifico.kuntur.expose.advice;

import com.pacifico.kuntur.core.errorhandling.ErrorCodeBuilder;
import com.pacifico.kuntur.core.errorhandling.ErrorConstant;
import com.pacifico.kuntur.core.errorhandling.ErrorResponse;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebInputException;
import pe.com.pacifico.kuntur.business.exception.PolizaValidationException;

/**
 * <b>Class</b>: ExposeControllerAdvice <br/>
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
@RestControllerAdvice
@Slf4j
public class ExposeControllerAdvice {

  /**
   * This method is used to get only one ErrorResponse.
   * @param exception This is the first paramter to method.
   * @return one ErrorResponse.
   */
  @ExceptionHandler(value = {ServerWebInputException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleWebInputException(ServerWebInputException exception) {
    String code = ErrorCodeBuilder.getErrorCode(
        ErrorConstant.Type.FUNCTIONAL,
        ErrorConstant.Layer.SUPPORT,
        ErrorConstant.FunctionalComponent.FORMAT,
        DetailErrorEnum.GENERIC_ERROR.getCode());

    return ErrorResponse
        .builder()
        .id(UUID.randomUUID().toString())
        .code(code)
        .type(ErrorConstant.Type.FUNCTIONAL.getDescription())
        .category(ErrorConstant.Category.ERROR)
        .description(String.format(DetailErrorEnum.GENERIC_ERROR.getDescription(),
            Optional.ofNullable(exception.getReason()).orElse("").split(":")[0]))
        .build();
  }

  /**
   * This method is used to get only one ErrorResponse.
   * @param exception This is the first paramter to method.
   * @return one ErrorResponse.
   */
  @ExceptionHandler(value = {WebExchangeBindException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleWebExchangeBindException(WebExchangeBindException exception) {
    String code = ErrorCodeBuilder.getErrorCode(
        ErrorConstant.Type.FUNCTIONAL,
        ErrorConstant.Layer.SUPPORT,
        ErrorConstant.FunctionalComponent.FORMAT,
        DetailErrorEnum.FIELD_ERROR.getCode());

    return ErrorResponse
        .builder()
        .id(UUID.randomUUID().toString())
        .code(code)
        .type(ErrorConstant.Type.FUNCTIONAL.getDescription())
        .category(ErrorConstant.Category.ERROR)
        .description(String.format(DetailErrorEnum.FIELD_ERROR.getDescription(), exception.getReason()))
        .errors(exception.getFieldErrors().stream()
            .map(fieldError -> String.format("%s: %s", fieldError.getField(), fieldError.getDefaultMessage()))
            .collect(Collectors.toList()))
        .build();
  }



  /**
   * This method is used to get only one ErrorResponse.
   * @param exception This is the first paramter to method.
   * @return one ErrorResponse.
   */
  @ExceptionHandler(value = {PolizaValidationException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handlePolizaValidationException(PolizaValidationException exception) {
    String code = ErrorCodeBuilder.getErrorCode(
        ErrorConstant.Type.FUNCTIONAL,
        ErrorConstant.Layer.SUPPORT,
        ErrorConstant.FunctionalComponent.FORMAT,
        DetailErrorEnum.FIELD_ERROR.getCode());

    return ErrorResponse
        .builder()
        .id(UUID.randomUUID().toString())
        .code(code)
        .type(ErrorConstant.Type.FUNCTIONAL.getDescription())
        .category(ErrorConstant.Category.ERROR)
        .description(exception.getMessage())
        .build();
  }

}
