package pe.com.pacifico.kuntur.expose.request;


import com.pacifico.kuntur.core.data.input.validation.RegularExpression;
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
@ApiModel(description = "ProductoRequest model")
@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Setter
@ToString
public class ProductoRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  @NotNull
  @NotBlank(message = "Input must not be empty")
  @ApiModelProperty(example = "0280")
  @Pattern(regexp = RegularExpression.ONLY_NUMBERS, message = "Input should be only numbers")
  private String idProducto;

  @Size(max = 30)
  @NotNull
  @NotBlank(message = "Input must not be empty")
  @ApiModelProperty(example = "Lampara")
  private String nombreProducto;

  @Size(max = 250)
  @NotNull
  @NotBlank(message = "Input must not be empty")
  @ApiModelProperty(example = "Lampara antigua color marron")
  private String descripcionProducto;

  @Size(max = 30)
  @NotNull
  @NotBlank(message = "Input must not be empty")
  @ApiModelProperty(example = "Hogar")
  private String categoria;

  @Size(max = 30)
  @NotNull
  @NotBlank(message = "Input must not be empty")
  @ApiModelProperty(example = "lampara.jpg")
  private String urlImagen;

  @Size(max = 30)
  @NotNull
  @NotBlank(message = "Input must not be empty")
  @Pattern(regexp = "[0-9,]+[^.]", message = "Input should be only numbers")
  private Long precio;

  @Size(max = 500)
  @NotNull
  @NotBlank(message = "Input must not be empty")
  @ApiModelProperty(example = "Lampara antigua color marron")
  private String descripcionLarga;
}
