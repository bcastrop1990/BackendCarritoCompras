package pe.com.pacifico.kuntur.business;

import java.util.List;
import pe.com.pacifico.kuntur.model.Producto;

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
public interface ProductoService {

  List<Producto> getAllProducts();

  Producto getProductoPorId(Long productId);

  List<Producto> getProductosPorCategoria(String category);

  Producto agregarProducto(Producto producto);

  Producto editarProducto(Long productId, Producto producto);

  void borrarProducto(Long productId);
}
