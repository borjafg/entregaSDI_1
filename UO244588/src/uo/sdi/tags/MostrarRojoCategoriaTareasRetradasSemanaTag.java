package uo.sdi.tags;

import java.io.IOException;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import alb.util.date.DateUtil;

public class MostrarRojoCategoriaTareasRetradasSemanaTag extends
	SimpleTagSupport {

    private Date fecha_evaluada;
    private String nombreCategoria;

    public void setFecha_evaluada(Date fecha_evaluada) {
	this.fecha_evaluada = fecha_evaluada;
    }

    public void setNombreCategoria(String nombreCategoria) {
	this.nombreCategoria = nombreCategoria;
    }

    public void setTexto(String nombreCategoria) {
	this.nombreCategoria = nombreCategoria;
    }

    public void doTag() throws JspException, IOException {
	if (fecha_evaluada == null) {
	    getJspContext().getOut().write("------");
	}

	else if (fecha_evaluada.before(DateUtil.today())) {
	    StringBuilder sb = new StringBuilder();

	    sb.append("<p style=\"color:red\">");
	    sb.append(nombreCategoria);
	    sb.append("</p>");

	    getJspContext().getOut().write(sb.toString());
	}

	else {
	    getJspContext().getOut().write(nombreCategoria);
	}
    }

}