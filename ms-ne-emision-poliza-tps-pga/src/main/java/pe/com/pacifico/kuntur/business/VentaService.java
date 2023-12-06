package pe.com.pacifico.kuntur.business;

import pe.com.pacifico.kuntur.expose.request.VentaRequest;
import pe.com.pacifico.kuntur.expose.request.response.VentaResponse;

public interface VentaService {

  VentaResponse registrarVenta(VentaRequest request);
}
