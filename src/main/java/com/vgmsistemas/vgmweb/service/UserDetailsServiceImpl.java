package com.vgmsistemas.vgmweb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vgmsistemas.vgmweb.entity.Cliente;
import com.vgmsistemas.vgmweb.entity.PkCliente;
import com.vgmsistemas.vgmweb.entity.PkRolesUsuarios;
import com.vgmsistemas.vgmweb.entity.Rol;
import com.vgmsistemas.vgmweb.entity.RolesUsuarios;
import com.vgmsistemas.vgmweb.entity.Usuario;
import com.vgmsistemas.vgmweb.repository.ClienteRepo;
import com.vgmsistemas.vgmweb.repository.RolesUsuariosRepo;
import com.vgmsistemas.vgmweb.repository.UsuarioRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UsuarioRepo usuarioRepo;
	
	@Autowired
	RolesUsuariosRepo rolesUsuariosRepo;
	
	@Autowired
	ClienteRepo clienteRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// Buscar el usuario con el repositorio y si no existe lanzar una exepcion
		Usuario appUser = usuarioRepo.findByUsuario(username)
				.orElseThrow(() -> new UsernameNotFoundException("El usuario no existe"));
		
		if (appUser.getIdSucursal() == null || appUser.getIdCliente() == null || appUser.getIdComercio() == null ) {
			throw new UsernameNotFoundException("El usuario existe pero no está asignado a un cliente");
		}
		
		PkCliente pkCliente = new PkCliente();
		
		pkCliente.setIdSucursal(appUser.getIdSucursal());
		pkCliente.setIdCliente(appUser.getIdCliente());
		pkCliente.setIdComercio(appUser.getIdComercio());
		
		// Controlo que el cliente existe y esté activo, esto se da si encuentra en la vista.
		clienteRepo.findById(pkCliente)
				.orElseThrow(() -> new UsernameNotFoundException("El cliente no existe o esta deshabilitado")) ;

		// Mapear nuestra lista de Authority con la de spring security
		List grantList = getRoles(appUser);

		// Crear El objeto UserDetails que va a ir en sesion y retornarlo.
		UserDetails user = (UserDetails) new User(appUser.getUsuario(), appUser.getClave(), grantList);
		return user;
	}
	
	public UserDetails crearActualizarUsuario(Usuario entidad) throws UsernameNotFoundException {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
		UserDetails userDet;
		Optional<Usuario> user = usuarioRepo.findByUsuario(entidad.getUsuario());
        
        if(user.isPresent())
        {
            Usuario newUsuario= user.get();
            
            newUsuario.setNombre(entidad.getNombre());
            newUsuario.setEmail(entidad.getEmail());
            newUsuario.setTelefono(entidad.getTelefono());
            newUsuario.setClave(entidad.getClave());
 
            newUsuario = usuarioRepo.save(newUsuario) ;
            
            List grantList = getRoles(entidad);
            
            userDet = (UserDetails) new User(entidad.getUsuario(), entidad.getClave(), grantList);
            return userDet;
        } else {
        	
        	RolesUsuarios rolusuarioDefault = new RolesUsuarios();
        	PkRolesUsuarios pk = new PkRolesUsuarios();
        	
        	      	
        	
        	entidad.setClave(bCryptPasswordEncoder.encode(entidad.getClave()));
        	entidad = usuarioRepo.save(entidad);
        	
        	// Creo rol de usuario 
        	pk.setIdUsuario(entidad.getId());
        	pk.setIdRol(1);
        	
        	rolusuarioDefault.setId(pk);
        	
        	rolusuarioDefault = rolesUsuariosRepo.save(rolusuarioDefault);
        	
        	List grantList = new ArrayList();
        	GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_USER");
			grantList.add(grantedAuthority);
        	
			// Crear El objeto UserDetails que va a ir en sesion y retornarlo.
			userDet = (UserDetails) new User(entidad.getUsuario(), entidad.getClave(), grantList);
            return userDet;
        }
	}
	private List getRoles(Usuario appUser) {
		// Mapear nuestra lista de Authority con la de spring security
		List grantList = new ArrayList();
		for (Rol rol : appUser.getRoles()) {
			// ROLE_USER, ROLE_ADMIN,..
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(rol.getRol());
			grantList.add(grantedAuthority);
		}
		return grantList;
	}
}