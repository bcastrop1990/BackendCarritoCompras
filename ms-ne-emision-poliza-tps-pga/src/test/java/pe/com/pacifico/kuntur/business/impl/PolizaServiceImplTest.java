package pe.com.pacifico.kuntur.business.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.ResourceUtils;
import pe.com.pacifico.kuntur.business.exception.PolizaValidationException;
import pe.com.pacifico.kuntur.business.util.Constantes;
import pe.com.pacifico.kuntur.expose.request.PolizaRequest;
import pe.com.pacifico.kuntur.repository.*;

import java.io.IOException;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class PolizaServiceImplTest {

  @Mock
  PolizaJpaRepository polizaJpaRepository;

  @Mock
  SexoJpaRepository sexoJpaRepository;

  @Mock
  TipoDocumentoIdentidadJpaRepository tipoDocumentoIdentidadJpaRepository;

  @Mock
  TipoCalleJpaRepository tipoCalleJpaRepository;

  @Mock
  TipoInteriorJpaRepository tipoInteriorJpaRepository;


  @Mock
  TipoUrbanizacionJpaRepository tipoUrbanizacionJpaRepository;

  @Mock
  UbigeoJpaRepository ubigeoJpaRepository;

  @Mock
  PlanPrimaJpaRepository planPrimaJpaRepository;

  @Mock
  TipoSubDivisionJpaRepository tipoSubDivisionJpaRepository;

  @InjectMocks
  PolizaServiceImpl polizaService;

  ObjectMapper objectMapper = new ObjectMapper();

  @Test
  void shouldSavePolizaSuccessfully() throws IOException {
    Mockito.when(sexoJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoCalleJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoDocumentoIdentidadJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoInteriorJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoUrbanizacionJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoSubDivisionJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);


    Mockito.when(ubigeoJpaRepository.obtenerUbigeo(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(CodigoUbigeo.builder().build());

    Mockito.when(planPrimaJpaRepository.obtenerPrima(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(Optional.of(PlanPrima.builder().codInter("1").prima("12.05").build()));
    Mockito.when(polizaJpaRepository.save(ArgumentMatchers.any())).thenReturn(Poliza.builder().idPoliDet(1L).build());

    var file = ResourceUtils.getFile("classpath:requests/200_poliza_request.json");
    PolizaRequest request = objectMapper.readValue(file, PolizaRequest.class);

    String result = polizaService.savePoliza(request,"102030","192.168.1.120","aplicacionTest","usuarioTest","servicioTest");

    Assertions.assertThat(result).isNotNull().isNotEmpty().isEqualTo("1");
  }




  // El valor nulo y vacío es validado en el request con bean validation.
  @Test
  void shouldValidarSexoWhenElValorEsInvalido() throws IOException {

    var file = ResourceUtils.getFile("classpath:requests/400_poliza_request_sexo_invalido.json");
    PolizaRequest request = objectMapper.readValue(file, PolizaRequest.class);

    var exception = org.junit.jupiter.api.Assertions.assertThrows(PolizaValidationException.class, () -> polizaService.savePoliza(request ,"102030","192.168.1.120","aplicacionTest","usuarioTest", "servicioTest"));

    Assertions.assertThat(exception.getMessage()).isEqualTo(Constantes.MENSAJE_EXCEPCION_VALIDACION_SEXO);
  }

  // El valor nulo y vacío es validado en el request con bean validation.


  @Test
  void shouldValidarTipoCalleWhenEsVacio() throws IOException {
    Mockito.when(sexoJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoDocumentoIdentidadJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoInteriorJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoUrbanizacionJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(ubigeoJpaRepository.obtenerUbigeo(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(CodigoUbigeo.builder().build());
    Mockito.when(tipoSubDivisionJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(planPrimaJpaRepository.obtenerPrima(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(Optional.of(PlanPrima.builder().codInter("1").prima("12.05").build()));

    Mockito.when(polizaJpaRepository.save(ArgumentMatchers.any())).thenReturn(Poliza.builder().idPoliDet(1L).build());

    var file = ResourceUtils.getFile("classpath:requests/400_poliza_request_tipo_calle_vacio.json");
    PolizaRequest request = objectMapper.readValue(file, PolizaRequest.class);

    String result = polizaService.savePoliza(request,"102030","192.168.1.120","aplicacionTest","usuarioTest", "servicioTest");

    Assertions.assertThat(result).isNotNull().isNotEmpty().isEqualTo("1");
  }

  @Test
  void shouldValidarTipoCalleWhenEsInvalido() throws IOException {
    Mockito.when(sexoJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);

    var file = ResourceUtils.getFile("classpath:requests/400_poliza_request_tipo_calle_invalido.json");
    PolizaRequest request = objectMapper.readValue(file, PolizaRequest.class);

    var exception = org.junit.jupiter.api.Assertions.assertThrows(PolizaValidationException.class, () -> polizaService.savePoliza(request ,"102030","192.168.1.120","aplicacionTest","usuarioTest", "servicioTest"));

    Assertions.assertThat(exception.getMessage()).isEqualTo(Constantes.MENSAJE_EXCEPCION_VALIDACION_TIPO_CALLE);

  }

  // El valor nulo y vacío es validado en el request con bean validation.
  @Test
  void shouldValidarTipoDocumentoIdentWhenEsInvalido() throws IOException {
    Mockito.when(sexoJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoCalleJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);

    var file = ResourceUtils.getFile("classpath:requests/400_poliza_request_tipo_documento_identidad_invalido.json");
    PolizaRequest request = objectMapper.readValue(file, PolizaRequest.class);

    Mockito.when(sexoJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);

    var exception = org.junit.jupiter.api.Assertions.assertThrows(PolizaValidationException.class, () -> polizaService.savePoliza(request,"102030","192.168.1.120","aplicacionTest","usuarioTest", "servicioTest"));

    Assertions.assertThat(exception.getMessage()).isEqualTo(Constantes.MENSAJE_EXCEPCION_VALIDACION_TIPO_DOCUMENTO);

  }

  @Test
  void shouldValidarTipoInteriorWhenEsVacio() throws IOException {
    Mockito.when(sexoJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoCalleJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoDocumentoIdentidadJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoUrbanizacionJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(ubigeoJpaRepository.obtenerUbigeo(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(CodigoUbigeo.builder().build());
    Mockito.when(tipoSubDivisionJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);    Mockito.when(planPrimaJpaRepository.obtenerPrima(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(Optional.of(PlanPrima.builder().codInter("1").prima("12.05").build()));

    Mockito.when(polizaJpaRepository.save(ArgumentMatchers.any())).thenReturn(Poliza.builder().idPoliDet(1L).build());

    var file = ResourceUtils.getFile("classpath:requests/400_poliza_request_tipo_interior_vacio.json");
    PolizaRequest request = objectMapper.readValue(file, PolizaRequest.class);

    String result = polizaService.savePoliza(request,"102030","192.168.1.120","aplicacionTest","usuarioTest", "servicioTest");

    Assertions.assertThat(result).isNotNull().isNotEmpty().isEqualTo("1");

  }

  @Test
  void shouldValidarTipoInteriorWhenEsInvalido() throws IOException {
    Mockito.when(sexoJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoCalleJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoDocumentoIdentidadJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);

    var file = ResourceUtils.getFile("classpath:requests/400_poliza_request_tipo_interior_invalido.json");
    PolizaRequest request = objectMapper.readValue(file, PolizaRequest.class);

    var exception = org.junit.jupiter.api.Assertions.assertThrows(PolizaValidationException.class, () -> polizaService.savePoliza(request,"102030","192.168.1.120","aplicacionTest","usuarioTest", "servicioTest"));

    Assertions.assertThat(exception.getMessage()).isEqualTo(Constantes.MENSAJE_EXCEPCION_VALIDACION_TIPO_INTERIOR);

  }

  @Test
  void shouldValidarTipoUrbanizacionWhenEsVacio() throws IOException {
    Mockito.when(sexoJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoCalleJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoDocumentoIdentidadJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoInteriorJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(ubigeoJpaRepository.obtenerUbigeo(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(CodigoUbigeo.builder().build());
    Mockito.when(tipoSubDivisionJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(planPrimaJpaRepository.obtenerPrima(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(Optional.of(PlanPrima.builder().codInter("1").prima("12.05").build()));
    Mockito.when(polizaJpaRepository.save(ArgumentMatchers.any())).thenReturn(Poliza.builder().idPoliDet(1L).build());

    var file = ResourceUtils.getFile("classpath:requests/400_poliza_request_tipo_urbanizacion_vacio.json");
    PolizaRequest request = objectMapper.readValue(file, PolizaRequest.class);

    String result = polizaService.savePoliza(request,"102030","192.168.1.120","aplicacionTest","usuarioTest", "servicioTest");

    Assertions.assertThat(result).isNotNull().isNotEmpty().isEqualTo("1");

  }

  @Test
  void shouldValidarTipoUrbanizacionWhenEsInvalido() throws IOException {
    Mockito.when(sexoJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoCalleJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoDocumentoIdentidadJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoInteriorJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);

    var file = ResourceUtils.getFile("classpath:requests/400_poliza_request_tipo_urbanizacion_invalido.json");
    PolizaRequest request = objectMapper.readValue(file, PolizaRequest.class);

    var exception = org.junit.jupiter.api.Assertions.assertThrows(PolizaValidationException.class, () -> polizaService.savePoliza(request,"102030","192.168.1.120","aplicacionTest","usuarioTest", "servicioTest"));

    Assertions.assertThat(exception.getMessage()).isEqualTo(Constantes.MENSAJE_EXCEPCION_VALIDACION_TIPO_URBANIZACION);

  }

  // El valor nulo y vacío es validado en el request con bean validation.
  @Test
  void shouldValidarCodigoUbigeoWhenCodigoPaisInvalido() throws IOException {
    Mockito.when(sexoJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoCalleJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoDocumentoIdentidadJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoInteriorJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoUrbanizacionJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);

    var file = ResourceUtils.getFile("classpath:requests/400_poliza_request_codigo_ubigeo_codigo_pais_invalido.json");
    PolizaRequest request = objectMapper.readValue(file, PolizaRequest.class);

    var exception = org.junit.jupiter.api.Assertions.assertThrows(PolizaValidationException.class, () -> polizaService.savePoliza(request,"102030","192.168.1.120","aplicacionTest","usuarioTest", "servicioTest"));

    Assertions.assertThat(exception.getMessage()).isEqualTo(Constantes.MENSAJE_EXCEPCION_VALIDACION_CODIGO_UBIGEO);

  }

  // El valor nulo y vacío es validado en el request con bean validation.
  @Test
  void shouldValidarCodigoUbigeoWhenCodigoEstadoInvalido() throws IOException {
    Mockito.when(sexoJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoCalleJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoDocumentoIdentidadJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoInteriorJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoUrbanizacionJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);

    var file = ResourceUtils.getFile("classpath:requests/400_poliza_request_codigo_ubigeo_codigo_estado_invalido.json");
    PolizaRequest request = objectMapper.readValue(file, PolizaRequest.class);

    var exception = org.junit.jupiter.api.Assertions.assertThrows(PolizaValidationException.class, () -> polizaService.savePoliza(request,"102030","192.168.1.120","aplicacionTest","usuarioTest", "servicioTest"));

    Assertions.assertThat(exception.getMessage()).isEqualTo(Constantes.MENSAJE_EXCEPCION_VALIDACION_CODIGO_UBIGEO);

  }

  // El valor nulo y vacío es validado en el request con bean validation.
  @Test
  void shouldValidarCodigoUbigeoWhenCodigoCiudadInvalido() throws IOException {
    Mockito.when(sexoJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoCalleJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoDocumentoIdentidadJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoInteriorJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoUrbanizacionJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);

    var file = ResourceUtils.getFile("classpath:requests/400_poliza_request_codigo_ubigeo_codigo_ciudad_invalido.json");
    PolizaRequest request = objectMapper.readValue(file, PolizaRequest.class);

    var exception = org.junit.jupiter.api.Assertions.assertThrows(PolizaValidationException.class, () -> polizaService.savePoliza(request,"102030","192.168.1.120","aplicacionTest","usuarioTest", "servicioTest"));

    Assertions.assertThat(exception.getMessage()).isEqualTo(Constantes.MENSAJE_EXCEPCION_VALIDACION_CODIGO_UBIGEO);

  }

  // El valor nulo y vacío es validado en el request con bean validation.
  @Test
  void shouldValidarCodigoUbigeoWhenCodigoMunicipioInvalido() throws IOException {
    Mockito.when(sexoJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoCalleJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoDocumentoIdentidadJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoInteriorJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoUrbanizacionJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);

    var file = ResourceUtils.getFile("classpath:requests/400_poliza_request_codigo_ubigeo_codigo_municipio_invalido.json");
    PolizaRequest request = objectMapper.readValue(file, PolizaRequest.class);

    var exception = org.junit.jupiter.api.Assertions.assertThrows(PolizaValidationException.class, () -> polizaService.savePoliza(request,"102030","192.168.1.120","aplicacionTest","usuarioTest", "servicioTest"));

    Assertions.assertThat(exception.getMessage()).isEqualTo(Constantes.MENSAJE_EXCEPCION_VALIDACION_CODIGO_UBIGEO);

  }

  @Test
  void shouldValidarTipoSubDivisionWhenEsVacio() throws IOException {
    Mockito.when(sexoJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoCalleJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoDocumentoIdentidadJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoInteriorJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoUrbanizacionJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(ubigeoJpaRepository.obtenerUbigeo(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(CodigoUbigeo.builder().build());
    Mockito.when(planPrimaJpaRepository.obtenerPrima(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(Optional.of(PlanPrima.builder().codInter("1").prima("12.05").build()));
    Mockito.when(polizaJpaRepository.save(ArgumentMatchers.any())).thenReturn(Poliza.builder().idPoliDet(1L).build());

    var file = ResourceUtils.getFile("classpath:requests/400_poliza_request_tipo_sub_division_vacio.json");
    PolizaRequest request = objectMapper.readValue(file, PolizaRequest.class);
    String result = polizaService.savePoliza(request,"102030","192.168.1.120","aplicacionTest","usuarioTest", "servicioTest");
    Assertions.assertThat(result).isNotNull().isNotEmpty().isEqualTo("1");

  }

  @Test
  void shouldValidarTipoSubDivisionWhenEsInvalido() throws IOException {
    Mockito.when(sexoJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoCalleJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoDocumentoIdentidadJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoInteriorJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoUrbanizacionJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(ubigeoJpaRepository.obtenerUbigeo(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(CodigoUbigeo.builder().build());

    var file = ResourceUtils.getFile("classpath:requests/400_poliza_request_tipo_sub_division_invalido.json");
    PolizaRequest request = objectMapper.readValue(file, PolizaRequest.class);

    var exception = org.junit.jupiter.api.Assertions.assertThrows(PolizaValidationException.class, () -> polizaService.savePoliza(request,"102030","192.168.1.120","aplicacionTest","usuarioTest", "servicioTest"));

    Assertions.assertThat(exception.getMessage()).isEqualTo(Constantes.MENSAJE_EXCEPCION_VALIDACION_TIPO_SUB_DIVISION);

  }

  // El valor nulo y vacío es validado en el request con bean validation.
  @Test
  void shouldValidarPlanWhenEsInvalido() throws IOException {
    Mockito.when(sexoJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoCalleJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoDocumentoIdentidadJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoInteriorJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoUrbanizacionJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(ubigeoJpaRepository.obtenerUbigeo(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(CodigoUbigeo.builder().build());
    Mockito.when(tipoSubDivisionJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);

    var file = ResourceUtils.getFile("classpath:requests/400_poliza_request_plan_invalido.json");
    PolizaRequest request = objectMapper.readValue(file, PolizaRequest.class);

    var exception = org.junit.jupiter.api.Assertions.assertThrows(PolizaValidationException.class, () -> polizaService.savePoliza(request,"102030","192.168.1.120","aplicacionTest","usuarioTest", "servicioTest"));

    Assertions.assertThat(exception.getMessage()).isEqualTo(Constantes.MENSAJE_EXCEPCION_VALIDACION_PLAN_PRIMA);

  }

  // El valor nulo y vacío es validado en el request con bean validation.
  @Test
  void shouldValidarRevPlanWhenEsInvalido() throws IOException {
    Mockito.when(sexoJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoCalleJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoDocumentoIdentidadJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoInteriorJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoUrbanizacionJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(ubigeoJpaRepository.obtenerUbigeo(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(CodigoUbigeo.builder().build());
    Mockito.when(tipoSubDivisionJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);

    var file = ResourceUtils.getFile("classpath:requests/400_poliza_request_rev_plan_invalido.json");
    PolizaRequest request = objectMapper.readValue(file, PolizaRequest.class);

    var exception = org.junit.jupiter.api.Assertions.assertThrows(PolizaValidationException.class, () -> polizaService.savePoliza(request,"102030","192.168.1.120","aplicacionTest","usuarioTest", "servicioTest"));

    Assertions.assertThat(exception.getMessage()).isEqualTo(Constantes.MENSAJE_EXCEPCION_VALIDACION_PLAN_PRIMA);

  }

  @Test
  void shouldValidarIdentificadorCanalWhenEsInvalido() throws IOException {
    Mockito.when(sexoJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoCalleJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoDocumentoIdentidadJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoInteriorJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoUrbanizacionJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(ubigeoJpaRepository.obtenerUbigeo(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(CodigoUbigeo.builder().build());
    Mockito.when(tipoSubDivisionJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(planPrimaJpaRepository.obtenerPrima(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(Optional.of(PlanPrima.builder().prima("12.05").build()));

    var file = ResourceUtils.getFile("classpath:requests/400_poliza_request_indentificador_canal_invalido.json");
    PolizaRequest request = objectMapper.readValue(file, PolizaRequest.class);

    var exception = org.junit.jupiter.api.Assertions.assertThrows(PolizaValidationException.class, () -> polizaService.savePoliza(request,"102030","192.168.1.120","aplicacionTest","usuarioTest", "servicioTest"));

    Assertions.assertThat(exception.getMessage()).isEqualTo(Constantes.MENSAJE_EXCEPCION_VALIDACION_IDENTIFICADOR_CANAL);
  }
  @Test
  void shouldValidarFechaVentaMayorFechaActual() throws IOException {
    Mockito.when(sexoJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoCalleJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoDocumentoIdentidadJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoInteriorJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoUrbanizacionJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(ubigeoJpaRepository.obtenerUbigeo(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(CodigoUbigeo.builder().build());
    Mockito.when(tipoSubDivisionJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(planPrimaJpaRepository.obtenerPrima(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(Optional.of(PlanPrima.builder().prima("12.05").build()));

    var file = ResourceUtils.getFile("classpath:requests/400_poliza_request_fechaVenta_mayor_fecha_actual.json");
    PolizaRequest request = objectMapper.readValue(file, PolizaRequest.class);

    var exception = org.junit.jupiter.api.Assertions.assertThrows(PolizaValidationException.class, () -> polizaService.savePoliza(request,"102030","192.168.1.120","aplicacionTest","usuarioTest", "servicioTest"));

    Assertions.assertThat(exception.getMessage()).isEqualTo(Constantes.MENSAJE_EXCEPCION_FECHA_MAYOR_FECHA_ACTUAL);
  }

  @Test
  void shouldValidarLongitudNumeroDocumentoDni() throws IOException {
    Mockito.when(sexoJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoCalleJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoDocumentoIdentidadJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoInteriorJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoUrbanizacionJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(ubigeoJpaRepository.obtenerUbigeo(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(CodigoUbigeo.builder().build());
    Mockito.when(tipoSubDivisionJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(planPrimaJpaRepository.obtenerPrima(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(Optional.of(PlanPrima.builder().prima("12.05").build()));

    var file = ResourceUtils.getFile("classpath:requests/400_poliza_request_longitud_numdoc_dni.json");
    PolizaRequest request = objectMapper.readValue(file, PolizaRequest.class);

    var exception = org.junit.jupiter.api.Assertions.assertThrows(PolizaValidationException.class, () -> polizaService.savePoliza(request,"102030","192.168.1.120","aplicacionTest","usuarioTest", "servicioTest"));

    Assertions.assertThat(exception.getMessage()).isEqualTo(Constantes.MENSAJE_EXCEPCION_VALIDACION_LONGITUD_NUMERO_DOCUMENTO);
  }

  @Test
  void shouldValidarLongitudNumeroDocumentoRuc() throws IOException {
    Mockito.when(sexoJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoCalleJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoDocumentoIdentidadJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoInteriorJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoUrbanizacionJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(ubigeoJpaRepository.obtenerUbigeo(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(CodigoUbigeo.builder().build());
    Mockito.when(tipoSubDivisionJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(planPrimaJpaRepository.obtenerPrima(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(Optional.of(PlanPrima.builder().prima("12.05").build()));

    var file = ResourceUtils.getFile("classpath:requests/400_poliza_request_longitud_numdoc_ruc.json");
    PolizaRequest request = objectMapper.readValue(file, PolizaRequest.class);

    var exception = org.junit.jupiter.api.Assertions.assertThrows(PolizaValidationException.class, () -> polizaService.savePoliza(request,"102030","192.168.1.120","aplicacionTest","usuarioTest", "servicioTest"));

    Assertions.assertThat(exception.getMessage()).isEqualTo(Constantes.MENSAJE_EXCEPCION_VALIDACION_LONGITUD_NUMERO_DOCUMENTO);
  }

  @Test
  void shouldValidarLongitudNumeroDocumentoCarnetExtranjeria() throws IOException {
    Mockito.when(sexoJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoCalleJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoDocumentoIdentidadJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoInteriorJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoUrbanizacionJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(ubigeoJpaRepository.obtenerUbigeo(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(CodigoUbigeo.builder().build());
    Mockito.when(tipoSubDivisionJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(planPrimaJpaRepository.obtenerPrima(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(Optional.of(PlanPrima.builder().prima("12.05").build()));

    var file = ResourceUtils.getFile("classpath:requests/400_poliza_request_longitud_numdoc_carnet_extranjeria.json");
    PolizaRequest request = objectMapper.readValue(file, PolizaRequest.class);

    var exception = org.junit.jupiter.api.Assertions.assertThrows(PolizaValidationException.class, () -> polizaService.savePoliza(request,"102030","192.168.1.120","aplicacionTest","usuarioTest", "servicioTest"));

    Assertions.assertThat(exception.getMessage()).isEqualTo(Constantes.MENSAJE_EXCEPCION_VALIDACION_LONGITUD_NUMERO_DOCUMENTO);
  }

  @Test
  void shouldValidarApellidoRuc10ApellidoPaterno() throws IOException {
    Mockito.when(sexoJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoCalleJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoDocumentoIdentidadJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoInteriorJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoUrbanizacionJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(ubigeoJpaRepository.obtenerUbigeo(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(CodigoUbigeo.builder().build());
    Mockito.when(tipoSubDivisionJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(planPrimaJpaRepository.obtenerPrima(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(Optional.of(PlanPrima.builder().prima("12.05").build()));

    var file = ResourceUtils.getFile("classpath:requests/400_poliza_request_apellido_ruc_10_apellido_paterno.json");
    PolizaRequest request = objectMapper.readValue(file, PolizaRequest.class);

    var exception = org.junit.jupiter.api.Assertions.assertThrows(PolizaValidationException.class, () -> polizaService.savePoliza(request,"102030","192.168.1.120","aplicacionTest","usuarioTest", "servicioTest"));

    Assertions.assertThat(exception.getMessage()).isEqualTo(Constantes.MENSAJE_EXCEPCION_APELLIDOS_VACIO);
  }

  @Test
  void shouldValidarApellidoRuc10ApellidoMaterno() throws IOException {
    Mockito.when(sexoJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoCalleJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoDocumentoIdentidadJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoInteriorJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoUrbanizacionJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(ubigeoJpaRepository.obtenerUbigeo(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(CodigoUbigeo.builder().build());
    Mockito.when(tipoSubDivisionJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(planPrimaJpaRepository.obtenerPrima(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(Optional.of(PlanPrima.builder().prima("12.05").build()));

    var file = ResourceUtils.getFile("classpath:requests/400_poliza_request_apellido_ruc_10_apellido_materno.json");
    PolizaRequest request = objectMapper.readValue(file, PolizaRequest.class);

    var exception = org.junit.jupiter.api.Assertions.assertThrows(PolizaValidationException.class, () -> polizaService.savePoliza(request ,"102030","192.168.1.120","aplicacionTest","usuarioTest", "servicioTest"));

    Assertions.assertThat(exception.getMessage()).isEqualTo(Constantes.MENSAJE_EXCEPCION_APELLIDOS_VACIO);
  }

  @Test
  void shouldValidarApellidoDNIApellidoPaterno() throws IOException {
    Mockito.when(sexoJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoCalleJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoDocumentoIdentidadJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoInteriorJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoUrbanizacionJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(ubigeoJpaRepository.obtenerUbigeo(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(CodigoUbigeo.builder().build());
    Mockito.when(tipoSubDivisionJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(planPrimaJpaRepository.obtenerPrima(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(Optional.of(PlanPrima.builder().prima("12.05").build()));

    var file = ResourceUtils.getFile("classpath:requests/400_poliza_request_apellido_dni_apellido_paterno.json");
    PolizaRequest request = objectMapper.readValue(file, PolizaRequest.class);

    var exception = org.junit.jupiter.api.Assertions.assertThrows(PolizaValidationException.class, () -> polizaService.savePoliza(request,"102030","192.168.1.120","aplicacionTest","usuarioTest", "servicioTest"));

    Assertions.assertThat(exception.getMessage()).isEqualTo(Constantes.MENSAJE_EXCEPCION_APELLIDOS_VACIO);
  }


  @Test
  void shouldValidarApellidoDNIApellidoMaterno() throws IOException {
    Mockito.when(sexoJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoCalleJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoDocumentoIdentidadJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoInteriorJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoUrbanizacionJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(ubigeoJpaRepository.obtenerUbigeo(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(CodigoUbigeo.builder().build());
    Mockito.when(tipoSubDivisionJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(planPrimaJpaRepository.obtenerPrima(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(Optional.of(PlanPrima.builder().prima("12.05").build()));

    var file = ResourceUtils.getFile("classpath:requests/400_poliza_request_apellido_dni_apellido_materno.json");
    PolizaRequest request = objectMapper.readValue(file, PolizaRequest.class);

    var exception = org.junit.jupiter.api.Assertions.assertThrows(PolizaValidationException.class, () -> polizaService.savePoliza(request,"102030","192.168.1.120","aplicacionTest","usuarioTest", "servicioTest"));

    Assertions.assertThat(exception.getMessage()).isEqualTo(Constantes.MENSAJE_EXCEPCION_APELLIDOS_VACIO);
  }


  @Test
  void shouldValidarApellidoCarnetExtranjeriaApellidoPaterno() throws IOException {
    Mockito.when(sexoJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoCalleJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoDocumentoIdentidadJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoInteriorJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoUrbanizacionJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(ubigeoJpaRepository.obtenerUbigeo(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(CodigoUbigeo.builder().build());
    Mockito.when(tipoSubDivisionJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(planPrimaJpaRepository.obtenerPrima(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(Optional.of(PlanPrima.builder().prima("12.05").build()));

    var file = ResourceUtils.getFile("classpath:requests/400_poliza_request_apellido_carnet_extranjeria_apellido_paterno.json");
    PolizaRequest request = objectMapper.readValue(file, PolizaRequest.class);

    var exception = org.junit.jupiter.api.Assertions.assertThrows(PolizaValidationException.class, () -> polizaService.savePoliza(request,"102030","192.168.1.120","aplicacionTest","usuarioTest", "servicioTest"));

    Assertions.assertThat(exception.getMessage()).isEqualTo(Constantes.MENSAJE_EXCEPCION_APELLIDOS_VACIO);
  }


  @Test
  void shouldValidarApellidoCarnetExtranjeriaApellidoMaterno() throws IOException {
    Mockito.when(sexoJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoCalleJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoDocumentoIdentidadJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoInteriorJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(tipoUrbanizacionJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(ubigeoJpaRepository.obtenerUbigeo(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(CodigoUbigeo.builder().build());
    Mockito.when(tipoSubDivisionJpaRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Mockito.when(planPrimaJpaRepository.obtenerPrima(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(Optional.of(PlanPrima.builder().prima("12.05").build()));

    var file = ResourceUtils.getFile("classpath:requests/400_poliza_request_apellido_carnet_extranjeria_apellido_materno.json");
    PolizaRequest request = objectMapper.readValue(file, PolizaRequest.class);

    var exception = org.junit.jupiter.api.Assertions.assertThrows(PolizaValidationException.class, () -> polizaService.savePoliza(request,"102030","192.168.1.120","aplicacionTest","usuarioTest", "servicioTest"));

    Assertions.assertThat(exception.getMessage()).isEqualTo(Constantes.MENSAJE_EXCEPCION_APELLIDOS_VACIO);
  }
}
