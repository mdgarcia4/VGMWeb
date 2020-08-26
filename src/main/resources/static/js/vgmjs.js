/*cuando se cargar home*/
function load() {
	/*cargo items del carrito*/
	loadItemDwnCarrito();	
	/* si estoy en la pag de shopping cargo los items */
	if ($("#shopping-cart-content").length > 0) {
		loadItemsShoppingCart();
	};
	resguardarUrlLocal();
	let optionValue = localStorage.getItem("optionValue");
	if (optionValue === null) {
		optionValue = "es";
	};
	$("#idiomas option[value='"+ optionValue +"']").attr("selected",true);
	
	loadMemoryBackUp();
	/*cargo datos del comercio selecionado*/
	let comercioSelect = localStorage.getItem("comercioSeleccionado");
	if (comercioSelect === null){
		comercioSelectt = 0;
		localStorage.setItem('comercioSeleccionado', comercioSelect);
	};
	/*cargo el usuario logueado si estoy en categ*/
	if ($("#usuariologin").length > 0) {
		let userlogin = localStorage.getItem("userlogin");
		if (userlogin === null){
			userlogin = $("#usuariologueado").text();;
			localStorage.setItem('userlogin', userlogin);
		};
	};
	
};

function loadItemDwnCarrito(){
	var cbShopCart = JSON.parse(localStorage.getItem("cbShopCart"));
	if (cbShopCart === null){
		cbShopCart = [];
	};
	cbShopCart.forEach(function(item){
		var id = item.id;
		var lscantidad = precioString(item.cantidad);
		var lsdescripcion = item.descripcion;
		var lsprecio = precioString(item.precio);
		var lspreciototal = precioString(item.cantidad * item.precio);
		var lsimagensrc = item.srcimagen;

		cargaritemdwnhtml(id, lsimagensrc, lsdescripcion, lsprecio,lscantidad, lspreciototal);
	});
};

function cargaritemdwnhtml(id, asimagensrc, asdescripcion, asprecio, ascantidad, aspreciototal) {
	let cantidadexistente = 0
	if(document.getElementById("li-dwn-"+id)){
		/*Si existe */
		var cbShopCart = JSON.parse(localStorage.getItem("cbShopCart"));
		if (cbShopCart !== null){
			cbShopCart.forEach(function(item){
				if (item.id === id){
					cantidadexistente = item.cantidad;
				};
			});
		};
		if (cantidadexistente !==0 ) {
			let cantTotal = cantidadexistente + parseInt(ascantidad);
			let precioarticulo = parseFloat(parseFloat(asprecio).toFixed(2));
			aspreciototal = precioString(cantTotal * precioarticulo);
			ascantidad = parseInt(cantTotal);			
			$("#li-dwn-"+id).remove();
		}
	} 
	ascantidad = parseInt(ascantidad);
	let vaciar = 0;
	var htmlDinamico = '<li id="li-dwn-' + id + '">';	
		htmlDinamico += '<figure class="item-image">';
		htmlDinamico += '<a href="#"><img src="' + asimagensrc + '" alt="t-shirt" onerror="imgErrorLoad(this);" /></a>';
		htmlDinamico += '</figure>';
		htmlDinamico += '<div class="item-description">';
		htmlDinamico += '<h4><a href="#" class="item-name">' + asdescripcion + '</a></h4>';
		htmlDinamico += '<span ><strong>' + ascantidad + '</strong> item(s) - <strong>$ ' + asprecio + '</strong></span>';
    	htmlDinamico += '<span class="price"> $ ' + aspreciototal + '</span>';
    	htmlDinamico += '</div>';
		htmlDinamico += '<span id="li-dwn-' + id + '" class="delete fa fa-trash" onClick="borrarItemDwnCarritoVgm(' + id + ','+ vaciar +')"> </span>';
		htmlDinamico += '</li>';
		$("#dwnCarritoVgm").append(htmlDinamico);
		refreshCantPrDwnCarritoVgm();
}

function Pedido(id, descripcion, cantidad, precio,srcimagen) {
  this.id = id;
  this.descripcion = descripcion;
  this.cantidad = cantidad;
  this.precio = precio;
  this.srcimagen = srcimagen;
};

function MemoryBack(mpage,msize,morderby,mrubro,msubrubro,mproveedor,mmarca) {
	this.mpage = mpage;
	this.msize = msize;
	this.morderby = morderby;
	this.mrubro = mrubro;
	this.msubrubro = msubrubro;
	this.mproveedor = mproveedor;
	this.mmarca = mmarca;
};

