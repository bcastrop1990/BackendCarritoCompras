package pe.com.pacifico.kuntur.expose;

import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.pacifico.kuntur.business.ProductoService;
import pe.com.pacifico.kuntur.expose.request.ProductoCategoriaRequest;
import pe.com.pacifico.kuntur.expose.request.ProductoIdRequest;
import pe.com.pacifico.kuntur.expose.request.response.ProductoResponse;
import pe.com.pacifico.kuntur.model.Producto;

/**
 * This method is used to get only one exampleToBeObfuscated.
 * @author Bruno Castro
 * @return one example.
 */

@RestController
@RequestMapping("/producto")
@Slf4j
public class ProductoController {

  private final ProductoService productoService;

  @Autowired
  public ProductoController(ProductoService productoService) {

    this.productoService = productoService;
  }

  @GetMapping
  public ResponseEntity<List<ProductoResponse>>  getAllProducts() {
    return ResponseEntity.ok(productoService.getAllProducts().stream().map(this::buildResponse).collect(Collectors.toList()));
  }

  /**
   * This method is used to get only one exampleToBeObfuscated.
   *
   * @return one example.
   */
  @GetMapping("/id")
  public Producto getProductoPorId(@RequestBody ProductoIdRequest request) {
    return productoService.getProductoPorId(request.getIdProducto());
  }

  /**
   * This method is used to get only one exampleToBeObfuscated.
   *
   * @return one example.
   */
  @GetMapping("/categoria")
  public ResponseEntity<List<ProductoResponse>> getProductosPorCategoria(@RequestBody ProductoCategoriaRequest request) {

    return ResponseEntity.ok(productoService.getProductosPorCategoria(request.getCategoria()).stream()
        .map(this::buildResponse).collect(Collectors.toList()));

  }


  private ProductoResponse buildResponse(Producto p) {
    ProductoResponse pr = new ProductoResponse();
    pr.setIdProducto(String.valueOf(p.getIdProducto()));
    pr.setPrecio(p.getPrecio());
    pr.setNombreProducto(p.getNombreProducto());
    pr.setCategoria(p.getCategoria());
    pr.setUrlImagen(p.getUrlImagen());
    pr.setDescripcionProducto(p.getDescripcion());
    return pr;
  }

}