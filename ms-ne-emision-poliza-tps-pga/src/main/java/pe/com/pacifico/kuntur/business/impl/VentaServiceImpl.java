package pe.com.pacifico.kuntur.business.impl;

import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.pacifico.kuntur.business.VentaService;
import pe.com.pacifico.kuntur.expose.request.VentaRequest;
import pe.com.pacifico.kuntur.expose.request.response.VentaResponse;
import pe.com.pacifico.kuntur.model.Venta;
import pe.com.pacifico.kuntur.repository.VentaJpaRepository;

@Service
@Slf4j
public class VentaServiceImpl implements VentaService {
  private final VentaJpaRepository ventaJpaRepository;

  @Autowired
  public VentaServiceImpl(VentaJpaRepository ventaJpaRepository){
    this.ventaJpaRepository = ventaJpaRepository;
  }

  @Override
  public VentaResponse registrarVenta(VentaRequest request) {
    Venta venta = new Venta();
    venta.setIdUsuario(request.getIdUsuario());
    venta.setFecVenta(LocalDateTime.now());
    venta.setMontoTotal(request.getMontoTotal());
    venta.setIdVenta(ventaJpaRepository.obtenerMaxIdTarea());

    Venta ventaGuardada = ventaJpaRepository.save(venta);
    VentaResponse response = new VentaResponse();
    response.setIdVenta(ventaGuardada.getIdVenta());
    response.setIdUsuario(ventaGuardada.getIdUsuario());
    response.setMontoTotal(ventaGuardada.getMontoTotal());
    return response;
  }



}