/*carga items de la pag shoppingcart */
function loadItemsShoppingCart() {
	var cbShopCart = JSON.parse(localStorage.getItem("cbShopCart"));
	if (cbShopCart === null){
		cbShopCart = [];
	};
	cbShopCart.forEach(function(item){
		var id = item.id;
		var cantidad = item.cantidad;
		var descripcion = item.descripcion;
		var lsPrecio = precioString(item.precio);
		var lsPrecioxCant = precioString(item.precio * item.cantidad);
		var lsimagensrc = item.srcimagen;
		let vaciar = 0;
		var htmlDinamico = '<tr id="tr-item-' + id + '">';
		htmlDinamico += '<td data-product="product name" class="cart-fig">';
		htmlDinamico += '<figure>';
		htmlDinamico += '<img src="' + lsimagensrc + '" alt="" onerror="imgErrorLoad(this);" />';
		htmlDinamico += '</figure>';
		htmlDinamico += '<div class="cart-description">';
		htmlDinamico += '<h4>' + descripcion + '</h4>';
		htmlDinamico += '</div></td>';
		htmlDinamico += '<td data-price="price" class="cart-price-head"><span class="cart-price">$' + lsPrecio + '</span></td>';
		htmlDinamico += '<td data-quantity="quantity" class="cart-quantity">';
		htmlDinamico += '<input id="item-cant-' + item.id + '" type="text"  oninput="actualizarValorPrTotalItem(' + item.id + ')" placeholder="' + cantidad + '" required="" ></input>';
		htmlDinamico += '</td>';
		htmlDinamico += '<td data-total="total" class="cart-total"><span id="item-prtotal-' + item.id + '" class="cart-price">$' + lsPrecioxCant + '</span></td>';
		htmlDinamico += '<td class="cart-btn">';
		/*htmlDinamico += '<a href="#" class="trash"><i class="fa fa-trash"></i></a>';*/
		htmlDinamico += '<a id="li-dwn-' + id + '" onClick="borrarItemDwnCarritoVgm(' + id + ','+ vaciar +')" class="trash"><i class="fa fa-trash"></i></a>';
		/*htmlDinamico += '<a href="#" class="pencil"><span class="icon_pencil" aria-hidden="true"></span></a></td>';*/
		htmlDinamico += '</tr>';
		$("#itemShoppingCart").append(htmlDinamico);
		calcularTotales();
	});
};
/*Cargar la Memoria */
function loadMemoryBackUp() {
	var memoryBackUp = JSON.parse(localStorage.getItem("memoryBackUp"));
	if (memoryBackUp === null){
		memoryBackUp = [];
		let mpage = 1;
		let msize = 12;
		let morderby = 'articulo.descripcion';
		let mrubro = 0;
		let msubrubro = 0;
		let mproveedor = 0;
		let mmarca = 0;
		var newMemory = new MemoryBack(mpage,msize,morderby,mrubro,msubrubro,mproveedor,mmarca);	
		memoryBackUp.push(newMemory);
		localStorage.setItem("memoryBackUp", JSON.stringify(memoryBackUp));
	};	
};

function goToCatalog() {
	var memoryBackUp = JSON.parse(localStorage.getItem("memoryBackUp"));
	memoryBackUp.forEach(function(item){
		let mpage = item.mpage;
		let msize = item.msize;
		let morderby = item.morderby;
		let mrubro = item.mrubro;
		let msubrubro = item.msubrubro;
		let mproveedor = item.mproveedor;
		let mmarca = item.mmarca;
		window.location = "/categorias?page=" + mpage + "&size=" + msize + "&order=" + morderby +
						  "&rubro=" + mrubro + "&subrubro=" + msubrubro + "&proveedor=" + mproveedor +
						  "&marca=" + mmarca;

	});
};

function getpage(id) {
	let memoryBackUp = JSON.parse(localStorage.getItem("memoryBackUp"));
	memoryBackUp.forEach(function(item){
		item.mpage = id;
	});
	localStorage.setItem("memoryBackUp", JSON.stringify(memoryBackUp));
	goToCatalog();
};

function getsize(id) {
	let memoryBackUp = JSON.parse(localStorage.getItem("memoryBackUp"));
	memoryBackUp.forEach(function(item){
		item.msize = id;
	});
	localStorage.setItem("memoryBackUp", JSON.stringify(memoryBackUp));
	goToCatalog();
};

