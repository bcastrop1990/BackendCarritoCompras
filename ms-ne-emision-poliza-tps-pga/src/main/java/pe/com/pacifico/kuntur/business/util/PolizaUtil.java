package pe.com.pacifico.kuntur.business.util;

/**
 * This method is used to get only one example.
 *
 * @author 2022  Pacifico Seguros - La Chakra <br/>
 */

public class PolizaUtil {

  private PolizaUtil() {

  }

  /**
   * Este metodo se utiliza para: Valor, justificado a la derecha,
   * relleno de ceros a la izquierda y sin punto decimal.
   * Ejemplo:  00000000000001225 (12.25)
   */
  public static String numericToString16Caracteres(String numero) {

    StringBuilder sbInicio = new StringBuilder(16);
    StringBuilder sbFinal = new StringBuilder(16);

    if ((!numero.contains(".")) || (numero.contains(".") && numero.length() - 1 == numero.indexOf('.'))) {
      sbInicio.append(numero);
      sbInicio.append("00");
    } else if (numero.contains(".") && numero.length() - 2 == numero.indexOf('.')) {
      sbInicio.append(numero);
      sbInicio.append("0");
    } else {
      sbInicio.append(String.valueOf(Math.round(Double.parseDouble(numero.replace(",", "")) * 100.00) / 100.00));
      if (sbInicio.toString().length() - 2 == sbInicio.toString().indexOf('.')) {
        sbInicio.append("0");
      }
    }

    String sincoma = sbInicio.toString().replace(",", "");
    String sinpunto = sincoma.replace(".", "");

    if (sinpunto.length() < 16) {
      for (int i = 0; i < 16 - sinpunto.length(); i++) {
        sbFinal.append("0");
      }
      sbFinal.append(sinpunto);
    }
    return sbFinal.toString();
  }

  /**
   * Convierte el identificador de canal al código de intermediario.
   *
   * @param idenCanal Identificador de canal
   * @return código de intermediario
   */
  public static String convierteIdentificadorCanalACodigoIntermediario(String idenCanal) {
    switch (idenCanal) {
      case Constantes.IDENTIFICADOR_CANAL_AGENTE:
        return Constantes.CODIGO_INTERMEDIARIO_AGENTE;
      case Constantes.IDENTIFICADOR_CANAL_CAJERO_AUTOMATICO:
      default:
        return Constantes.CODIGO_INTERMEDIARIO_CAJERO_AUTOMATICO;
    }
  }


}
