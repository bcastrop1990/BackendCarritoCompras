package pe.com.pacifico.kuntur.business.util;

/**
 * <b>Class</b>: Constante <br/>
 * <b>Copyright</b>: 2022 Pacifico Seguros - La Chakra <br/>.
 *
 * @author 2022  Pacifico Seguros - La Chakra <br/>
 * <u>Service Provider</u>: Soluciones Digitales <br/>
 * <u>Developed by</u>: Oro solido <br/>
 * <u>Changes:</u><br/>
 * <ul>
 *   <li>
 *     September 7, 2022 Creación de Clase.
 *   </li>
 * </ul>
 */
public class Constantes {

  private Constantes() {
  }

  public static final  String S_YES = "S";
  public static final  String N_NO = "N";
  public static final  String VALOR_01 = "01";

  public static final  String VALOR_02 = "02";

  public static final  String VALOR_1 = "1";

  public static final  String VALOR_2 = "2";
  public static final  int VALOR_0 = 0;
  public static final String MENSAJE_EXCEPCION_VALIDACION_SEXO = "Sexo enviado inválido";
  public static final String MENSAJE_EXCEPCION_VALIDACION_PARENTESCO = "Código de parentesco inválido";
  public static final String MENSAJE_EXCEPCION_VALIDACION_TIPO_CALLE = "Tipo de calle inválido";
  public static final String MENSAJE_EXCEPCION_VALIDACION_TIPO_DOCUMENTO = "Tipo de documento de identidad inválido";
  public static final String MENSAJE_EXCEPCION_VALIDACION_TIPO_INTERIOR = "Tipo de interior inválido";
  public static final String MENSAJE_EXCEPCION_VALIDACION_TIPO_URBANIZACION = "Tipo de urbanización inválido";
  public static final String MENSAJE_EXCEPCION_VALIDACION_CODIGO_UBIGEO = "Código de ubigeo inválido";
  public static final String MENSAJE_EXCEPCION_VALIDACION_TIPO_SUB_DIVISION = "Tipo de sub división inválido";
  public static final String MENSAJE_EXCEPCION_VALIDACION_PLAN_PRIMA = "Código de plan o revPlan inválido";
  public static final String MENSAJE_EXCEPCION_VALIDACION_VALOR_PRIMA = "Monto de la prima inválido";

  public static final String MENSAJE_EXCEPCION_VALIDACION_INDICADOR_DEFECTO = "Identificador defecto inválido";
  public static final String MENSAJE_EXCEPCION_VALIDACION_IDENTIFICADOR_CANAL = "Identificador de canal inválido";
  public static final String MENSAJE_EXCEPCION_FECHA_MAYOR_FECHA_ACTUAL = "Fecha de Venta no puede ser mayor a fecha actual";
  public static final String MENSAJE_EXCEPCION_FORMATO_FECHA_INCORRECTO = "Formato de fecha incorrecto";
  public static final String MENSAJE_EXCEPCION_VALIDACION_TAREA_EXITE = "Tarea ya exite";
  public static final String MENSAJE_EXCEPCION_VALIDACION_TAREA_NO_EXISTE = "LA TAREA SELECCIONADA NO EXITE";
  public static final String MENSAJE_EXITO_UPDATE_TAREA = "LA TAREA SE ACTUALIZO CON EXITO.";
  public static final String MENSAJE_EXITO_UPDATE_DELETE_TAREA = "LA TAREA SE CAMBIO DE ESTADO.";
  public static final String MENSAJE_FALLO_UPDATE_DELETE_TAREA = "OCURRIO UN ERROR NO SE CAMBIO DE ESTADO.";
  public static final String MENSAJE_ERROR_USUARIO_EXISTE = "EL USUARIO YA EXITE.";
  public static final String MENSAJE_EXITO_USUARIO_GUARDADO = "USUARIO GUARDADO CORRECTAMENTE. ID: ";
  public static final String MENSAJE_ERROR_GUARDAR_USUARIO = "Error al guardar el usuario.";

  public static final String MENSAJE_EXITO_LOGIN_EXITOSO = "Inicio de sesion exitoso";
  public static final String MENSAJE_ERROR_LOGIN_FALLO = "Inicio de sesion fallo";

  public static final String MENSAJE_ERROR_LOGIN_USUARIO_NO_EXISTE = "Usuario no existe";

  public static final String FORMATO_FECHA = "yyyyMMdd";

  public static final String FORMATO_FECHA_HORA = "yyyyMMdd HH:mm:ss";
  public static final String MENSAJE_EXCEPCION_VALIDACION_LONGITUD_NUMERO_DOCUMENTO = "Longitud de documento de identidad inválido";
  public static final String MENSAJE_EXCEPCION_APELLIDOS_VACIO = "Apellido PATERNO ó MATERNO vacio";
  public static final String TIPO_DOC_IDEN_DNI = "01";
  public static final String TIPO_DOC_IDEN_RUC = "06";
  public static final String TIPO_DOC_IDEN_CARNETEXTRANJERIA = "03";

  public static final String TIPO_DOC_IDEN_RUC_20 = "20";
  public static final String TIPO_DOC_IDEN_RUC_10 = "10";
  public static final String VALOR_VACIO = "";
  public static final int LONGITUD_DNI = 8;
  public static final int LONGITUD_RUC = 11;
  public static final int LONGITUD_CARNET_EXTRANJERIA = 9;


  public static final String UTC_PERU = "UTC-5";

  public static final String IDENTIFICADOR_CANAL_AGENTE = "1";
  public static final String IDENTIFICADOR_CANAL_CAJERO_AUTOMATICO = "2";
  public static final String CODIGO_INTERMEDIARIO_AGENTE = "405458";
  public static final String CODIGO_INTERMEDIARIO_CAJERO_AUTOMATICO = "405464";

  public static final String REGEXP_EMAIL = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+"
      + "/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)"
      + "+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$";
}
