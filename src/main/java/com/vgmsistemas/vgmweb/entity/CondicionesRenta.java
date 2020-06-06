package com.vgmsistemas.vgmweb.entity;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="CondicionesRenta")
@XmlAccessorType(XmlAccessType.FIELD)
public class CondicionesRenta {

	@XmlElement(name="CondicionRenta")
	private List<CondicionRenta> condicionesRenta;

	/**
	 * @return the condicionesRenta
	 */
	public List<CondicionRenta> getCondicionesRenta() {
		if(condicionesRenta == null){
			condicionesRenta = new ArrayList<CondicionRenta>();
		}
		return condicionesRenta;
	}

	/**
	 * @param condicionesRenta the condicionesRenta to set
	 */
	public void setCondicionesRenta(List<CondicionRenta> condicionesRenta) {
		this.condicionesRenta = condicionesRenta;
	}
	
}
