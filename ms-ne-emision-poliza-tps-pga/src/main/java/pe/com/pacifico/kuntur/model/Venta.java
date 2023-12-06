package pe.com.pacifico.kuntur.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "VENTA")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString
@Builder
public class Venta implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "idventa")
  private Long idVenta;

  @Column(name = "idusuario")
  private Long idUsuario;

  @Column(name = "fecventa")
  private LocalDateTime fecVenta;

  @Column(name = "montototal")
  private double montoTotal;


}
