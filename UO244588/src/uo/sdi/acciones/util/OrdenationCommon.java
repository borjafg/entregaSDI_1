package uo.sdi.acciones.util;

import java.util.Date;

import uo.sdi.model.Category;
import alb.util.date.DateUtil;

class OrdenationCommon {

    /**
     * Compara dos fechas dejando las fechas nulas para el final.
     */
    static int compareToWithNulls(Date fecha1, Date fecha2, boolean orderASC) {
	if (fecha1 == null) {
	    if (fecha2 != null) {
		return 1;
	    }

	    return 0;
	}

	else if (fecha2 == null) { // fecha1 != null
	    return -1;
	}

	if (orderASC) {
	    return compareToWithoutNullsASC(fecha1, fecha2);
	}

	else {
	    return compareToWithoutNullsDESC(fecha1, fecha2);
	}
    }

    /**
     * Ordena de más antigua a más reciente.
     */
    private static int compareToWithoutNullsASC(Date fecha1, Date fecha2) {
	// Fecha 1 más reciente que fecha 2 ===> +1
	if (DateUtil.isAfter(fecha1, fecha2)) {
	    return +1;
	}

	// Fecha 1 más antigua que fecha 2 ===> -1
	else if (DateUtil.isBefore(fecha1, fecha2)) {
	    return -1;
	}

	return 0;
    }

    /**
     * Ordena de más reciente a más antigua.
     */
    private static int compareToWithoutNullsDESC(Date fecha1, Date fecha2) {
	// Fecha 1 más reciente que fecha 2 ===> +1
	if (DateUtil.isAfter(fecha1, fecha2)) {
	    return +1;
	}

	// Fecha 1 más antigua que fecha 2 ===> -1
	else if (DateUtil.isBefore(fecha1, fecha2)) {
	    return -1;
	}

	return 0;
    }
    
    /**
     * Ordena por nombre de categoria ascendentemente
     */
    static int compareToWithNulls(Category o1, Category o2) {
	if(o1 == null) {
	    if(o2 == null) {
		return 0;
	    }
	    
	    return 1;
	}
	
	else { // o1 != null
	    if(o2 == null) {
		return -1;
	    }
	    
	    return o1.getName().compareTo(o2.getName());
	}
    }

}