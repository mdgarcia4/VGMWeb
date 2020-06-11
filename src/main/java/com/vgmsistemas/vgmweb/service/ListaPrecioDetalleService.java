package com.vgmsistemas.vgmweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.vgmsistemas.vgmweb.entity.ListaPrecioDetalle;
import com.vgmsistemas.vgmweb.repository.ListaPrecioDetalleRepo;

@Service
public class ListaPrecioDetalleService {

	@Autowired
	ListaPrecioDetalleRepo listaPrecioDetalleRepo;

	
	
	public Page<ListaPrecioDetalle> getListaBySucursalAndLista(Long sucursal, Long lista, Integer pagNro, Integer pagTamanio, String ordenadoPor)  {
		
		 Pageable pagina = PageRequest.of(pagNro, pagTamanio, Sort.by(ordenadoPor));
		 
	        Page<ListaPrecioDetalle> listaPrecios= listaPrecioDetalleRepo.findListaBySucursalAndLista(sucursal, lista, pagina);
	         
	        /*if(articulos.hasContent()) {
	            return articulos.getContent();
	        } else {
	            return new ArrayList<Articulo>();
	        }*/
	        
	        return listaPrecios;
	}
	
	
}