function getorder(id) {
	let memoryBackUp = JSON.parse(localStorage.getItem("memoryBackUp"));
	memoryBackUp.forEach(function(item){
		item.morderby = id;
	});
	localStorage.setItem("memoryBackUp", JSON.stringify(memoryBackUp));
	goToCatalog();
};

function getrubro(id) {
	let memoryBackUp = JSON.parse(localStorage.getItem("memoryBackUp"));
	memoryBackUp.forEach(function(item){
		item.mrubro = id;
	});
	localStorage.setItem("memoryBackUp", JSON.stringify(memoryBackUp));
	goToCatalog();
};

function getsubrubro(idrubro, idsubrubro) {
	let memoryBackUp = JSON.parse(localStorage.getItem("memoryBackUp"));
	memoryBackUp.forEach(function(item){
		item.mrubro = idrubro;
		item.msubrubro = idsubrubro;
	});
	localStorage.setItem("memoryBackUp", JSON.stringify(memoryBackUp));
	goToCatalog();
};

function getmarca(id) {
	let memoryBackUp = JSON.parse(localStorage.getItem("memoryBackUp"));
	memoryBackUp.forEach(function(item){
		item.mmarca = id;
	});
	localStorage.setItem("memoryBackUp", JSON.stringify(memoryBackUp));
	goToCatalog();
};

function getproveedor(id) {
	let memoryBackUp = JSON.parse(localStorage.getItem("memoryBackUp"));
	memoryBackUp.forEach(function(item){
		item.mproveedor = id;
	});
	localStorage.setItem("memoryBackUp", JSON.stringify(memoryBackUp));
	goToCatalog();
};

function goToAccount() {
	window.location="/myaccount"
}

/*agrego items al ddn-carrito*/
function agregarDwnCarritoVgm(id, isCombo,msj){
	var cantidad = 1;
	swal({
        title: "Cantidad",
        input: "text",
        showCancelButton: true,
        confirmButtonText: "Guardar",
        cancelButtonText: "Cancelar",
        inputValidator: nombre => {
            // Si el valor es válido, debes regresar undefined. Si no, una cadena
            if (!nombre) {
                return "Por favor ingrese una cantidad.";
            }
            else {
            	if (nombre === 0) {
                    return "Por favor ingrese una cantidad mayor a 0.";
                }
                else {
		            for(i=0;i<nombre.length;i++){
		                var code=nombre.charCodeAt(i);
		                    if(code<=48 || code>=57){          
		                    	return "Por favor ingrese sólo números.";
		                    }    
		              }
	                return undefined;
                }
            }
        }
    })
    .then(resultado => {
        if (resultado.value) {
			var cantidad = parseInt(resultado.value);
			let lscantidad = cantidad;
        	var lsdescripcion = $("#item-descr-"+id).text();
        	var precio = parseFloat(parseFloat($("#item-precio-"+id).text()).toFixed(2));
        	let lspreciototal = precioString(parseFloat(cantidad * precio.toFixed(2)));
        	var lsprecio = precioString(precio);/*$("#item-precio-"+id).text();*/
        	var ele_imagen = document.getElementById("item-fig-"+id);
        	var lsimagen =  ele_imagen.getAttribute("src");
			
			cargaritemdwnhtml(id, lsimagen, lsdescripcion, lsprecio,lscantidad, lspreciototal);
        	/*mando a agregar el item localstorage*/
        	localStorage.setItem("cantidadPedida", 0);
        	localStorage.setItem("cantidadPedida", cantidad);
        	addItem(id, isCombo,msj);
        	refreshCantPrDwnCarritoVgm();
        }
    });
	
}

