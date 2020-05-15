package com.vgmsistemas.vgmweb.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Rubros")
@XmlAccessorType(XmlAccessType.FIELD)
public class Rubros implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7714551411495534694L;
	@XmlElement(name="Rubro")
	private List<Rubro> rubros;

	/**
	 * @return the rubros
	 */
	public List<Rubro> getRubros() {
		if(rubros == null){
			rubros = new ArrayList<Rubro>();
		}
		return rubros;
	}

	/**
	 * @param rubros the rubros to set
	 */
	public void setRubros(List<Rubro> rubros) {
		this.rubros = rubros;
	}
	
	
	
}
