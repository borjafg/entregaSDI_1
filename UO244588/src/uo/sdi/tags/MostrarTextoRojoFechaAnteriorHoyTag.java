package uo.sdi.tags;

import java.io.IOException;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import alb.util.date.DateUtil;

public class MostrarTextoRojoFechaAnteriorHoyTag extends SimpleTagSupport {

    private Date fecha_evaluada;
    private Date fecha_finalizacion;
    private String texto;

    public void setFecha_evaluada(Date fecha_evaluada) {
	this.fecha_evaluada = fecha_evaluada;
    }

    public void setFecha_finalizacion(Date fecha_finalizacion) {
	this.fecha_finalizacion = fecha_finalizacion;
    }

    public void setTexto(String texto) {
	this.texto = texto;
    }

    public void doTag() throws JspException, IOException {
	if (fecha_evaluada == null) {
	    getJspContext().getOut().write(texto);
	}

	else if(fecha_finalizacion != null) {
	    getJspContext().getOut().write(texto);
	}
	
	else if (fecha_evaluada.before(DateUtil.today())) {
	    StringBuilder sb = new StringBuilder();

	    sb.append("<p style=\"color:red\">");
	    sb.append(texto);
	    sb.append("</p>");

	    getJspContext().getOut().write(sb.toString());
	}

	else {
	    getJspContext().getOut().write(texto);
	}
    }

}