/*agrego datos al localstorage*/
function addItem(id, isCombo,msj){
	
	/*if (localStorage.getItem("nombre") === null) {
		swal({
		  type: 'error',
		  title: msj,
		  text: 'Debe estar logueado para poder ingresar pedidos',
		  footer: '<a class="text-left" href="register.html">Registrarse |</a><a class="text-right" href="login.html"> Iniciar Sesión</a>',
		});
		return;
	};*/

	let cantidad = parseInt(localStorage.getItem("cantidadPedida"));
	localStorage.setItem("cantidadPedida", 0);//limpio la variable
	let descripcion = $("#item-descr-"+id).text();
	let precio = parseFloat(parseFloat($("#item-precio-"+id).text()).toFixed(2));
	let ele_imagen = document.getElementById("item-fig-"+id);
	let lsimagen =  ele_imagen.getAttribute("src");
	if(isCombo === "N"){
		var cbShopCart = JSON.parse(localStorage.getItem("cbShopCart"));
	}else{
		var cbShopCart = JSON.parse(localStorage.getItem("cbShopCart-combos"));
	};
	if (cbShopCart === null){
		cbShopCart = [];
	};
	let find = false;
	cbShopCart.forEach(function(item){
		if (item.id === id){
			item.cantidad += cantidad;
			find = true;
		};
	});
	if (find === false){
		if(isCombo === 'N'){
			var nuevoPedido = new Pedido(id, descripcion, cantidad, precio, lsimagen);
		}else{
			var artBonif = [];
			combos = JSON.parse(localStorage.getItem('cb-combos'));
			combos.forEach(function(combo){
				if (combo.id === id){
					combo.articulosPromocion.forEach(function(articulo){
						var negocio = articulo.articulo.subrubro.id.idNegocio;
						var rubro = articulo.articulo.subrubro.id.idRubro;
						var subrubro = articulo.articulo.subrubro.id.idSubrubro;
						var cantidad = articulo.unidades;
						artBonif.push(new ArticuloBonificado(negocio,rubro,subrubro,cantidad));
					});
				};
			});
			var nuevoPedido = new PedidoCombo(id, descripcion, cantidad, precio, artBonif)
		};
		cbShopCart.push(nuevoPedido);
	}
	if(isCombo === 'N'){
		localStorage.setItem("cbShopCart", JSON.stringify(cbShopCart));
	} else{
		localStorage.setItem("cbShopCart-combos", JSON.stringify(cbShopCart));
	};
	swal({
	  position: 'top-end',
	  type: 'success',
	  html: `Se sumaron al carrito <b>${cantidad}</b> Item(s)<br> de<br> <a href="#">${descripcion}</a> `,
	  showConfirmButton: false,
	  timer: 1500
	});
};

/*borro completo el carrito*/
function vaciarCarrito(){
	swal({
		title: '¿Desea limpiar carrito?',
		text: "No podrás revertir esta acción",
		type: 'warning',
		showCancelButton: true,
		confirmButtonColor: '#333333',/*#3085d6*/
		/*cancelButtonColor: '#d33',*/
        confirmButtonText: "Sí, limpiar",
        cancelButtonText: "Cancelar"
	  }).then(resultado => {
        if (resultado.value) {
            // Hicieron click en "Sí"
            var cbShopCart = JSON.parse(localStorage.getItem("cbShopCart"));
			cbShopCart.forEach(function(item){
				borrarItemDwnCarritoVgm(item.id,1);/*1: no generna msj*/
			});
			localStorage.removeItem("cbShopCart");
			load();
        } else {
            // Dijeron que no
            return;
        }
    });	
};
/*Borra un elemento de dropdown del carrito*/
function borrarItemDwnCarritoVgm(id,vaciar) {
	let idborrar = id
	if(vaciar === 0){
		swal({
			title: '¿Desea eliminar este Item?',
			text: "No podrás revertir esta acción",
			type: 'warning',
			showCancelButton: true,
			confirmButtonColor: '#333333',/*#3085d6*/
			/*cancelButtonColor: '#d33',*/
	        confirmButtonText: "Sí, eliminar",
	        cancelButtonText: "Cancelar"
		  }).then(resultado => {
	        if (resultado.value) {
	            // Hicieron click en "Sí"
	        	$("#li-dwn-"+idborrar).remove();
	        	/* si estoy en la pag de shopping cargo los items */
	        	if ($("#shopping-cart-content").length > 0) {
	        		$("#tr-item-"+idborrar).remove();
	        	};
	        	deleteStorage(idborrar);
	        	refreshCantPrDwnCarritoVgm();
	        } else {
	            // Dijeron que no
	            return;
	        }
		  });
	}else{
		// Hicieron click en "Sí"
    	$("#li-dwn-"+idborrar).remove();
    	/* si estoy en la pag de shopping cargo los items*/ 
    	if ($("#shopping-cart-content").length > 0) {
    		$("#tr-item-"+idborrar).remove();
    	};
    	deleteStorage(idborrar);
    	refreshCantPrDwnCarritoVgm();
	}
};

/*quitar de storage por id*/
function deleteStorage(id) {
	var indice = 0;
	var indice_borrar = -1;
	var cbShopCart = JSON.parse(localStorage.getItem('cbShopCart'));

	cbShopCart.forEach(function(item){
		if (id === item.id){
			indice_borrar = indice;
		}
		indice += 1; 
	});
	if (indice_borrar !== -1){
		cbShopCart.splice(indice_borrar,1);
	};
	localStorage.setItem('cbShopCart',JSON.stringify(cbShopCart));
}

