package com.vgmsistemas.vgmweb.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vgmsistemas.vgmweb.entity.AccionesComDetalle;
import com.vgmsistemas.vgmweb.entity.PkAccionesComDetalle;

@Repository
public interface AccionesComDetalleRepo extends JpaRepository<AccionesComDetalle, PkAccionesComDetalle>{
	
	@Query("SELECT acd FROM AccionesComDetalle acd, AccionesGrupos ag, GrupoClientesDetalle gcd, AccionesCom ac, Articulo a "
			+ "WHERE ag.idGrupoClie = gcd.id.idGrupoClie AND "
			+ "ac.idAccionesCom = acd.id.idAccionesCom AND "
			+ "ag.idAccionesCom = acd.id.idAccionesCom AND "
			+ "acd.articulo.id = a.id AND "
			+ "acd.articulo.id = ?1 AND "
			+ "a.snActivo = 'S' AND "
			+ "gcd.cliente.id.idSucursal = ?2 AND " 
			+ "gcd.cliente.id.idCliente = ?3 AND "
			+ "gcd.cliente.id.idComercio = ?4 AND "
			+ "?5 BETWEEN ac.feVigenciaIni AND ac.feVigenciaFin AND "
			+ "ac.tiOrigen = ?6 AND "
			+ "ac.idTipoAcciones = ?7 AND "
			+ "(acd.rgLimiteInf IS NULL OR acd.rgLimiteInf <= ?9) AND "
			+ "(acd.rgLimiteSup IS NULL OR acd.rgLimiteSup > ?9 OR acd.rgLimiteSup = 0) AND "
			+ "(('P' = ?6 AND a.idProveedor = ?8 ) OR ('P' <> ?6))" )    
	public Optional<AccionesComDetalle> recoveryAccionPorArticulo(Long idArticulo, Long idSucursal, Long idCliente,
			Long idComercio, Date fechaActual,String tiOrigen, Integer tipoAccion, Long idProveedor,
			Float cantidadPedida);
			
}
