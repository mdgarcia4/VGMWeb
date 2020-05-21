function Pedido(id, descripcion, cantidad, precio) {
  this.id = id;
  this.descripcion = descripcion;
  this.cantidad = cantidad;
  this.precio = precio;
};

/*agrego items al ddn-carrito*/
function agregarDwnCarritoVgm(id, isCombo,msj){	
	var cantidad = 1;//parseInt($("#item-cant-"+id).val(),10);
	var descripcion = $("#item-descr-"+id).text();;
	var precio = parseFloat(parseFloat($("#item-precio-"+id).text()).toFixed(2));
	var lsprecio = $("#item-precio-"+id).text();
	
	var htmlDinamico = '<li id="li-dwn-' + id + '">';	
	htmlDinamico += '<figure class="item-image">';
	htmlDinamico += '<a href="#"><img src="img/img-ddcarrito.jpg" alt="t-shirt" /></a>';
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
			var nuevoPedido = new Pedido(id, descripcion, cantidad, precio);
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

/*borro completo el cartito isHome es vi viene de la Home.html*/
function vaciarCarrito(isHome){
	if(isHome === 'S'){
		var cbShopCart = JSON.parse(localStorage.getItem("cbShopCart"));
		cbShopCart.forEach(function(item){
			$("li-dwn-"+item.id).remove();
		});
	}
	localStorage.removeItem("cbShopCart");
	localStorage.removeItem("cbShopCart-combos");
};
/*Borra un elemento de dropdown del carrito*/
function borrarItemDwnCarritoVgm(id) {
	$("#li-dwn-"+id).remove();
};

function refreshCantPrDwnCarritoVgm() {
	var cantidad = 0;
	var precio = 0;
	var cbShopCart = JSON.parse(localStorage.getItem("cbShopCart"));
	if (cbShopCart !== null){
		cbShopCart.forEach(function(item){
			cantidad += parseInt(item.cantidad,10);
			precio += parseFloat(parseFloat(item.precio).toFixed(2));
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
		htmlDinamico += precio + '</strong> <i class="arrow_carrot-down"></i>';
		htmlDinamico += '</span>';
		$("#cantPrCarritoVgm").append(htmlDinamico);
		htmlDinamico = '<span id="totDwnCarritoVgmItem" class="amount"> $' + precio +'</span>';
		$("#totDwnCarritoVgm").append(htmlDinamico);
	}else{
		var htmlDinamico = '<i id="icono1CantPrCarritoVgm" class="icon_bag_alt"></i>';	
		htmlDinamico += '<span id="cantPrCarritoVgmItem" class="item-count"> ';
		htmlDinamico += '0 Item(s) - <strong>$ ';
		htmlDinamico += '0.00 </strong> <i class="arrow_carrot-down"></i>';
		htmlDinamico += '</span>';
		$("#cantPrCarritoVgm").append(htmlDinamico);
		htmlDinamico = '<span id="totDwnCarritoVgmItem" class="amount"> $ 0.00</span>';
		$("#totDwnCarritoVgm").append(htmlDinamico);
	}
};