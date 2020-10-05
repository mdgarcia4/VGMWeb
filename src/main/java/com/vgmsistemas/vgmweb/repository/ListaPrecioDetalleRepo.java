package com.vgmsistemas.vgmweb.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.vgmsistemas.vgmweb.entity.ListaPrecioDetalle;
import com.vgmsistemas.vgmweb.entity.PkListaPrecioDetalle;

@Repository
public interface ListaPrecioDetalleRepo extends JpaRepository<ListaPrecioDetalle, PkListaPrecioDetalle>  {
	
	@Query("SELECT l FROM ListaPrecioDetalle l,Articulo a "
    		+ " WHERE a.id = l.id.idArticulo "
    		+ "  AND a.snActivo ='S'"
    		+ "  and a.snWeb='S'"
    		+ "  AND l.id.idSucursal = ?1"
    		+ "  AND l.id.idLista = ?2")
    public Page<ListaPrecioDetalle> findListaBySucursalAndLista(Long sucursal, Long lista, Pageable pageable);
	
	@Query("SELECT l FROM ListaPrecioDetalle l,Articulo a "
    		+ " WHERE a.id = l.id.idArticulo "
    		+ "  AND a.snActivo ='S'"
    		+ "  and a.snWeb='S'"
    		+ "  AND l.id.idSucursal = ?1"
    		+ "  AND l.id.idLista = ?2"
    		+ "  AND a.subrubro.id.idRubro = ?3"
    		+ "  AND a.subrubro.id.idSubrubro = ?4")
    public Page<ListaPrecioDetalle> findListaBySucursalAndListaAndSubrubro(Long sucursal, Long lista, Long rubro, Long subrubro, Pageable pageable);
	
	@Query("SELECT l FROM ListaPrecioDetalle l,Articulo a "
    		+ " WHERE a.id = l.id.idArticulo "
    		+ "  AND a.snActivo ='S'"
    		+ "  and a.snWeb='S'"
    		+ "  AND l.id.idSucursal = ?1"
    		+ "  AND l.id.idLista = ?2"
    		+ "  AND a.idProveedor = ?3")
    public Page<ListaPrecioDetalle> findListaBySucursalAndListaAndProveedor(Long sucursal, Long lista, Long proveedor, Pageable pageable);
   
	@Query("SELECT l FROM ListaPrecioDetalle l,Articulo a "
    		+ " WHERE a.id = l.id.idArticulo "
    		+ "  AND a.snActivo ='S'"
    		+ "  and a.snWeb='S'"
    		+ "  AND l.id.idSucursal = ?1"
    		+ "  AND l.id.idLista = ?2"
    		+ "  AND a.marca.id = ?3")
    public Page<ListaPrecioDetalle> findListaBySucursalAndListaAndMarca(Long sucursal, Long lista, Long marca, Pageable pageable);
    
	@Query("SELECT l FROM ListaPrecioDetalle l,Articulo a "
    		+ " WHERE a.id = l.id.idArticulo "
    		+ "  AND a.snActivo ='S'"
    		+ "  and a.snWeb='S'"
    		+ "  AND l.id.idSucursal = ?1"
    		+ "  AND l.id.idLista = ?2"
    		+ "  AND (a.subrubro.id.idRubro = ?3 OR 0 = ?3 )"
    		+ "  AND (a.subrubro.id.idSubrubro = ?4 OR 0 = ?4 )"
    		+ "  AND (a.marca.id = ?5 OR 0 = ?5 )"
    		+ "  AND (a.idProveedor = ?6 OR 0 = ?6 )")
    public Page<ListaPrecioDetalle> findListaBySucListaRubroSubrubroMarcaProvedor(Long sucursal, Long lista, 
    			Long rubro, Long subrubro ,Long marca, Long proveedor, Pageable pageable);
	
	@Query("SELECT l FROM ListaPrecioDetalle l,Articulo a "
    		+ " WHERE a.id = l.id.idArticulo "
    		+ "  AND a.snActivo ='S'"
    		+ "  and a.snWeb='S'"
    		+ "  AND l.id.idSucursal = ?1"
    		+ "  AND l.id.idLista = ?2"
    		+ "  AND a.tiWebDestacados in ?3")
    public Page<ListaPrecioDetalle> findByTiWebDestacadosIn(Long sucursal, Long lista, List<String> arrTiWebDestacados, Pageable pageable);
    