function refreshCantPrDwnCarritoVgm() {
	var cantidad = 0;
	var precio = 0;
	var cbShopCart = JSON.parse(localStorage.getItem("cbShopCart"));
	if (cbShopCart !== null){
		cbShopCart.forEach(function(item){
			cantidad += parseInt(item.cantidad,10);
			precio += parseFloat(item.precio * item.cantidad);
		});	
	};
	var cbShopCartCombos = JSON.parse(localStorage.getItem("cbShopCart-combos"));
	if (cbShopCartCombos !== null){
		cbShopCartCombos.forEach(function(item){
			cantidad += parseInt(item.cantidad,10);
			precio += parseFloat(parseFloat(item.precio).toFixed(2));
		});
	};
	/*remuevo el span para volver a cargarlo*/
	$("#cantPrCarritoVgmItem").remove();
	$("#icono1CantPrCarritoVgm").remove();
	$("#totDwnCarritoVgmItem").remove();
	if (cantidad !== 0) {
		var htmlDinamico = '<i id="icono1CantPrCarritoVgm" class="icon_bag_alt"></i>';	
		htmlDinamico += '<span id="cantPrCarritoVgmItem" class="item-count"> ';
		htmlDinamico += cantidad + ' Item(s) - <strong>$ ';
		htmlDinamico += precioString(precio) + '</strong> <i class="arrow_carrot-down"></i>';
		htmlDinamico += '</span>';
		$("#cantPrCarritoVgm").append(htmlDinamico);
		htmlDinamico = '<span id="totDwnCarritoVgmItem" class="amount"> $' + precioString(precio)  +'</span>';
		$("#totDwnCarritoVgm").append(htmlDinamico);
		/* si estoy en la pag de shopping cargo los items */
		if ($("#shopping-cart-content").length > 0) {
			calcularTotales();
		};
	}else{
		var htmlDinamico = '<i id="icono1CantPrCarritoVgm" class="icon_bag_alt"></i>';	
		htmlDinamico += '<span id="cantPrCarritoVgmItem" class="item-count"> ';
		htmlDinamico += '0 Item(s) - <strong>$ ';
		htmlDinamico += '0.00 </strong> <i class="arrow_carrot-down"></i>';
		htmlDinamico += '</span>';
		$("#cantPrCarritoVgm").append(htmlDinamico);
		htmlDinamico = '<span id="totDwnCarritoVgmItem" class="amount"> $ 0.00</span>';
		$("#totDwnCarritoVgm").append(htmlDinamico);
		/* si estoy en la pag de shopping cargo los items */
		if ($("#shopping-cart-content").length > 0) {
			calcularTotales();
		};
	};
};


function precioString(x) {
  return Number.parseFloat(x).toFixed(2);
};

function actualizarValorPrTotalItem(id) {
	let cantIngresada = document.getElementById("item-cant-" + id).value;
	//Se actualiza precio total
	if (cantIngresada > 0) {
		let precioDelItem = 0;
		let cbShopCart = JSON.parse(localStorage.getItem('cbShopCart'));
		cbShopCart.forEach(function(item){
			if (id === item.id){
				precioDelItem = item.precio;
				item.cantidad = cantIngresada;
			};
		});
		let precioTotal = "$ " + precioString(cantIngresada * precioDelItem);
		document.getElementById("item-prtotal-" + id).innerText = precioTotal;
		localStorage.setItem("cbShopCart", JSON.stringify(cbShopCart));
		refreshCantPrDwnCarritoVgm();
	};	
};

function calcularTotales() {
	let precio = 0;
	var cbShopCart = JSON.parse(localStorage.getItem("cbShopCart"));
	if (cbShopCart !== null){
		cbShopCart.forEach(function(item){
			precio += parseFloat(item.precio * item.cantidad);
		});	
	};
	$("#cart-pr-subtotal").remove();
	$("#cart-pr-total-gral").remove();
	let htmlsubtotal = "<span id='cart-pr-subtotal' class='sub-total'>Sub total<strong>$" + precioString(precio) + "</strong></span>";
	let htmltotalgral = "<span id='cart-pr-total-gral' class='grand-total'>Total <strong>$" + precioString(precio) + "</strong></span>";
	$("#cart-totales").append(htmlsubtotal);
	$("#cart-totales").append(htmltotalgral);
};

function imgError(image){ 
	$(image).attr("src","img/gral/imagen_no_disp.jpg");

} ;

