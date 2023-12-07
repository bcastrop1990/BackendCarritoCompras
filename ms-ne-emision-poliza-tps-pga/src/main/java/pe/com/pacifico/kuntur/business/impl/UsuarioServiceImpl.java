package pe.com.pacifico.kuntur.business.impl;

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
import pe.com.pacifico.kuntur.expose.request.response.UsuarioLoginResponse;
import pe.com.pacifico.kuntur.expose.request.response.UsuarioResponse;
import pe.com.pacifico.kuntur.model.Usuario;
import pe.com.pacifico.kuntur.repository.UsuarioJpaRepository;

@Service
@Slf4j
public class UsuarioServiceImpl implements UsuarioService {

  private final UsuarioJpaRepository usuarioJpaRepository;

  @Autowired
  public UsuarioServiceImpl(UsuarioJpaRepository usuarioJpaRepository){
    this.usuarioJpaRepository = usuarioJpaRepository;
  }

  @Override
  public UsuarioLoginResponse login (UsuarioLoginRequest request) {
    UsuarioLoginResponse response = new UsuarioLoginResponse();
    Optional<Usuario> usuarioOptional = usuarioJpaRepository.buscarPorNombreUsuario(request.getUsername());
    if (usuarioOptional.isPresent()) {
      Usuario usuario = usuarioOptional.get();
      if(usuario.getPassword().equals(request.getContrasena())){
        //return ResponseEntity.ok(Constantes.MENSAJE_EXITO_LOGIN_EXITOSO );
        response.setMensaje(Constantes.MENSAJE_EXITO_LOGIN_EXITOSO);
        response.setIdUsuario(1);
        return response;
      }else{
        //return ResponseEntity.ok(MENSAJE_ERROR_LOGIN_FALLO);
        response.setMensaje(Constantes.MENSAJE_ERROR_LOGIN_FALLO);
        response.setIdUsuario(0);
        return response;
      }
     } else {
      //return ResponseEntity.ok(MENSAJE_ERROR_LOGIN_USUARIO_NO_EXISTE);
      response.setMensaje(Constantes.MENSAJE_ERROR_LOGIN_USUARIO_NO_EXISTE);
      response.setIdUsuario(0);
      return response;
    }
  }


  @Override
  public UsuarioLoginResponse guardarUsuario(UsuarioSaveRequest request) {
    UsuarioLoginResponse response = new UsuarioLoginResponse();
    Optional<Usuario> usuarioOptional = usuarioJpaRepository.buscarPorNombreUsuario(request.getUsername());
    if (usuarioOptional.isPresent()) {
      response.setIdUsuario(0);
      response.setMensaje(Constantes.MENSAJE_ERROR_USUARIO_EXISTE);
      return response;
    }else {
      Usuario usuario = new Usuario();
      usuario.setUsername(request.getUsername());
      usuario.setPassword(request.getContrasena());
      usuario.setCorreo(request.getEmail());
      usuario.setIdUsuario(usuarioJpaRepository.obtenerMaxIdUsuario());
      Usuario usuarioGuardado = usuarioJpaRepository.save(usuario);
      if (usuarioGuardado != null) {
        response.setIdUsuario(usuarioGuardado.getIdUsuario());
        response.setMensaje(Constantes.MENSAJE_EXITO_USUARIO_GUARDADO);
        return response;
      } else {
        response.setIdUsuario(0);
        response.setMensaje(Constantes.MENSAJE_ERROR_GUARDAR_USUARIO);
        return response;
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
