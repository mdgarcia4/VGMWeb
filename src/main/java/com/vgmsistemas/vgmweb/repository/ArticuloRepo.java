package com.vgmsistemas.vgmweb.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.vgmsistemas.vgmweb.entity.Articulo;

@Repository
public interface ArticuloRepo extends JpaRepository<Articulo, Long>  {
	
    public Page<Articulo> findBySnActivo(String snActivo,Pageable pageable);
    
    @Query("SELECT a FROM Articulo a, ListaPrecioDetalle l "
    		+ " WHERE a.id = l.id.idArticulo "
    		+ "  AND a.snActivo ='S'"
    		+ "  and a.snWeb='S'"
    		+ "  AND l.id.idSucursal = ?1"
    		+ "  AND l.id.idLista = ?2")
    public Page<Articulo> findArticuloBySucursalAndLista(Long sucursal, Long lista, Pageable pageable);
    
    @Query("SELECT a FROM Articulo a "
    		+ "WHERE a.subrubro.id.idRubro = ?1 "
    		+ "and a.subrubro.id.idSubrubro = ?2")
    public Page<Articulo> findArticuloByRubroAndSubrubro(Long rubro, Long subrubro, Pageable pageable);
    
    @Query("SELECT a FROM Articulo a "
    		+ "WHERE a.marca.id = ?1 ")
    public Page<Articulo> findArticuloByMarca(Long marca, Pageable pageable);
    
    @Query("SELECT a FROM Articulo a "
    		+ "WHERE a.idProveedor = ?1 ")
    public Page<Articulo> findArticuloByProveedor(Long proveedor, Pageable pageable);
    
    public Page<Articulo> findByDescripcionContainingIgnoreCase(String descripcion, Pageable pageable);
    
    public List<Articulo> findByTiWebDestacadosIn(List<String> tiWebDestacados);
    
}