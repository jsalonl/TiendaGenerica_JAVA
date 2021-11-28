CREATE DATABASE  IF NOT EXISTS `tiendagenerica` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `tiendagenerica`;

START TRANSACTION;

CREATE TABLE `cliente` (
  `cedula_cliente` bigint(20) NOT NULL,
  `direccion_cliente` varchar(255) NULL,
  `email_cliente` varchar(255) NULL,
  `nombre_cliente` varchar(255) NULL,
  `telefono_cliente` varchar(255) NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `detalle_venta` (
  `codigo_detalle_venta` bigint(20) NOT NULL AUTO_INCREMENT,
  `cantidad_producto` int(11) DEFAULT NULL,
  `codigo_producto` bigint(20) DEFAULT NULL,
  `codigo_venta` bigint(20) DEFAULT NULL,
  `valor_total` double DEFAULT NULL,
  `valor_venta` double DEFAULT NULL,
  `valor_iva` double DEFAULT NULL
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `producto` (
  `codigo_producto` bigint(20) NOT NULL,
  `iva_compra` double DEFAULT NULL,
  `nit_proveedor` bigint(20) NOT NULL,
  `nombre_producto` varchar(255) DEFAULT NULL,
  `precio_compra` double DEFAULT NULL,
  `precio_venta` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `proveedor` (
  `nit_proveedor` bigint(20) NOT NULL,
  `ciudad_proveedor` varchar(255) DEFAULT NULL,
  `direccion_proveedor` varchar(255) DEFAULT NULL,
  `nombre_proveedor` varchar(255) DEFAULT NULL,
  `telefono_proveedor` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `usuario` (
  `cedula_usuario` bigint(20) NOT NULL,
  `email_usuario` varchar(255) DEFAULT NULL,
  `nombre_usuario` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `usuario` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `venta` (
  `codigo_venta` bigint(20) NOT NULL,
  `cedula_cliente` bigint(20) NOT NULL,
  `cedula_usuario` bigint(20) NOT NULL,
  `iva_venta` double DEFAULT NULL,
  `total_venta` double DEFAULT NULL,
  `valor_venta` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `cliente`
  ADD PRIMARY KEY (`cedula_cliente`);

ALTER TABLE `detalle_venta`
  ADD PRIMARY KEY (`codigo_detalle_venta`),
  ADD KEY `codigo_venta` (`codigo_venta`),
  ADD KEY `codigo_producto` (`codigo_producto`);

ALTER TABLE `producto`
  ADD PRIMARY KEY (`codigo_producto`,`nit_proveedor`),
  ADD KEY `nit_proveedor` (`nit_proveedor`);

ALTER TABLE `proveedor`
  ADD PRIMARY KEY (`nit_proveedor`);

ALTER TABLE `usuario`
  ADD PRIMARY KEY (`cedula_usuario`);

ALTER TABLE `venta`
  ADD PRIMARY KEY (`codigo_venta`,`cedula_cliente`,`cedula_usuario`),
  ADD KEY `cedula_cliente` (`cedula_cliente`),
  ADD KEY `cedula_usuario` (`cedula_usuario`);

ALTER TABLE `cliente`
  MODIFY `cedula_cliente` bigint(20) NOT NULL;

ALTER TABLE `detalle_venta`
  MODIFY `codigo_detalle_venta` bigint(20) NOT NULL AUTO_INCREMENT;

ALTER TABLE `producto`
  MODIFY `codigo_producto` bigint(20) NOT NULL;

ALTER TABLE `proveedor`
  MODIFY `nit_proveedor` bigint(20) NOT NULL;

ALTER TABLE `usuario`
  MODIFY `cedula_usuario` bigint(20) NOT NULL ;

ALTER TABLE `venta`
  MODIFY `codigo_venta` bigint(20) NOT NULL AUTO_INCREMENT;

ALTER TABLE `detalle_venta`
  ADD CONSTRAINT `detalle_venta_ibfk_1` FOREIGN KEY (`codigo_venta`) REFERENCES `venta` (`codigo_venta`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detalle_venta_ibfk_2` FOREIGN KEY (`codigo_producto`) REFERENCES `producto` (`codigo_producto`);

ALTER TABLE `producto`
  ADD CONSTRAINT `producto_ibfk_1` FOREIGN KEY (`nit_proveedor`) REFERENCES `proveedor` (`nit_proveedor`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `venta`
  ADD CONSTRAINT `venta_ibfk_1` FOREIGN KEY (`cedula_cliente`) REFERENCES `cliente` (`cedula_cliente`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `venta_ibfk_2` FOREIGN KEY (`cedula_usuario`) REFERENCES `usuario` (`cedula_usuario`) ON DELETE CASCADE;
COMMIT;
