package com.vgmsistemas.vgmweb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSendException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vgmsistemas.vgmweb.dto.Clave;
import com.vgmsistemas.vgmweb.entity.PkCliente;
import com.vgmsistemas.vgmweb.entity.PkRolesUsuarios;
import com.vgmsistemas.vgmweb.entity.Rol;
import com.vgmsistemas.vgmweb.entity.RolesUsuarios;
import com.vgmsistemas.vgmweb.entity.Usuario;
import com.vgmsistemas.vgmweb.repository.ClienteRepo;
import com.vgmsistemas.vgmweb.repository.RolesUsuariosRepo;
import com.vgmsistemas.vgmweb.repository.UsuarioRepo;
import com.vgmsistemas.vgmweb.util.ValidationTextUtil;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UsuarioRepo usuarioRepo;
	@Autowired
	RolesUsuariosRepo rolesUsuariosRepo;
	@Autowired
	ClienteRepo clienteRepo;
	@Autowired
	PropertiesService prepertyService;
	@Autowired
	EnvioMail mail;

	public static Integer ERROR_USUARIO_YA_RESGISTRADO = -1;
	public static Integer ERROR_USUARIO_RESGISTRADO_CON_MISMO_MAIL = -2;
	public static Integer USUARIO_VALIDO = 1;

	public static String PROBLEMAS = "ERROR";
	public static String SIN_PROBLEMAS = "OK";
	public static String ERROR_EMAIL_REQUERIDO = "El campo 'Email' es requerido.";
	public static String ERROR_USUARIO_REQUERIDO = "El campo 'Usuario' es requerido.";
	public static String ERROR_NOMBRE_REQUERIDO = "El campo 'Nombre' es requerido.";
	public static String ERROR_TELEFONO_REQUERIDO = "El campo 'Teléfono' es requerido.";
	public static String ERROR_CLAVE_REQUERIDO = "El campo 'Clave' es requerido.";
	public static String ERROR_CLAVE_OLD_REQUERIDO = "El campo 'Clave actual' es requerido.";
	public static String ERROR_CLAVE_NEW_REQUERIDO = "El campo 'Clave nueva' es requerido.";
	public static String ERROR_CLAVE_NEW_NOCOINCIDE = "El campo 'Clave"
			+ " nueva' y su repetición debe coincidir.";
	public static String ERROR_NOMBRE_FUERA_DE_RANGO = "El campo 'Nombre' debe ser mínimo de 6 a 50 caracteres.";
	public static String ERROR_USUARIO_FUERA_DE_RANGO = "El campo 'Usuario' debe ser mínimo de 6 a 30 caracteres.";
	public static String ERROR_TELEFONO_FUERA_DE_RANGO = "El campo 'Teléfono' debe ser mínimo de 8 a 20 caracteres.";
	public static String ERROR_EMAIL_FORMATO = "El campo 'Email' no tiene el formato correcto.";

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// Buscar el usuario con el repositorio y si no existe lanzar una exepcion
		Usuario appUser = usuarioRepo.findByUsuario(username)
				.orElseThrow(() -> new UsernameNotFoundException("El usuario no registrado."));

		if (appUser.getIdSucursal() == null || appUser.getIdCliente() == null || appUser.getIdComercio() == null) {
			throw new UsernameNotFoundException("El usuario existe pero no está asignado a un cliente");
		}

		PkCliente pkCliente = new PkCliente();

		pkCliente.setIdSucursal(appUser.getIdSucursal());
		pkCliente.setIdCliente(appUser.getIdCliente());
		pkCliente.setIdComercio(appUser.getIdComercio());

		// Controlo que el cliente existe y esté activo, esto se da si encuentra en la
		// vista.
		clienteRepo.findById(pkCliente)
				.orElseThrow(() -> new UsernameNotFoundException("El cliente no existe o esta deshabilitado"));

		// Mapear nuestra lista de Authority con la de spring security
		List<GrantedAuthority> grantList = getRoles(appUser);

		// Crear El objeto UserDetails que va a ir en sesion y retornarlo.
		UserDetails user = (UserDetails) new User(appUser.getUsuario(), appUser.getClave(), grantList);
		return user;
	}

	public UserDetails crearActualizarUsuario(Usuario entidad) throws UsernameNotFoundException {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
		UserDetails userDet;
		Optional<Usuario> user = usuarioRepo.findByUsuario(entidad.getUsuario());

		if (user.isPresent()) {
			Usuario newUsuario = user.get();
			// Validar Datos de usuario nuevo.
			String validacion = validarDatosUserNew(newUsuario);
			if (!validacion.equals(SIN_PROBLEMAS)) {
				throw new UsernameNotFoundException("Por favor verifique: " + validacion);
			}
			// Controlo el usuario registrado
			int controlUsuario = validarUserNewConUserBd(entidad, newUsuario);
			if (controlUsuario == ERROR_USUARIO_YA_RESGISTRADO) {
				throw new UsernameNotFoundException("Ya existe un usuario: " + entidad.getUsuario() + " registrado.");
			}
			if (controlUsuario == ERROR_USUARIO_RESGISTRADO_CON_MISMO_MAIL) {
				throw new UsernameNotFoundException(
						"Ya existe un usuario: " + entidad.getUsuario() + " registrado y tiene el mismo email.");
			}
			newUsuario.setNombre(entidad.getNombre());
			newUsuario.setEmail(entidad.getEmail());
			newUsuario.setTelefono(entidad.getTelefono());
			newUsuario.setClave(entidad.getClave());

			newUsuario = usuarioRepo.save(newUsuario);

			List<GrantedAuthority> grantList = getRoles(entidad);

			userDet = (UserDetails) new User(entidad.getUsuario(), entidad.getClave(), grantList);
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

			List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_USER");
			grantList.add(grantedAuthority);

			// Crear El objeto UserDetails que va a ir en sesion y retornarlo.
			userDet = (UserDetails) new User(entidad.getUsuario(), entidad.getClave(), grantList);
		}
		String mailEnvio = entidad.getEmail();
		if (mailEnvio != null && !mailEnvio.equals("")) {
			String asunto = prepertyService.getNameApp() + " - " + "Se ha registrado correctamente.";
			String cuerpoMail = "Se ha registrado correctamete. El usuario con el que se encuentra registrado este e-mail es " + entidad.getEmail();
			mandarMail(mailEnvio, asunto, cuerpoMail);
		}
		return userDet;
	}

	public String cambiarClave(Clave clave) throws UsernameNotFoundException {
		// valido clave
		String validacion = validarDatosPasswordNew(clave);
		if (!validacion.equals(SIN_PROBLEMAS)) {
			throw new UsernameNotFoundException("Por favor verifique: " + validacion);
		}
		// Buscar el usuario con el repositorio y si no existe lanzar una exepcion
		Usuario appUser = usuarioRepo.findByUsuario(clave.getUser())
				.orElseThrow(() -> new UsernameNotFoundException("El usuario no registrado."));

		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);

		appUser.setClave(bCryptPasswordEncoder.encode(clave.getNewPassword()));
		appUser = usuarioRepo.save(appUser);
		try {
			// Mandar mail de cambio de clave
			if (appUser != null) {
				String mailEnvio = appUser.getEmail();
				if (mailEnvio != null && !mailEnvio.equals("")) {
					String asunto = prepertyService.getNameApp() + " - " + "Se ha cambiado la contraseña.";
					String cuerpoMail = "La contraseña se cambiado correctamente.";
					mandarMail(mailEnvio, asunto, cuerpoMail);
				}
				return SIN_PROBLEMAS;
			}
			return PROBLEMAS;
		} catch (MailParseException mpe) {
			throw mpe;
		} catch (MailAuthenticationException mae) {
			throw mae;
		} catch (MailSendException mse) {
			throw mse;
		} catch (MailException me) {
			throw me;
		} catch (UsernameNotFoundException ex) {
			throw ex;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public String recuperarClave(Clave clave) {
		// valido el mail
		String validacion = validarEmailRecuperacion(clave);
		if (!validacion.equals(SIN_PROBLEMAS)) {
			throw new UsernameNotFoundException("Por favor verifique: " + validacion);
		}
		Usuario appUser = usuarioRepo.findByEmail(clave.getMail())
				.orElseThrow(() -> new UsernameNotFoundException("El usuario no registrado."));
		try {
			String newPassword = generarPasswordNew(appUser);
			//se genero clave nueva mando mail
			if (newPassword != null && !newPassword.equals("")) {
				String mailEnvio = clave.getMail();
				if (mailEnvio != null && !mailEnvio.equals("")) {
					String asunto = prepertyService.getNameApp() + " - " + "Se ha generado una nueva contraseña.";
					String cuerpoMail = "Se ha generado una nueva contraseña para ingresar. Su nueva contraseña es: "
							+ newPassword + ". Para cambiar la contraseña es necesario que haga login, ingrese a Mi Cuenta y presione el botón <<Cambiar Clave>>";
					mandarMail(mailEnvio, asunto, cuerpoMail);
				}
				return SIN_PROBLEMAS;
			}	
			return PROBLEMAS;
		} catch (MailParseException mpe) {
			throw mpe;
		} catch (MailAuthenticationException mae) {
			throw mae;
		} catch (MailSendException mse) {
			throw mse;
		} catch (MailException me) {
			throw me;
		} catch (UsernameNotFoundException ex) {
			throw ex;
		} catch (Exception e) {
			throw e;
		}
	}
	
	private String validarEmailRecuperacion(Clave clave) {
		// control requeridos
		if (clave.getMail() == null) {
			return ERROR_EMAIL_REQUERIDO;
		}
		// control validacion estructura
		if (!ValidationTextUtil.isEmailValido(clave.getMail())) {
			return ERROR_EMAIL_FORMATO;
		}
		return SIN_PROBLEMAS;
	}

	private List<GrantedAuthority> getRoles(Usuario appUser) {
		// Mapear nuestra lista de Authority con la de spring security
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		for (Rol rol : appUser.getRoles()) {
			// ROLE_USER, ROLE_ADMIN,..
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(rol.getRol());
			grantList.add(grantedAuthority);
		}
		return grantList;
	}

	private int validarUserNewConUserBd(Usuario userNew, Usuario userDB) {
		// control mismo usr y igual mail
		if (userNew.getUsuario().equals(userDB.getUsuario()) && userNew.getEmail().equals(userDB.getEmail())) {
			return ERROR_USUARIO_YA_RESGISTRADO;
		}
		// control mismo usr y distintos mail
		if (userNew.getUsuario().equals(userDB.getUsuario()) && !userNew.getEmail().equals(userDB.getEmail())) {
			return ERROR_USUARIO_YA_RESGISTRADO;
		}

		return USUARIO_VALIDO;
	}

	private String validarDatosUserNew(Usuario userNew) {
		// control requeridos
		if (userNew.getEmail() == null) {
			return ERROR_EMAIL_REQUERIDO;
		}
		if (userNew.getUsuario() == null) {
			return ERROR_USUARIO_REQUERIDO;
		}
		if (userNew.getNombre() == null) {
			return ERROR_NOMBRE_REQUERIDO;
		}
		if (userNew.getTelefono() == null) {
			return ERROR_TELEFONO_REQUERIDO;
		}
		if (userNew.getClave() == null) {
			return ERROR_CLAVE_REQUERIDO;
		}
		// control fuera de rango al ingreso
		if (userNew.getUsuario().length() < 6 && userNew.getUsuario().length() > 30) {
			return ERROR_USUARIO_FUERA_DE_RANGO;
		}
		if (userNew.getNombre().length() < 6 && userNew.getNombre().length() > 50) {
			return ERROR_NOMBRE_FUERA_DE_RANGO;
		}
		if (userNew.getTelefono().length() < 8 && userNew.getTelefono().length() > 20) {
			return ERROR_TELEFONO_FUERA_DE_RANGO;
		}
		// control validacion estructura
		if (!ValidationTextUtil.isEmailValido(userNew.getEmail())) {
			return ERROR_EMAIL_FORMATO;
		}
		return SIN_PROBLEMAS;
	}

	private String validarDatosPasswordNew(Clave clave) {
		// control requeridos
		if (clave.getNewPassword() == null) {
			return ERROR_CLAVE_NEW_REQUERIDO;
		}
		if (clave.getUser()== null) {
			return ERROR_USUARIO_REQUERIDO;
		}
		if (clave.getOldPassword() == null) {
			return ERROR_CLAVE_OLD_REQUERIDO;
		}
		// control fuera de rango al ingreso
		if (clave.getUser().length() < 6 && clave.getUser().length() > 30) {
			return ERROR_USUARIO_FUERA_DE_RANGO;
		}
		if (!clave.getNewPassword().equals(clave.getRepeatPassword())) {
			return ERROR_CLAVE_NEW_NOCOINCIDE;
		}
		return SIN_PROBLEMAS;
	}

	public String generarPasswordNew(Usuario appUser) throws UsernameNotFoundException {
		// Buscar el usuario en el repositorio y si no existe lanzar una exepcion
		String alfanumerico = "abcdefghijklmnopqrstuvwxyz";
		String claveAlfa = randomString(alfanumerico, 16);
		String numerico = "1234567890";
		String claveNro = randomString(numerico, 8);
		String passNew = claveNro + claveAlfa;
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);

		appUser.setClave(bCryptPasswordEncoder.encode(passNew));
		appUser = usuarioRepo.save(appUser);

		return passNew;

	}

	private String randomString(String chars, int length) {
		Random rand = new Random();
		StringBuilder buf = new StringBuilder();
		for (int i = 0; i < length; i++) {
			buf.append(chars.charAt(rand.nextInt(chars.length())));
		}
		return buf.toString();
	}
	
	private void mandarMail(String mailDestinatario, String asunto, String cuerpoMsj) {
		try {
			mail.sendEmail(mailDestinatario, asunto, cuerpoMsj);
		} catch (MailParseException mpe) {
			throw mpe;
		} catch (MailAuthenticationException mae) {
			throw mae;
		} catch (MailSendException mse) {
			throw mse;
		} catch (MailException me) {
			throw me;
		} catch (UsernameNotFoundException ex) {
			throw ex;
		} catch (Exception e) {
			throw e;
		}
	}
}