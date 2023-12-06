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

@Entity
@Table(name = "USUARIOS")
@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Setter
@ToString

public class Usuario {
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "ID_USUARIO")
  private Long idUsuario;

  @Column(name = "CORREO")
  private String correo;

  @Column(name = "USUARIO")
  private String username;

  @Column(name = "CONTRASENA")
  private String password;
}
