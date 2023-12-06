package pe.com.pacifico.kuntur.expose.request;


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
import com.pacifico.kuntur.core.data.input.validation.RegularExpression;


/**
 * <b>Class</b>: PolizaRequest <br/>
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
@ApiModel(description = "PolizaRequest model")
@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Setter
@ToString
public class TareaUpdateRequest implements Serializable {

  private static final long serialVersionUID = 1L;


  @Size(min=1 , max = 10)
  @ApiModelProperty(example = "356")
  @Pattern(regexp = RegularExpression.ONLY_NUMBERS, message = "ID")
  private String idTarea;


  @Size(min = 1, max = 30)
  @NotNull
  @NotBlank(message = "Nombre de la tarea")
  @ApiModelProperty(example = "Actividad prueba test")
  @Pattern(regexp = "^$|[A-Z0-9a-z ]+$", message = "Descripcion de la tarea")
  private String nombreTarea;



  @Size(min = 1, max = 50)
  @NotNull
  @NotBlank(message = "Descripcion de la tarea")
  @ApiModelProperty(example = "Descripcion test")
  @Pattern(regexp = "^$|[A-Z0-9a-z ]+$", message = "Descripcion de la tarea")
  private String descripcion;

}
