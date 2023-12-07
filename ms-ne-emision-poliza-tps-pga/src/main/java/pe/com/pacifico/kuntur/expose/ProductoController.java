package pe.com.pacifico.kuntur.expose;

import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.pacifico.kuntur.business.ProductoService;
import pe.com.pacifico.kuntur.expose.request.ProductoCategoriaRequest;
import pe.com.pacifico.kuntur.expose.request.ProductoIdRequest;
import pe.com.pacifico.kuntur.expose.request.response.ProductoResponse;
import pe.com.pacifico.kuntur.model.Producto;

import javax.validation.Valid;

/**
 * This method is used to get only one exampleToBeObfuscated.
 * @author Bruno Castro
 * @return one example.
 */

@RestController
@RequestMapping("/producto")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
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
  @GetMapping("/id/{id}")
  public Producto getProductoPorId(@PathVariable String id) {
    return productoService.getProductoPorId(Long.parseLong(id));
  }

  /**
   * This method is used to get only one exampleToBeObfuscated.
   *
   * @return one example.
   */
  @GetMapping("/categoria/{categoria}")
  public ResponseEntity<List<ProductoResponse>> getProductosPorCategoria(@PathVariable String categoria) {

    return ResponseEntity.ok(productoService.getProductosPorCategoria(categoria).stream()
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
    pr.setDescripcionLarga(p.getDescripcionlarga());
    return pr;
  }

}
