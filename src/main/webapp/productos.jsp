<%@ page import="javax.servlet.http.HttpSession" %>
<%
HttpSession sesion = request.getSession();
if(sesion.getAttribute("usuario") == null){
	response.sendRedirect("index.jsp");
}
%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="es">
 	<head>
	    <!-- Required meta tags -->
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	    <link rel="shortcut icon" href="#" />  
	    <title>Productos</title>
	      
	    <!-- Bootstrap CSS & Style CSS-->
	    <link rel="stylesheet" href="assets/css/styles.css">
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
                            <a class="nav-link active" href="productos.jsp">
                                <span class="sb-nav-link-icon"><i class="fas fa-shopping-cart"></i></span>
                                Productos
                            </a>
                            <a class="nav-link" href="ventas.jsp">
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
							<h3>Modulo Producto</h3>
						</div>
			
						<div class="row">
							<div class="col-md-12">
								<button id="btnNuevo" type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#modalAgregarUsuario">Nuevo</button>
							</div>
						</div>
			
						<div class="row mt-3">
							<div class="col-md-12">
								<table id="tablaUsuarios" class="table table-bordered table-striped" width="100%">
									<thead>
										<tr>
											<th>Codigo</th>
											<th>Proveedor</th>
											<th>IVA Compra</th>
											<th>Nombre</th>
											<th>Precio Compra</th>
											<th>Precio Venta</th>
											<th>Acciones</th>
										</tr>
									</thead>
									<tbody id="bodyTable">
										
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</main>
	    	</div><!-- Contenido estatico -->	
		</div><!-- Contenido -->
		<!-- Modal Agregar -->
		<div class="modal fade" id="modalAgregarUsuario" tabindex="-1" aria-labelledby="modalAgregarUsuarioLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Agregar Producto</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<form class="row" id="formAgregar" method="POST" action="Producto" enctype="multipart/form-data">
							<input type="hidden" name="cargar" value="">
							<div class="col-sm-12">
								<label for="csv" class="form-label">Archivo CSV</label>
								 <input class="form-control" type="file" id="archivo" name="archivo">
							</div>
							<div class="col-sm-12 mt-3">
								<button type="button" class="btn btn-secondary float-start" data-bs-dismiss="modal">Cancelar</button>
								<button type="submit" class="btn btn-primary float-end">Guardar</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		
		<!-- Modal Editar -->
		<div class="modal fade" id="modalEditarUsuario" tabindex="-1" aria-labelledby="modalAgregarUsuarioLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Editar Producto</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<form class="row" id="formEditar">
						<input type="hidden" name="editar" value="">
							<div class="col-sm-12">
								<label for="codigo" class="form-label">Codigo</label>
								<input type="tel" class="form-control" name="codigo" id="codigo" readonly required>
							</div>
							<div class="col-sm-12">
								<label for="ivaCompra" class="form-label">IVA Compra</label>
								<input type="text" class="form-control" name="ivaCompra" id="ivaCompra" required>
							</div>
							<div class="col-sm-12">
								<label for="nitProveedor" class="form-label">NIT Proveedor</label>
								<input type="tel" class="form-control" name="nitProveedor" id="nitProveedor" required>
							</div>
							<div class="col-sm-12">
								<label for="nombre" class="form-label">Nombre</label>
								<input type="text" class="form-control" name="nombre" id="nombre" required>
							</div>
							<div class="col-sm-12">
								<label for="precioCompra" class="form-label">Precio Compra</label>
								<input type="text" class="form-control" name="precioCompra" id="precioCompra" required>
							</div>
							<div class="col-sm-12">
								<label for="precioVenta" class="form-label">Precio Venta</label>
								<input type="text" class="form-control" name="precioVenta" id="precioVenta" required>
							</div>
							<div class="col-sm-12 mt-3">
								<button type="button" class="btn btn-secondary float-start" data-bs-dismiss="modal">Cancelar</button>
								<button type="submit"  class="btn btn-primary float-end">Editar</button>
							</div>
						</form>
					</div>
				</div>
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
		<script type="text/javascript" src="assets/js/product.js"></script>
	</body>
</html>
