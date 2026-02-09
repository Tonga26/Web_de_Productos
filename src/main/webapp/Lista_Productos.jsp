<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Inventario de productos</title>
    <link rel="stylesheet" type="text/css" href="css/estilos.css">
</head>
<body>

<h1>Lista de Productos</h1>

<table>
    <tr>
        <th>Código</th>
        <th>Sección</th>
        <th>Nombre</th>
        <th>Fecha</th>
        <th>Precio</th>
        <th>Importado</th>
        <th>País de origen</th>
        <th>Acción</th>
    </tr>

    <c:forEach var="tempProd" items="${LISTAPRODUCTOS}">
        <!-- Link para cada producto con su campo clave -->
        <c:url var="linkTemp" value="ControladorProductos">
            <c:param name="instruccion" value="cargar"></c:param>
            <c:param name="cArticulo" value="${tempProd.cArt}"></c:param>
        </c:url>

        <tr>
            <td>${tempProd.cArt}</td>
            <td>${tempProd.seccion}</td>
            <td>${tempProd.nArt}</td>
            <td>${tempProd.fecha}</td>
            <td>${tempProd.precio}</td>
            <td>${tempProd.importado}</td>
            <td>${tempProd.pOrig}</td>
            <td><a href="${linkTemp}" class="enlace-boton boton-actualizar">Actualizar</a></td>
        </tr>
    </c:forEach>

</table>

<div id="contenedorBoton">
    <input type="button" value="Insertar Nuevo Producto" class="boton-insertar" onclick="window.location.href='Inserta_Producto.jsp'"/>
</div>

</body>
</html>