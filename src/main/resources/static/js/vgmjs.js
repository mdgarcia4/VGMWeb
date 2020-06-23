
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
};

function loadItemDwnCarrito(){
	var cbShopCart = JSON.parse(localStorage.getItem("cbShopCart"));
	if (cbShopCart === null){
		cbShopCart = [];
	};
	cbShopCart.forEach(function(item){
		var id = item.id;
		var cantidad = item.cantidad;
		var descripcion = item.descripcion;
		var lsprecio = precioString(item.precio);
		var lsimagensrc = item.srcimagen;		
		
		var htmlDinamico = '<li id="li-dwn-' + id + '">';	
		htmlDinamico += '<figure class="item-image">';
		htmlDinamico += '<a href="#"><img src="' + lsimagensrc + '" alt="t-shirt" onerror="imgErrorLoad(this);" /></a>';
		htmlDinamico += '</figure>';
		htmlDinamico += '<div class="item-description">';
		htmlDinamico += '<h4><a href="#" class="item-name">' + descripcion + '</a></h4>';
		htmlDinamico += '<span class="review-icon clearfix"> <i class="icon_star active"></i> <i class="icon_star active"></i> <i class="icon_star active"></i> <i class="icon_star active"></i> <i class="icon_star"></i> </span>';
		htmlDinamico += '<span class="price"> $ ' + lsprecio + '</span>';
		htmlDinamico += '</div>';
		htmlDinamico += '<span id="li-dwn-' + id + '" class="delete fa fa-trash" onClick="borrarItemDwnCarritoVgm(' + id + ')"> </span>';
		htmlDinamico += '</li>';
		$("#dwnCarritoVgm").append(htmlDinamico);
		refreshCantPrDwnCarritoVgm();
	});
};

function Pedido(id, descripcion, cantidad, precio,srcimagen) {
  this.id = id;
  this.descripcion = descripcion;
  this.cantidad = cantidad;
  this.precio = precio;
  this.srcimagen = srcimagen;
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
		htmlDinamico += '<a id="li-dwn-' + id + '" onClick="borrarItemDwnCarritoVgm(' + id + ')" class="trash"><i class="fa fa-trash"></i></a>';
		htmlDinamico += '<a href="#" class="pencil"><span class="icon_pencil" aria-hidden="true"></span></a></td>';
		htmlDinamico += '</tr>';
		$("#itemShoppingCart").append(htmlDinamico);
		calcularTotales();
	});
}

/*agrego items al ddn-carrito*/
function agregarDwnCarritoVgm(id, isCombo,msj){	
	var cantidad = 1;//parseInt($("#item-cant-"+id).val(),10);
	var descripcion = $("#item-descr-"+id).text();
	var precio = parseFloat(parseFloat($("#item-precio-"+id).text()).toFixed(2));
	var lsprecio = $("#item-precio-"+id).text();	
	var ele_imagen = document.getElementById("item-fig-"+id);
	var lsimagen =  ele_imagen.getAttribute("src");
	
	var htmlDinamico = '<li id="li-dwn-' + id + '">';	
	htmlDinamico += '<figure class="item-image">';
	htmlDinamico += '<a href="#"><img src="'+ lsimagen + '" alt="t-shirt" onerror="imgErrorLoad(this);" /></a>';
	htmlDinamico += '</figure>';
	htmlDinamico += '<div class="item-description">';
	htmlDinamico += '<h4><a href="#" class="item-name">' + descripcion + '</a></h4>';
	htmlDinamico += '<span class="review-icon clearfix"> <i class="icon_star active"></i> <i class="icon_star active"></i> <i class="icon_star active"></i> <i class="icon_star active"></i> <i class="icon_star"></i> </span>';
	htmlDinamico += '<span class="price"> $ ' + lsprecio + '</span>';
	htmlDinamico += '</div>';
	htmlDinamico += '<span id="li-dwn-' + id + '" class="delete fa fa-trash" onClick="borrarItemDwnCarritoVgm(' + id + ')"> </span>';
	htmlDinamico += '</li>';
	$("#dwnCarritoVgm").append(htmlDinamico);
	/*mando a agregar el item localstorage*/
	addItem(id, isCombo,msj);
	refreshCantPrDwnCarritoVgm();
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
	var cantidad = 1;//parseInt($("#item-cant-"+id).val(),10);
	var descripcion = $("#item-descr-"+id).text();
	var precio = parseFloat(parseFloat($("#item-precio-"+id).text()).toFixed(2));
	var ele_imagen = document.getElementById("item-fig-"+id);
	var lsimagen =  ele_imagen.getAttribute("src");
	if(isCombo === "N"){
		var cbShopCart = JSON.parse(localStorage.getItem("cbShopCart"));
	}else{
		var cbShopCart = JSON.parse(localStorage.getItem("cbShopCart-combos"));
	};
	if (cbShopCart === null){
		cbShopCart = [];
	};
	var find = false;
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
	  title: cantidad+' '+descripcion+ ' se ha agregado correctamente al carrito',
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
		confirmButtonColor: '#3085d6',
		cancelButtonColor: '#d33',
        confirmButtonText: "Sí, limpiar",
        cancelButtonText: "Cancelar"
	  }).then(resultado => {
        if (resultado.value) {
            // Hicieron click en "Sí"
            var cbShopCart = JSON.parse(localStorage.getItem("cbShopCart"));
			cbShopCart.forEach(function(item){
				borrarItemDwnCarritoVgm(item.id);
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
function borrarItemDwnCarritoVgm(id) {
	$("#li-dwn-"+id).remove();
	/* si estoy en la pag de shopping cargo los items */
	if ($("#shopping-cart-content").length > 0) {
		$("#tr-item-"+id).remove();
	};
	deleteStorage(id);
	refreshCantPrDwnCarritoVgm();
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
	$(image).attr("src","img/imagen_no_disp.jpg");

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
		respuesta = reemplazarCadena(cadenaVieja, "", respuesta)
	}else{
		let cadenaVieja = respuesta.substring(posInicial,posInicial + 9);
		respuesta = reemplazarCadena(cadenaVieja, "", respuesta)
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
		var oparam = '{'+ listaArticulos+ '}';
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
			/*beforeSend: function (xhr) {
				xhr.setRequestHeader("Accept", "application/json"); 
				xhr.setRequestHeader("Authorization", "Basic dmdtcHJldmVudGE6cHJldmVudGEq");
				xhr.setRequestHeader("Content-Type", "application/json"); 
			}, */
			success: function (data) {
				localStorage.removeItem("cbShopCart");
				swal({
				  type: 'success',
				  title: 'Envío',
				  text: 'El pedido ha sido enviado correctamente',
				}).then(function(){
					location.reload(); 
				});
			},
			error: function (jqXHR, textStatus, errorThrown) {
				swal({
				  type: 'error',
				  title: 'Error',
				  text: 'Parte del pedido no ha sido enviado correctamente',
				}).then(function(){
					location.reload(); 
				});
			}, 
		});

	};
}