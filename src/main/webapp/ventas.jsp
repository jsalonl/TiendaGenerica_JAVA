<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="modelo.VentaDAO" %>
<%
HttpSession sesion = request.getSession();
if(sesion.getAttribute("usuario") == null){
	response.sendRedirect("index.jsp");
}
VentaDAO dao = new VentaDAO();
%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="es">
 	<head>
	    <!-- Required meta tags -->
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	    <link rel="shortcut icon" href="#" />  
	    <title>Ventas</title>
	      
	    <!-- Bootstrap CSS & Style CSS-->
	    <link rel="stylesheet" href="assets/css/styles.css">
	    <link rel="stylesheet" href="assets/css/invoice.css">
		<!-- DataTables CSS-->
		<link rel="stylesheet" href="assets/vendor/DataTables/datatables.min.css">
	</head>
	<body>
		<!-- Menu Top -->
		<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" href="index.html">Tienda Generica</a>
            <!-- Sidebar Toggle-->
            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle">
            	<i class="fas fa-bars"></i>
            </button>
            <!-- Navbar Search-->
            <div class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
            </div>
            <!-- Navbar-->
            <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="Login?out">Salir</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
        <!-- Menu Top -->
        
        <!-- Contenido -->
        <div id="layoutSidenav">
        	<!-- Sidebar -->
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            <a class="nav-link" href="#">
                                <span class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></span>
                                Panel Principal
                            </a>
                            <a class="nav-link" href="usuarios.jsp">
                                <span class="sb-nav-link-icon"><i class="fas fa-user-tie"></i></span>
                                Usuarios
                            </a>
                            <a class="nav-link" href="clientes.jsp">
                                <span class="sb-nav-link-icon"><i class="fas fa-users"></i></span>
                                Clientes
                            </a>
                            <a class="nav-link" href="proveedores.jsp">
                                <span class="sb-nav-link-icon"><i class="fas fa-building"></i></span>
                                Proveedores
                            </a>
                            <a class="nav-link" href="productos.jsp">
                                <span class="sb-nav-link-icon"><i class="fas fa-shopping-cart"></i></span>
                                Productos
                            </a>
                            <a class="nav-link active" href="ventas.jsp">
                                <span class="sb-nav-link-icon"><i class="fas fa-credit-card"></i></span>
                                Ventas
                            </a>
                            <a class="nav-link collapsed" href="#collapseReportes" data-bs-toggle="collapse" data-bs-target="#collapseReportes" >
                                <span class="sb-nav-link-icon"><i class="fas fa-file-alt"></i></span>
                                Reportes
                                <span class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></span>
                            </a>
                            <div class="collapse" id="collapseReportes" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="reporteUsuarios.jsp">Usuarios</a>
                                    <a class="nav-link" href="reporteClientes.jsp">Clientes</a>
                                    <a class="nav-link" href="reporteVentasCliente.jsp">Ventas por cliente</a>
                                </nav>
                            </div>
                        </div>
                    </div>
                    <div class="sb-sidenav-footer">
                        <div class="small">Logueado como:</div>
                        ${sessionScope.nombreUsuario}
                    </div>
                </nav>
            </div>
            <!-- Sidebar -->
      
      		<!-- Contenido estatico -->
			<div id="layoutSidenav_content">
				<main>
					<div class="container-fluid px-4 py-2">
		    			
		    			<div class="row">
							<h3>Modulo Ventas</h3>
						</div>
						
		    			<div class="row">
		               		<div class="col-12">
		               			<form id="buscarCliente">
								<div class="input-group mb-3">
									<input type="text" class="form-control" id="cedulaCliente" placeholder="Ingrese cedula del cliente">
								  	<div class="input-group-append">
								  		<button class="btn btn-success" type="submit">Buscar</button>
								  	</div>
								</div>
								</form>
		               		</div>
		               	</div>
		                
		                <div class="row mt-2 d-none panelVentas">
		                    <div class="col-sm-6">
		                        <div>
		                            <span id="nombreCliente" class="text-600 text-110 text-blue align-middle"></span>
		                        </div>
		                        <div class="text-grey-m2">
		                            <div id="direccionCliente" class="my-1"></div>
		                            <div id="emailCliente" class="my-1"></div>
		                            <div id="telefonoCliente" class="my-1"></div>
		                        </div>
		                    </div>
	
		                    <div class="text-95 col-sm-6 align-self-start d-sm-flex justify-content-end">

		                        <div class="text-grey-m2">
		                            <div class="text-600 text-110 text-blue align-middl">
		                                Codigo Venta : <span id="codigoVenta"><%=dao.consecutivoVenta() %></span>
		                            </div>
		                        </div>
		                    </div>
		                    
		                </div>
		
		                <div class="row mt-4 d-none panelVentas">
		                	<form id="formularioVenta">
		                	<input type="hidden" name="insertar" value="">
		                	<input type="hidden" name="codigoVenta" id="codigoVenta" value="<%=dao.consecutivoVenta() %>">
		                	<input type="hidden" name="cedulaCliente" id="cedula">
		                	<!-- //TODO sesion para usuario  -->
		                	<input type="hidden" name="cedulaUsuario" value="${sessionScope.usuario}">
		                	<input type="hidden" name="ivaVenta" id="iva">
		                	<input type="hidden" name="totalVenta" id="subtotal">
		                	<input type="hidden" name="valorVenta" id="total">
				            <div class="table-responsive">
				                <table class="table table-striped table-borderless border-0 border-b-2 brc-default-l1">
				                    <thead class="bg-none bg-blue">
				                        <tr class="text-white">
				                            <th>Codigo</th>
				                            <th>Nombre</th>
				                            <th>Cantidad</th>
				                            <th>Valor</th>
				                        </tr>
				                    </thead>
				
				                    <tbody class="text-95 text-secondary-d3">
				                    	<!-- Producto 1 -->
				                        <tr>
				                            <td width="20%">
				                            	<input type="text" name="codp1" class="form-control codigoProducto">
				                            </td>
				                            <td>
				                            	<input type="text" readonly class="form-control nombreProducto">
				                            </td>
				                            <td width="10%">
				                            	<input type="number" name="canp1" class="form-control cantidadProducto">
				                            </td>
				                             <td class="text-end" width="15%">
				                            	<input type="text" readonly class="form-control text-end valorProducto">
				                            </td>
				                        </tr> 
				                        <!-- Producto 2 -->
				                        <tr>
				                            <td width="20%">
				                            	<input type="text" name="codp2" class="form-control codigoProducto">
				                            </td>
				                            <td>
				                            	<input type="text" readonly class="form-control nombreProducto">
				                            </td>
				                            <td width="10%">
				                            	<input type="number" name="canp2" class="form-control cantidadProducto">
				                            </td>
				                             <td class="text-end" width="15%">
				                            	<input type="text" readonly class="form-control text-end valorProducto">
				                            </td>
				                        </tr> 
				                        <!-- Producto 3 -->
				                        <tr>
				                            <td width="20%">
				                            	<input type="text" name="codp3" class="form-control codigoProducto">
				                            </td>
				                            <td>
				                            	<input type="text" readonly class="form-control nombreProducto">
				                            </td>
				                            <td width="10%">
				                            	<input type="number" name="canp3" class="form-control cantidadProducto">
				                            </td>
				                             <td class="text-end" width="15%">
				                            	<input type="text" readonly class="form-control text-end valorProducto">
				                            </td>
				                        </tr> 
				                    </tbody>
				                    <tfoot>
				                    	<tr>
				                    		<th class="text-end" colspan="3">Subtotal</th>
				                    		<th class="text-end subTotal"></th>
				                    	</tr>
				                    	<tr>
				                    		<th class="text-end" colspan="3">IVA</th>
				                    		<th class="text-end valorIVA"></th>
				                    	</tr>
				                    	<tr>
				                    		<th class="text-end" colspan="3">Total</th>
				                    		<th class="text-end valorTotal"></th>
				                    	</tr>
				                    </tfoot>
				                </table>
				            </div>
				            <div class="d-grid gap-2">
							  <button class="btn btn-success" type="submit">Realizar Venta</button>
							</div>
				            </form>
	                </div>            
	             </div>
             </main>
			</div>
		</div>
	     <!-- Scripts Bootstrap & DataTables -->
		<script type="text/javascript" src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="assets/vendor/DataTables/datatables.min.js"></script>
		<!-- Script FontAwesome -->
		<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"></script>
		<!-- Script Sweet AlertJS -->
		<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
		<!-- Script Productos -->
		<script type="text/javascript" src="assets/js/scripts.js"></script>
		<script type="text/javascript" src="assets/js/venta.js"></script>
	</body>
</html>
