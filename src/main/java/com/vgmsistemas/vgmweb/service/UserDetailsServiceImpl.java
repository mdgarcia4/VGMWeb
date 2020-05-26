package com.vgmsistemas.vgmweb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vgmsistemas.vgmweb.entity.PkRolesUsuarios;
import com.vgmsistemas.vgmweb.entity.Rol;
import com.vgmsistemas.vgmweb.entity.RolesUsuarios;
import com.vgmsistemas.vgmweb.entity.Usuario;
import com.vgmsistemas.vgmweb.repository.RolesUsuariosRepo;
import com.vgmsistemas.vgmweb.repository.UsuarioRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UsuarioRepo usuarioRepo;
	
	@Autowired
	RolesUsuariosRepo rolesUsuariosRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// Buscar el usuario con el repositorio y si no existe lanzar una exepcion
		com.vgmsistemas.vgmweb.entity.Usuario appUser = usuarioRepo.findByUsuario(username)
				.orElseThrow(() -> new UsernameNotFoundException("El usuairo no existe"));

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