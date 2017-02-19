package uo.sdi.acciones.util;

<<<<<<< HEAD

=======
>>>>>>> 08c59578178029b6c882960bb1af6f29f8afafe9
public class LongUtil {

    private LongUtil() {

    }

    /**
     * Convierte un objeto de tipo en String en uno de tipo Long.
     * 
     * @param cadena
     *            texto que se quiere convertir en Long
     * 
     * @return valor del objeto convertido, o null si no fue posible convetirlo
     * 
     */
    public static Long parseLong(String cadena) {
	try {
	    return Long.parseLong(cadena);
	}
	
	catch(NumberFormatException nfe) {
	    return null;
	}
    }

}