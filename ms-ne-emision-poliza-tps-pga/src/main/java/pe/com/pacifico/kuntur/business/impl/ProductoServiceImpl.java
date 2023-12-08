package pe.com.pacifico.kuntur.business.impl;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.pacifico.kuntur.business.ProductoService;
import pe.com.pacifico.kuntur.model.Producto;
import pe.com.pacifico.kuntur.repository.ProductoJpaRepository;

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
@Service
@Slf4j
public class ProductoServiceImpl implements ProductoService {

  private final ProductoJpaRepository productoJpaRepository;

  @Autowired
  public ProductoServiceImpl(ProductoJpaRepository productRepository) {

    this.productoJpaRepository = productRepository;
  }

  @Override
  public List<Producto> getAllProducts() {

    return productoJpaRepository.findAll();
  }

  @Override
  public Producto getProductoPorId(Long productId) {

    return productoJpaRepository.findById(productId).orElse(null);
  }

  @Override
  public List<Producto> getProductosPorCategoria(String category) {
    if ("Todo".equals(category)) {
      return productoJpaRepository.findAll();
    }
    List<Producto> productosTotales = new ArrayList<>();
    String[] categoryList = category.split(",");

    for (String categoryActual : categoryList) {
      List<Producto> productosPorCategory = productoJpaRepository.findByCategory(categoryActual.trim());
      productosTotales.addAll(productosPorCategory);
    }

    return productosTotales;
  }

  @Override
  public Producto agregarProducto(Producto producto) {
    return productoJpaRepository.save(producto);
  }

  @Override
  public Producto editarProducto(Long productId, Producto productoActualizado) {
    Producto productoExistente = productoJpaRepository.findById(productId).orElse(null);

    if (productoExistente != null) {
      productoExistente.setNombreProducto(productoActualizado.getNombreProducto());
      productoExistente.setDescripcionProducto(productoActualizado.getDescripcionProducto());
      productoExistente.setDescripcionLarga(productoActualizado.getDescripcionLarga());
      productoExistente.setPrecio(productoActualizado.getPrecio());
      productoExistente.setUrlImagen(productoActualizado.getUrlImagen());
      return productoJpaRepository.save(productoExistente);
    } else {
      return null;
    }
  }

  @Override
  public void borrarProducto(Long productId) {
    Producto productoExistente = productoJpaRepository.findById(productId).orElse(null);
    if (productoExistente != null) {
      productoJpaRepository.delete(productoExistente);
    }
  }
}