function UrlActual(urlcompleta, urlpathname, urlabsuluta, urldominio, urlhash, urlquery) {
	this.urlcompleta = urlcompleta;
	this.urlpathname = urlpathname;
	this.urlabsuluta = urlabsuluta;
	this.urldominio = urldominio;
	this.urlhash = urlhash;
	this.urlquery = urlquery;
};

function getAbsolutePath() {
    var loc = window.location;
    var pathName = loc.pathname.substring(0, loc.pathname.lastIndexOf('/') + 1);
    return loc.href.substring(0, loc.href.length - ((loc.pathname + loc.search + loc.hash).length - pathName.length));
};

function resguardarUrlLocal() {
	var lsUrlcompleta = window.location.href;
	var lsUrlpathname = window.location.pathname;
	var lsUrlabsuluta = getAbsolutePath();
	var lsUrldominio = window.location.host;
	var lsUrlhash = window.location.hash;
	var lsUrlquery = window.location.search;
	var localUrl = JSON.parse(localStorage.getItem("localUrl"));	
	if (localUrl !== null){
		localStorage.removeItem("localUrl");
	};
	localUrl = [];
	var urlocal = new UrlActual(lsUrlcompleta, lsUrlpathname, lsUrlabsuluta, lsUrldominio, lsUrlhash, lsUrlquery)
	localUrl.push(urlocal);	
	localStorage.setItem("localUrl", JSON.stringify(localUrl));
};

if(document.getElementById("idiomas")){
	$('select#idiomas').change(function(){
		var lsValorSelect = $(this).val();
		var localUrl = JSON.parse(localStorage.getItem("localUrl"));
		if (lsValorSelect === "#") { lsValorSelect = "es";} /*Idioma por default*/
		localStorage.removeItem("optionValue");
		localStorage.setItem('optionValue', lsValorSelect);
		if (localUrl !== null){
			var lsurlhash = localUrl[0].urlhash;		
			var lsurlquery= localUrl[0].urlquery;
			var lsurlpaht = localUrl[0].urlpathname;
			if ( lsurlhash !== null || lsurlquery !== null ){
				if ( lsurlhash !== "" || lsurlquery !== "") {
					if ( lsurlhash !== "" && lsurlquery === ""){
						lsurlhash = delParamIdioma(lsurlhash);
						if (lsurlhash === "") {
							window.location  = lsurlpaht + lsurlhash + "?idioma=" + lsValorSelect;
						}else{
							window.location  = lsurlpaht + lsurlhash + "&idioma=" + lsValorSelect;
						};
							
					}else if ( lsurlhash === "" && lsurlquery !== ""){
						lsurlquery = delParamIdioma(lsurlquery);
						if (lsurlquery === "") {
							window.location  = lsurlpaht + lsurlquery + "?idioma=" + lsValorSelect;
						}else{
							window.location  = lsurlpaht + lsurlquery + "&idioma=" + lsValorSelect;
						};					
					};
				}else{
					window.location = lsurlpaht + "?idioma=" + lsValorSelect;
				};			
			}else{
				window.location = lsurlpaht + "?idioma=" + lsValorSelect;
			};
		};
	});
};

if(document.getElementById("tamanio")){
	$('select#tamanio').change(function(){
		var valorSelect = $(this).val();
		if (valorSelect === "#") { valorSelect = 12;} /*Por default*/
		getsize(valorSelect);
	});
};

if(document.getElementById("ordenamiento")){
	$('select#ordenamiento').change(function(){
		var valorSelect = $(this).val();
		if (valorSelect === "#") { valorSelect = "articulo.descripcion";} /*Por default*/
		getorder(valorSelect);
	});
};

function delParamIdioma(strCadena) {
	let posInicial = strCadena.indexOf("?idioma=");
	let intentos = 0;
	let respuesta = strCadena;
	if (posInicial === -1){
		intentos += 1;
		posInicial = strCadena.indexOf("&idioma=");
		if (posInicial === -1){
			intentos += 1;
			posInicial = strCadena.indexOf("idioma=");
			if (posInicial === -1){
				return respuesta;
			};
		};
	};
	if (intentos <= 1){
		let cadenaVieja = respuesta.substring(posInicial,posInicial + 10);
		respuesta = reemplazarCadena(cadenaVieja, "", respuesta);
	}else{
		let cadenaVieja = respuesta.substring(posInicial,posInicial + 9);
		respuesta = reemplazarCadena(cadenaVieja, "", respuesta);
	};
	return respuesta;
};

