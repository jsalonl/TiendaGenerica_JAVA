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
	    <title>Reporte de Ventas por Cliente</title>
	      
	    <!-- Bootstrap CSS & Style CSS-->
	    <link rel="stylesheet" href="assets/css/styles.css">
		<!-- DataTables CSS-->
		<link rel="stylesheet" href="assets/vendor/DataTables/datatables.min.css">
	</head>
	<body class="sb-nav-fixed">
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
                            <a class="nav-link" href="ventas.jsp">
                                <span class="sb-nav-link-icon"><i class="fas fa-credit-card"></i></span>
                                Ventas
                            </a>
                            <a class="nav-link" href="#collapseReportes" data-bs-toggle="collapse" data-bs-target="#collapseReportes" >
                                <span class="sb-nav-link-icon"><i class="fas fa-file-alt"></i></span>
                                Reportes
                                <span class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></span>
                            </a>
                            <div class="collapse show" id="collapseReportes" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="reporteUsuarios.jsp">Usuarios</a>
                                    <a class="nav-link" href="reporteClientes.jsp">Clientes</a>
                                    <a class="nav-link active" href="reporteVentasCliente.jsp">Ventas por cliente</a>
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
							<h3>Listado de ventas por cliente</h3>
						</div>
								
						<div class="row mt-3">
							<div class="col-md-12">
								<table id="tablaUsuarios" class="table table-bordered table-striped" width="100%">
									<thead>
										<tr>
											<th>Cedula</th>
											<th>Nombre</th>
											<th>Valor Total Ventas</th>
										</tr>
									</thead>
									<tbody id="bodyTable">
										
									</tbody>
									<tfoot>
										<tr>
											<th colspan="2" class="text-end">Total</th>
											<th id="granTotal"></th>
										</tr>
									</tfoot>
								</table>
							</div>
						</div>
					</div>
				</main>
	    	</div><!-- Contenido estatico -->
	    	
		</div><!-- Contenido -->
		
		
		
		<!-- Scripts Bootstrap & DataTables -->
		<script type="text/javascript" src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="assets/vendor/DataTables/datatables.min.js"></script>
		<!-- Script FontAwesome -->
		<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"></script>
		<!-- Script Sweet AlertJS -->
		<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
		<!-- Script Usuarios -->
		<script type="text/javascript" src="assets/js/scripts.js"></script>
		<script type="text/javascript" src="assets/js/reporteVentaCliente.js"></script>
	</body>
</html>
