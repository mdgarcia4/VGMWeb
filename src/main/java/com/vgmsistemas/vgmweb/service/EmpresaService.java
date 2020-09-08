package com.vgmsistemas.vgmweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vgmsistemas.vgmweb.entity.CategoriaFiscal;
import com.vgmsistemas.vgmweb.entity.Cliente;
import com.vgmsistemas.vgmweb.entity.Empresa;
import com.vgmsistemas.vgmweb.entity.PkDocumento;
import com.vgmsistemas.vgmweb.repository.EmpresaRepo;

@Service
public class EmpresaService {
	@Autowired
	EmpresaRepo empresaRepo;
	@Autowired
	PropertiesService propertyService;
	@Autowired
	SucursalService sucursalService;
	@Autowired
	DocumentoService documentoService;

	public Empresa getById(Long id) throws Exception{
		return empresaRepo.findById(id).get();
	}
	public Long getDepositoActivo(Empresa empresa, Cliente cliente)throws Exception{
		long idDeposito;
		if (empresa.getIsControlDesposito().equals("S")){ //stock por sucursal
			idDeposito = sucursalService.getById(cliente.getId().getIdSucursal()).getIdDeposito();
		}else if(empresa.getIsControlDesposito().equals(Empresa.CONTROL_DEPOSITO_X_COMPROBANTE)){
			PkDocumento id = new PkDocumento();
			id.setIdDocumento(propertyService.getIdDocWeb());
			id.setPuntoVenta(propertyService.getIdPtoVtaWeb());
			if (cliente.getCategoriaFiscal().getId().equals(CategoriaFiscal.INSCRIPTO)){
				id.setIdLetra("A");
			}else {
				id.setIdLetra("B");
			}				
			idDeposito = documentoService.getById(id).getIdN1();
		}else{	
			idDeposito = empresa.getIdSucactiva();
		}
		return Long.valueOf(idDeposito);
	}
}
