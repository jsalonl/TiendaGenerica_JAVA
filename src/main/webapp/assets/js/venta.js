$(document).ready(function(){
	$('.valorProducto').val(0);
	$('.valorProducto').attr("value",0);
	//Funcion consultar cliente
	$("#buscarCliente").submit(function(e){
		e.preventDefault();
		var id = $("#cedulaCliente").val();
		$.ajax({
			type:"post",
	   		url:"Cliente", //Servlet
			data: "ver=&cedula="+id,
			dataType:"json",
			success: function(resultado){
				console.log(resultado);
				if(resultado.cedulaCliente!=null){
					$("#cedula").val(resultado.cedulaCliente);
					$("#nombreCliente").html(resultado.nombreCliente);
					$("#direccionCliente").html(resultado.direccionCliente);
					$("#emailCliente").html(resultado.emailCliente);
					$("#telefonoCliente").html(resultado.telefonoCliente);
					$(".panelVentas").removeClass("d-none").show();
				}else{
					$("#cedula, #nombreCliente, #direccionCliente, #emailCliente, #telefonoCliente").html("");
					$(".panelVentas").hide();
					alert("Ocurrio un error");
				}
			}
		});
	})
	
	//Funcion buscar producto
	$(".codigoProducto").blur(function(e){
		e.stopPropagation();
		var td = $(this);
		var id = $(this).val();
		$.ajax({
			type:"post",
	   		url:"Producto", //Servlet
			data: "ver=&codigo="+id,
			dataType:"json",
			success: function(resultado){
				if(resultado.codigoProducto!=null){
					td.parent().next().children().val(resultado.nombreProducto);
					td.parent().next().next().next().children().val(resultado.precioVenta);
					td.parent().next().next().next().children().attr("data-value",resultado.precioVenta);
					td.parent().next().next().next().children().attr("data-iva",resultado.ivaCompra);
				}else{
					td.val("");
					td.attr("value", "");
					alert("Ocurrio un error");
					td.parent().next().children().val("");
					td.parent().next().next().next().children().val(0);
					td.parent().next().next().next().children().attr(0);
				}
			}
		});
	})
	
	//Funcion calcular valor cantidad
	$(".cantidadProducto").blur(function(e){
		e.stopPropagation();
		var td = $(this);
		var cantidad = parseFloat($(this).val());
		var precio = td.parent().next().children().attr("data-value");
		if(isNaN(precio)) {
			var precio = 0;
		}
		var total = cantidad*precio;
		td.parent().next().children().attr("value", total);
		td.parent().next().children().val(total);
		calcularTotal();
	})
	
	function calcularTotal(){
		var subtotal = 0;
		var iva = 0;
		$('.valorProducto').each(function(){
		    subtotal += parseFloat($(this).attr("value"));
			if(isNaN(parseFloat($(this).attr("data-iva")))) {
				iva += 0;
			}else{
				iva += parseFloat($(this).attr("data-iva"))*parseFloat($(this).attr("value"))/100;
			}
		});
		$(".subTotal").html(subtotal);
		var valorIva = (iva);
		$(".valorIVA").html(valorIva);
		$(".valorTotal").html(subtotal+valorIva);
		//Pasar datos a formulario
		$("#subtotal").val(subtotal);
		$("#iva").val(valorIva);
		$("#total").val(subtotal+valorIva);
	}
	
	//Funcion Venta
	$("#formularioVenta").submit(function(e){
		e.preventDefault();
		var data = $("#formularioVenta").serialize();
		$.ajax({
			type:"post",
	   		url:"Venta", //Servlet
			data: data,
			dataType:"json",
			success: function(resultado){
				if(resultado[0].estado=="Ok"){
					Swal.fire(
						"Proceso exitoso",
					  	"Venta realizada",
					  	"success"
					)
					setTimeout(function(){
					    window.location.replace("ventas.jsp");
					}, 800);
				}
			}
		});
	});
	
});