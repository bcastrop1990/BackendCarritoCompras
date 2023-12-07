package pe.com.pacifico.kuntur.business.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pe.com.pacifico.kuntur.business.UsuarioService;
import pe.com.pacifico.kuntur.business.util.Constantes;
import pe.com.pacifico.kuntur.expose.request.UsuarioLoginRequest;
import pe.com.pacifico.kuntur.expose.request.UsuarioSaveRequest;
import pe.com.pacifico.kuntur.expose.request.response.UsuarioResponse;
import pe.com.pacifico.kuntur.model.Usuario;
import pe.com.pacifico.kuntur.repository.UsuarioJpaRepository;

import static pe.com.pacifico.kuntur.business.util.Constantes.MENSAJE_ERROR_LOGIN_FALLO;
import static pe.com.pacifico.kuntur.business.util.Constantes.MENSAJE_ERROR_LOGIN_USUARIO_NO_EXISTE;
@Service
@Slf4j
public class UsuarioServiceImpl implements UsuarioService {

  private final UsuarioJpaRepository usuarioJpaRepository;

  @Autowired
  public UsuarioServiceImpl(UsuarioJpaRepository usuarioJpaRepository){
    this.usuarioJpaRepository = usuarioJpaRepository;
  }

  @Override
  public ResponseEntity login (UsuarioLoginRequest request) {

    Optional<Usuario> usuarioOptional = usuarioJpaRepository.buscarPorNombreUsuario(request.getUsername());
    if (usuarioOptional.isPresent()) {
      Usuario usuario = usuarioOptional.get();
      if(usuario.getPassword().equals(request.getContrasena())){
        //return ResponseEntity.ok(Constantes.MENSAJE_EXITO_LOGIN_EXITOSO );
        return ResponseEntity.ok("1");
      }else{
        //return ResponseEntity.ok(MENSAJE_ERROR_LOGIN_FALLO);
        return ResponseEntity.ok("0");
      }
     } else {
      //return ResponseEntity.ok(MENSAJE_ERROR_LOGIN_USUARIO_NO_EXISTE);
      return ResponseEntity.ok("0");
    }
  }


  @Override
  public ResponseEntity guardarUsuario(UsuarioSaveRequest request) {

    Optional<Usuario> usuarioOptional = usuarioJpaRepository.buscarPorNombreUsuario(request.getUsername());
    if (usuarioOptional.isPresent()) {
       return ResponseEntity.ok(Constantes.MENSAJE_ERROR_USUARIO_EXISTE);
    }else {
      Usuario usuario = new Usuario();
      usuario.setUsername(request.getUsername());
      usuario.setPassword(request.getContrasena());
      usuario.setCorreo(request.getEmail());
      usuario.setIdUsuario(usuarioJpaRepository.obtenerMaxIdUsuario());
      Usuario usuarioGuardado = usuarioJpaRepository.save(usuario);
      if (usuarioGuardado != null) {
        return ResponseEntity.ok(usuarioGuardado.getIdUsuario());
      } else {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( Constantes.MENSAJE_ERROR_GUARDAR_USUARIO);
      }
    }

  }


  @Override
  public UsuarioResponse buscarUsuarioById(Long idUsuario) {

    Optional<Usuario> usuario = usuarioJpaRepository.buscarPorId(idUsuario);
    UsuarioResponse response = new UsuarioResponse();
    usuario.ifPresent(u -> response.setIdUsuario(u.getIdUsuario()));
    usuario.ifPresent(u -> response.setUsername(u.getUsername()));
    usuario.ifPresent(u -> response.setEmail(u.getCorreo()));
    usuario.ifPresent(u -> response.setContrasena(u.getPassword()));

    return response;
  }

}
