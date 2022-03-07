INSERT INTO clientes (apellidos, nombre, sexo, telefono) VALUES ('Menendez', 'Pablo', 'Varon', '987778866');
INSERT INTO clientes (apellidos, nombre, sexo, telefono) VALUES ('Suarez', 'Pedro', 'Varon', '985345324');
INSERT INTO clientes (apellidos, nombre, sexo, telefono) VALUES ('Rodriguez', 'Esteban', 'Varon', '986787656');
INSERT INTO clientes (apellidos, nombre, sexo, telefono) VALUES ('Martinez', 'Nuria', 'Mujer', '98644321');

INSERT INTO productos (descripcion, existencias, nombre, precio_unitario) VALUES ('Es una lavadora', '300', 'Lavadora 300HT', '200');
INSERT INTO productos (descripcion, existencias, nombre, precio_unitario) VALUES ('Es un movil', '200', 'Xiaomi Redmi 9', '250');
INSERT INTO productos (descripcion, existencias, nombre, precio_unitario) VALUES ('Es una batidora', '100', 'Batidora 3000 Max', '50');
INSERT INTO productos (descripcion, existencias, nombre, precio_unitario) VALUES ('Es una ordenador', '50', 'Ordenador HP 300', '800');

INSERT INTO ventas (cantidad, iva, subtotal, total, num_cliente, clave) VALUES ('2', '21', '200', '300', '1', '3');
INSERT INTO ventas (cantidad, iva, subtotal, total, num_cliente, clave) VALUES ('3', '10', '300', '150', '2', '2');
INSERT INTO ventas (cantidad, iva, subtotal, total, num_cliente, clave) VALUES ('4', '4', '500', '300', '3', '1');
