package pe.com.pacifico.kuntur.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <b>Class</b>: Sexo <br/>
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
@Entity
@Table(name = "PRODUCTO")
@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Setter
@ToString
public class Producto {
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "idproducto")
  private Long idProducto;

  @Column(name = "nombreproducto")
  private String nombreProducto;

  @Column(name = "descripcion")
  private String descripcion;

  @Column(name = "categoria")
  private String categoria;


  @Column(name = "precio")
  private Long precio;

  @Column(name = "urlimagen")
  private String urlImagen;
}
