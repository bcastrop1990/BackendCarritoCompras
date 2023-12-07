package pe.com.pacifico.kuntur.expose.request.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pe.com.pacifico.kuntur.business.util.Constantes;
import com.pacifico.kuntur.core.data.input.validation.RegularExpression;

/**
 * <b>Class</b>: ExampleResponse <br/>
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

@ApiModel(description = "Usuario response")
@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Setter
@ToString
public class UsuarioLoginResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  @NotNull
  @NotBlank(message = "Input must not be empty")
  @ApiModelProperty(example = "01")
  @Pattern(regexp = RegularExpression.ONLY_NUMBERS, message = "Input should be only numbers")
  private int idUsuario;

  @NotNull
  @NotBlank(message = "Input must not be empty")
  @ApiModelProperty(example = "01")
  @Pattern(regexp = RegularExpression.ONLY_NUMBERS, message = "Input should be only numbers")
  private String mensaje;

}
