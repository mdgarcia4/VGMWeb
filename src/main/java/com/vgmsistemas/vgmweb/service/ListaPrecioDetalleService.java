package com.vgmsistemas.vgmweb.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
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

	
	
	public Page<ListaPrecioDetalle> getListaBySucursalAndLista(Long sucursal, Long lista, Integer pagNro, Integer pagTamanio, String ordenadoPor, Long rubro, Long subrubro, Long proveedor, Long marca)  {
		
		 Pageable pagina = PageRequest.of(pagNro, pagTamanio, Sort.by(ordenadoPor));
		 
	        Page<ListaPrecioDetalle> listaPrecios= listaPrecioDetalleRepo.findListaBySucursalAndLista(sucursal, lista, pagina);
	         
	        /*if(articulos.hasContent()) {
	            return articulos.getContent();
	        } else {
	            return new ArrayList<Articulo>();
	        }*/
	        
	        return controlImgArticulos(listaPrecios);
	}
	

	private Page<ListaPrecioDetalle> controlImgArticulos(Page<ListaPrecioDetalle> listado){
		String codBarraControl;
		ApplicationHome home = new ApplicationHome(this.getClass());
		File dirApp = home.getDir();
		String path = dirApp.toString() + "\\src\\main\\resources\\static\\img\\";
		for(ListaPrecioDetalle list : listado){
			codBarraControl = list.getArticulo().getIdCodigoBarras();
			File myFile = new File(path+codBarraControl+".jpg");
			//si no existe pongo imagen no disponible.
			if (!myFile.exists()) {
				list.getArticulo().setIdCodigoBarras("imagen_no_disp");
			}
		}
		return listado;
	}	

	public Page<ListaPrecioDetalle> getListaPrecio(Long sucursal, Long lista, Integer pagNro, Integer pagTamanio, String ordenadoPor, Long rubro, Long subrubro, Long proveedor, Long marca)  {
		
		 Pageable pagina = PageRequest.of(pagNro, pagTamanio, Sort.by(ordenadoPor));
		 Page<ListaPrecioDetalle> listaPrecios;
		 
		 if (rubro != 0 && subrubro != 0) {
			 listaPrecios = listaPrecioDetalleRepo.findListaBySucursalAndListaAndSubrubro(sucursal, lista, rubro, subrubro, pagina);
		 }
		 else if(proveedor != 0) {
			 listaPrecios = listaPrecioDetalleRepo.findListaBySucursalAndListaAndProveedor(sucursal, lista, proveedor, pagina);
		 }
		 else if(marca!=0) {
			 listaPrecios = listaPrecioDetalleRepo.findListaBySucursalAndListaAndMarca(sucursal, lista, marca, pagina);
		 }
		 else {
			 listaPrecios= listaPrecioDetalleRepo.findListaBySucursalAndLista(sucursal, lista, pagina);
	     }
	     	        
	    return controlImgArticulos(listaPrecios);
	}

}