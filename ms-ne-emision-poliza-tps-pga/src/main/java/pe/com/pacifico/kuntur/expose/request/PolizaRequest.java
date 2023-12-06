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
import pe.com.pacifico.kuntur.business.util.Constantes;



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
public class PolizaRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  @NotNull
  @NotBlank(message = "Input must not be empty")
  @ApiModelProperty(example = "0028280")
  @Pattern(regexp = RegularExpression.ONLY_NUMBERS, message = "Input should be only numbers")
  private String idConsAgen;

  @Size(min = 17, max = 17)
  @NotNull
  @NotBlank(message = "Input must not be empty")
  @ApiModelProperty(example = "20070131 00:00:00")
  private String fecVenta;

  @Size(max = 16)
  @NotNull
  @NotBlank(message = "Input must not be empty")
  @ApiModelProperty(example = "12.25")
  @Pattern(regexp = "^[0-9., ]+$", message = "Monto prima")
  private String primaBruta;

  @Size(max = 30)
  @NotNull
  @NotBlank(message = "Input must not be empty")
  @ApiModelProperty(example = "JOSE ALBERTO")
  @Pattern(regexp = "^[A-Za-z ]+$", message = "Nombre del asegurado")
  private String nomTer;

  @Size(max = 30)
  @ApiModelProperty(example = "PEREZ")
  @Pattern(regexp = "^$|[A-Za-z ]+$", message = "Apellido paterno")
  private String apeTer;

  @Size(max = 30)
  @ApiModelProperty(example = "RAMIREZ")
  @Pattern(regexp = "^$|[A-Za-z ]+$", message = "Apellido Materno")
  private String apeTer2;

  @Size(min = 8, max = 8)
  @NotNull
  @NotBlank(message = "Input must not be empty")
  @ApiModelProperty(example = "20070131")
  @Pattern(regexp = RegularExpression.ONLY_NUMBERS, message = "Fecha de nacimiento del asegurado")
  private String fecNac;

  @Size(min = 1, max = 1)
  @NotNull
  @NotBlank(message = "Input must not be empty")
  @ApiModelProperty(example = "M")
  @Pattern(regexp = "^$|[A-Za-z ]+$", message = "Indicador de Sexo del asegurado")
  private String sexo;

  @Size(min = 2, max = 2)
  @NotNull
  @NotBlank(message = "Input must not be empty")
  @ApiModelProperty(example = "01")
  @Pattern(regexp = RegularExpression.ONLY_NUMBERS, message = "Tipo documento identidad")
  private String tipoDocIden;

  @Size(max = 20)
  @NotNull
  @NotBlank(message = "Input must not be empty")
  @ApiModelProperty(example = "40589563")
  @Pattern(regexp = RegularExpression.ONLY_NUMBERS, message = "Número documento de identidad")
  private String numDocIden;

  @Size(max = 3)
  @NotNull
  @NotBlank(message = "Input must not be empty")
  @ApiModelProperty(example = "007")
  @Pattern(regexp = RegularExpression.ONLY_NUMBERS, message = "Código de plan")
  private String codPlan;

  @Size(max = 3)
  @NotNull
  @NotBlank(message = "Input must not be empty")
  @ApiModelProperty(example = "001")
  @Pattern(regexp = RegularExpression.ONLY_NUMBERS, message = "Revisión de plan")
  private String revPlan;

  @Size(max = 6)
  @ApiModelProperty(example = "CA")
  @Pattern(regexp = "^$|[A-Za-z ]+$", message = "Tipo calle para dirección")
  private String tipoCalle;

  @Size(max = 40)
  @ApiModelProperty(example = "SANTA CATALINA")
  @Pattern(regexp = "^$|[A-Z0-9a-z ]+$", message = "Calle dirección asegurado")
  private String calle;

  @Size(max = 10)
  @ApiModelProperty(example = "356")
  @Pattern(regexp = RegularExpression.ONLY_NUMBERS, message = "Numero de direccion")
  private String numero;

  @Size(max = 1)
  @ApiModelProperty(example = "D")
  @Pattern(regexp = "^$|[A-Za-z0-9.-]+$", message = "Tipo interior")
  private String tipoInt;

  @Size(max = 8)
  @ApiModelProperty(example = "203")
  @Pattern(regexp = "^$|[A-Za-z0-9.-]+$", message = "Interior de dirección")
  private String interior;

  @Size(max = 8)
  @ApiModelProperty(example = "3")
  @Pattern(regexp = "^$|[A-Za-z0-9-]+$", message = "Manzana de dirección")
  private String manzana;

  @Size(max = 8)
  @ApiModelProperty(example = "3")
  @Pattern(regexp = "^$|[A-Za-z0-9.-]+$", message = "Lote de dirección")
  private String lote;

  @Size(max = 8)
  @ApiModelProperty(example = "3")
  @Pattern(regexp = "^$|[A-Za-z0-9]+$", message = "Piso dirección")
  private String piso;

  @Size(max = 3)
  @ApiModelProperty(example = "RES")
  @Pattern(regexp = "^$|[A-Za-z0-9 ]+$", message = "Tipo urbanización")
  private String tipoUrb;

  @Size(max = 50)
  @ApiModelProperty(example = "LIMATAMBO")
  @Pattern(regexp = "^$|[A-Za-z0-9 -]+$", message = "Urbanización")
  private String urbanizacion;

  @Size(max = 1)
  @ApiModelProperty(example = "Z")
  @Pattern(regexp = "^$|[A-Za-z0-9 ]+$", message = "Tipo subdivisión")
  private String tipoSubDiv;

  @Size(max = 50)
  @ApiModelProperty(example = "")
  @Pattern(regexp = "^$|[A-Za-z0-9 ]+$", message = "Nombre Conjunto habitacional")
  private String subDiv;

  @Size(max = 250)
  @NotNull
  @NotBlank
  @ApiModelProperty(example = "CA SANTA CATALINA 356")
  @Pattern(regexp = "^$|[A-Za-z0-9 ]+$", message = "Dirección asegurado")
  private String direc;

  @Size(max = 50)
  @ApiModelProperty(example = "ALTURA CRUCE DE LA PANAMERICANA CON TOMAS VALLE")
  @Pattern(regexp = "^$|[A-Za-z0-9 ]+$", message = "Referencia domicilio")
  private String referencia;

  @NotNull
  @NotBlank
  @Size(max = 3)
  @ApiModelProperty(example = "001")
  @Pattern(regexp = RegularExpression.ONLY_NUMBERS, message = "Código Pais")
  private String codPais;

  @NotNull
  @NotBlank
  @Size(max = 3)
  @ApiModelProperty(example = "001")
  @Pattern(regexp = RegularExpression.ONLY_NUMBERS, message = "Código Departamento")
  private String codEstado;

  @NotNull
  @NotBlank
  @Size(max = 3)
  @ApiModelProperty(example = "001")
  @Pattern(regexp = RegularExpression.ONLY_NUMBERS, message = "Código Provincia")
  private String codCiudad;

  @NotNull
  @NotBlank
  @Size(max = 3)
  @ApiModelProperty(example = "009")
  @Pattern(regexp = RegularExpression.ONLY_NUMBERS, message = "Código Distrito")
  private String codMunicipio;

  @Size(max = 12)
  @ApiModelProperty(example = "984676603")
  @Pattern(regexp = RegularExpression.ONLY_NUMBERS, message = "Teléfono 1 del asegurado")
  private String telef1;

  @Size(max = 12)
  @ApiModelProperty(example = "984951603")
  @Pattern(regexp = RegularExpression.ONLY_NUMBERS, message = "Teléfono 2 del asegurado")
  private String telef2;

  @Size(max = 12)
  @ApiModelProperty(example = "985276603")
  @Pattern(regexp = RegularExpression.ONLY_NUMBERS, message = "Teléfono 3 del asegurado")
  private String telef3;

  @Size(max = 60)
  @ApiModelProperty(example = "example@gmail.com")
  @Pattern(regexp = Constantes.REGEXP_EMAIL, message = "Correo electrónico asegurado")
  private String email;

  @Size(min = 1, max = 16)
  @NotNull
  @NotBlank(message = "Input must not be empty")
  @ApiModelProperty(example = "508.50")
  @Pattern(regexp = "^(?!0+\\.00)(?=.{1,9}(\\.|$))(?!0(?!\\.))\\d{1,3}(,\\d{3})*(\\.\\d+)?$", message = "Suma asegurada")
  private String sumaAsegMoneda;

  @Size(min = 1, max = 1)
  @NotNull
  @NotBlank(message = "Input must not be empty")
  @ApiModelProperty(example = "1")
  @Pattern(regexp = RegularExpression.ONLY_NUMBERS, message = "Identificador de Canal")
  private String idenCanal;

  @Size(min = 1, max = 20)
  @NotNull
  @NotBlank(message = "Input must not be empty")
  @ApiModelProperty(example = "1")
  @Pattern(regexp = "^[A-Za-z0-9 ]+$", message = "Identificador de venta del socio (BCP)")
  private String idVentaExterna;
}