	@Query("SELECT NEW com.vgmsistemas.vgmweb.entity.ListaPrecioDetalle("
					+ "l.id,"
					+ "l.precioSinIva,"
					+ "l.precioConIva,"
					+ "l.cantidadPorLista,"
					+ "l.cantidadVendida,"
					+ "l.snMovil,"
					+ "l.fechaVigenciaDesde,"
					+ "l.fechaVigenciaHasta,"
					+ "l.articulo,"
					+ "s.stock )"
			+ " FROM ListaPrecioDetalle l,Articulo a, Stock s "
    		+ " WHERE a.id = l.id.idArticulo "
    		+ "  AND a.id = s.idArticulos"
    		+ "  AND s.idDeposito = ?7"
    		+ "  AND a.snActivo ='S'"
    		+ "  and a.snWeb='S'"
    		+ "  AND l.id.idSucursal = ?1"
    		+ "  AND l.id.idLista = ?2"
    		+ "  AND (a.subrubro.id.idRubro = ?3 OR 0 = ?3 )"
    		+ "  AND (a.subrubro.id.idSubrubro = ?4 OR 0 = ?4 )"
    		+ "  AND (a.marca.id = ?5 OR 0 = ?5 )"
    		+ "  AND (a.idProveedor = ?6 OR 0 = ?6 )"
    		+ "  AND ((l.precioConIva between ?8 AND ?9) OR (0 = ?8 AND 0 = ?9))"
    		+ "  AND ((s.stock > 0 AND 'S' = ?10) OR ('N' = ?10 ))")
    public Page<ListaPrecioDetalle> findListaBySucListaRubroSubrubroMarcaProvedorStock(Long sucursal, Long lista, 
    			Long rubro, Long subrubro ,Long marca, Long proveedor, Long deposito, Double prMin, Double prMax, 
    			String soloConStock, Pageable pageable);
	
	@Query("SELECT NEW com.vgmsistemas.vgmweb.entity.ListaPrecioDetalle("
			+ "l.id,"
			+ "l.precioSinIva,"
			+ "l.precioConIva,"
			+ "l.cantidadPorLista,"
			+ "l.cantidadVendida,"
			+ "l.snMovil,"
			+ "l.fechaVigenciaDesde,"
			+ "l.fechaVigenciaHasta,"
			+ "l.articulo,"
			+ "s.stock )"
			+ " FROM ListaPrecioDetalle l,Articulo a, Stock s "
			+ " WHERE a.id = l.id.idArticulo "
			+ "  AND a.id = s.idArticulos"
			+ "  AND s.idDeposito = ?4"
    		+ "  AND a.snActivo ='S'"
    		+ "  and a.snWeb='S'"
    		+ "  AND l.id.idSucursal = ?1"
    		+ "  AND l.id.idLista = ?2"
    		+ "  AND a.tiWebDestacados in ?3")
	public Page<ListaPrecioDetalle> findByTiWebDestacadosInStock(Long sucursal, Long lista, 
			List<String> arrTiWebDestacados, Long deposito, Pageable pageable);
	
	@Query("SELECT NEW com.vgmsistemas.vgmweb.entity.ListaPrecioDetalle("
			+ "l.id,"
			+ "l.precioSinIva,"
			+ "l.precioConIva,"
			+ "l.cantidadPorLista,"
			+ "l.cantidadVendida,"
			+ "l.snMovil,"
			+ "l.fechaVigenciaDesde,"
			+ "l.fechaVigenciaHasta,"
			+ "l.articulo,"
			+ "s.stock )"
	+ " FROM ListaPrecioDetalle l,Articulo a, Stock s "
	+ " WHERE a.id = l.id.idArticulo "
	+ "  AND a.id = s.idArticulos"
	+ "  AND s.idDeposito = ?4"
	+ "  AND a.snActivo ='S'"
	+ "  and a.snWeb='S'"
	+ "  AND l.id.idSucursal = ?1"
	+ "  AND l.id.idLista = ?2"
	+ "  AND (Upper(a.descripcion) LIKE Upper('%'+ ?3 +'%' ))")
public Page<ListaPrecioDetalle> findDescripcionArticulo(Long sucursal, Long lista,String descripcion, Long deposito,Pageable pageable);
	    
}