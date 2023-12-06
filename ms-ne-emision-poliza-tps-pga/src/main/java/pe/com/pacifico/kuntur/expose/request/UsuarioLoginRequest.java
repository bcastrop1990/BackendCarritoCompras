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
import pe.com.pacifico.kuntur.business.util.Constantes;
import com.pacifico.kuntur.core.data.input.validation.RegularExpression;


@ApiModel(description = "Usuario model")
@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Setter
@ToString
public class UsuarioLoginRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  @NotNull
  @NotBlank(message = "Input must not be empty")
  @ApiModelProperty(example = "12345678")
  private String contrasena;

  @Size(max = 20)
  @NotNull
  @NotBlank(message = "Input must not be empty")
  @ApiModelProperty(example = "bcastro")
  private String username;



}
