package pe.com.pacifico.kuntur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.com.pacifico.kuntur.model.Venta;

public interface VentaJpaRepository extends JpaRepository<Venta, Long>  {
  @Query(value = "SELECT COALESCE(MAX(IDVENTA), 0) + 1 FROM ADMIN.VENTA u", nativeQuery = true)
  Long obtenerMaxIdTarea();
}