function reemplazarCadena(cadenaVieja, cadenaNueva, cadenaCompleta) {
// Reemplaza cadenaVieja por cadenaNueva en cadenaCompleta
   for (var i = 0; i < cadenaCompleta.length; i++) {
      if (cadenaCompleta.substring(i, i + cadenaVieja.length) == cadenaVieja) {
         cadenaCompleta= cadenaCompleta.substring(0, i) + cadenaNueva + cadenaCompleta.substring(i + cadenaVieja.length, cadenaCompleta.length);
      }
   }
   return cadenaCompleta;
};

if(document.getElementById("form-register")){
	$.validator.addMethod("campoemail", function(value, element) {
		return this.optional(element) || /^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$/.test(value);
		}, "Debe ser una direcci\u00f3n email valida");

	$.validator.addMethod("campotelefono", function(value, element) {
		return this.optional(element) || /^[0-9]{1,50}$/.test(value);
		}, "S\u00f3lo se adminten n\u00fameros");

	$.validator.addMethod("sin-caracter-especial", function(value, element) {
		return this.optional(element) || /^[A-Za-z\d=#$%@_ -]+$/.test(value);
		}, "No se adminten caracteres especiales");

	jQuery(function() {
		jQuery( "#form-register" ).validate({
			rules :{
				email : {
					required : true, //para validar campo vacio
					maxlength : 60,  //para validar campo con maximo 60 caracteres
					campoemail : ""  //para validar formato email					
					/*email	 : true*/
				},
				nombre : {
					required : true,
					minlength : 6, //para validar campo con minimo 6 caracteres
					maxlength : 50  //para validar campo con maximo 50 caracteres
				},
				usuario : {
					required : true,
					minlength : 6, //para validar campo con minimo 6 caracteres
					maxlength : 30  //para validar campo con maximo 30 caracteres
				},
				telefono : {
					required : true,
					minlength : 8, //para validar campo con minimo 8 caracteres
					maxlength : 20,  //para validar campo con maximo 20 caracteres
					number : true   //para validar campo solo numeros
				},
				clave : {
					required :true,
				},
				claverepetir: {
					required :true,
					equalTo: "#clave"
				},
				idCuit : {
					minlength : 11, //para validar campo con minimo 11 caracteres
					maxlength : 11,  //para validar campo con maximo 11 caracteres
					number : true   //para validar campo solo numeros
				},
				idNroDoc : {
					required : true,
					minlength : 7, //para validar campo con minimo 7 caracteres
					maxlength : 8,  //para validar campo con maximo 8 caracteres
					number : true   //para validar campo solo numeros
				}
			},
			messages : {
				email : {
					required : "Debe ingresar un email.",
					maxlength : "El email debe tener un m\u00e1ximo de 60 caracteres."
					/*email    : "Debe ingresar un email valido"*/
				},
				nombre : {
					required : "Debe ingresar un nombre.",
					minlength : "El nombre debe tener un m\u00ednimo de 6 caracteres.",
					maxlength : "El nombre debe tener un m\u00e1ximo de 50 caracteres."
				},
				usuario : {
					required : "Debe ingresar un usuario",
					minlength : "El usuario debe tener un m\u00ednimo de 6 caracteres.",
					maxlength : "El usuario debe tener un m\u00e1ximo de 30 caracteres."
				},
				telefono : {
					required : "Debe ingresar un tel\u00e9fono",
					minlength : "El tel\u00e9fono debe tener un m\u00ednimo de 6 caracteres.",
					maxlength : "El tel\u00e9fono debe tener un m\u00e1ximo de 50 caracteres.",
					number : "Solo se adminten n\u00fameros en en el tel\u00e9fono."
				},
				clave : {
					required : "Debe ingresar una clave."
				} ,
				claverepetir : {
					required : "Debe repetir la clave ingresada."
				},
				idCuit : {
					minlength : "El CUIT debe tener un m\u00ednimo de 11 caracteres.",
					maxlength : "El CUIT debe tener un m\u00e1ximo de 11 caracteres.",
					number : "Solo se adminten n\u00fameros en en el CUIT."
				},
				telefono : {
					required : "Debe ingresar un DNI",
					minlength : "El DNI debe tener un m\u00ednimo de 7 caracteres.",
					maxlength : "El DNI debe tener un m\u00e1ximo de 8 caracteres.",
					number : "Solo se adminten n\u00fameros en en el DNI."
				}
			}
		});
	});
};

if(document.getElementById("form-change-pass")){
	jQuery(function() {
		jQuery( "#form-change-pass" ).validate({
			rules :{
				user : {
					required : true,
					minlength : 6, //para validar campo con minimo 6 caracteres
					maxlength : 30  //para validar campo con maximo 30 caracteres
				},
				oldpassword : {
					required :true,
				},
				newPassword : {
					required :true,
				},
				repeatPassword: {
					required :true,
					equalTo: "#newPassword"
				}
			},
			messages : {
				user : {
					required : "Debe ingresar un usuario",
					minlength : "El usuario debe tener un m\u00ednimo de 6 caracteres.",
					maxlength : "El usuario debe tener un m\u00e1ximo de 30 caracteres."
				},
				oldpassword : {
					required : "Debe ingresar la clave actual."
				} ,
				newPassword : {
					required : "Debe ingresar la nueva clave."
				} ,
				repeatPassword : {
					required : "Debe repetir la nueva clave ingresada."
				}
			}
		});
	});
};

function getComercioSelected() {
	let lsValorSelect = document.getElementById("dropDnwComercios").value;
	if (lsValorSelect === "#") { lsValorSelect = "0";} /*No hay comercio selecionado*/
	let intValorSelect = parseInt(lsValorSelect);
	localStorage.removeItem("comercioSeleccionado");
	localStorage.setItem('comercioSeleccionado', intValorSelect);
}

function enviarPedido() {
	var cbShopCart = JSON.parse(localStorage.getItem("cbShopCart"));
	if ((cbShopCart === null || !Array.isArray(cbShopCart) || cbShopCart.length === 0)){
		swal({
			type: 'error',
			title: 'Error',
			text: 'No es posible envíar el pedido ya que su carrito esta vacío',
		});
		return
	};
	
	let comercioSelect = localStorage.getItem("comercioSeleccionado");
	if ((comercioSelect === null || comercioSelect === 0)){
		swal({
			type: 'error',
			title: 'Error',
			text: 'Debe eligir un comercio',
		});
		return
	};

	if (cbShopCart !== null){
		var url_enviar = "http://" + window.location.host
		/*DIA Y HORA*/
		var today = new Date();
		var dd = today.getDate();
		var mm = today.getMonth()+1; //January is 0!
		var yyyy = today.getFullYear();
		if(dd<10) {
			dd = '0'+dd;
		}; 
		if(mm<10) {
			mm = '0'+mm;
		};
		var time = today.getHours()+""+today.getMinutes()+""+today.getSeconds();
		today = yyyy+mm+dd;
		/************ "web-"+"PD-"+"-"+cliente.idSucursal+"-"+cliente.idCliente+"-"+comercioID+   */
		var idMovil = today+"-"+time;
		var listaArticulos = '"ventaDetalle":[';			
		cbShopCart.forEach(function(art){
			if (listaArticulos !=='"ventaDetalle":['){
				listaArticulos += ',';
			}
			listaArticulos += '{"articulo":{"id":'+art.id+'},"idMovil":"'+idMovil+'","cantidad":'+art.cantidad
							+',"unidades":'+art.cantidad+',"bultos":0}';
		});
		listaArticulos += ']';	
		var oparam = '{'+ listaArticulos+ ', "comercioSeleccionado":'+comercioSelect+'}';
		$.ajax({ 			
			method: "POST", 
			contentType: "application/json; charset=utf-8", 
			dataType: "json",  
			url: url_enviar + "/shopping-cart",
			data: oparam,
			timeout: 30000,	
			statusCode: {
				404: function(response){
					console.log(response);
					window.location.href = "404.html";				
				}/*,
				503: function(response){
					console.log(response);
					window.location.href = "503.html"
				}*/
			},
			beforeSend: function () {
				$('#loading').fadeIn();
			  },
			complete: function () {
				$('#loading').fadeOut(500);
			  },
			success: function (data) {
				if(data === 0){
					limpiarLocalStorage();
					swal({
					  type: 'success',
					  title: 'Envío',
					  text: 'El pedido ha sido enviado correctamente',
					}).then(function(){
						location.reload(); 
					});
				}else{
					swal({
						  type: 'error',
						  title: 'Error',
						  text: data,
						}).then(function(){
							location.reload(); 
						});
				}
				
			},
			error: function (jqXHR, textStatus, errorThrown) {
				swal({
				  type: 'error',
				  title: 'Error',
				  text: 'Parte del pedido no ha sido enviado correctamente. Problemas en el ingreso de pedidos.',
				}).then(function(){
					location.reload(); 
				});
			}, 
		});

	};
};

function limpiarLocalStorage() {
	localStorage.removeItem("cbShopCart");
	localStorage.removeItem("comercioSeleccionado");
	localStorage.removeItem("optionValue");
};